#!/usr/bin/env python3
"""Build the WordMaster Sentence of the Day bank from real sentences.

Source: Tatoeba English sentences (per-language export,
https://downloads.tatoeba.org/exports/per_language/eng/eng_sentences.tsv.bz2),
CC-BY — every sentence is a real, grammatical English sentence written
by a human contributor; nothing here is generated or hand-written.

The script:
1. Streams the Tatoeba export (2,040,024 sentences) and keeps short,
   natural sentences (3-15 words) that start with a capital, end with
   . / ? / !, are plain ASCII, and contain at least one of the 1,000
   most frequent English words (scripts/words.json) as a whole word.
   Entries containing two short sentences are split; each half is
   itself a real sentence. Sentences are preferred when most of their
   words are in the top 1000 and when they contain several top-1000
   words.
2. Picks, for each of the 1,000 words, up to 3 sentences — walking the
   candidates best-first so sentences that cover several words serve
   several words at once.
3. Adds longer natural single sentences (16-30 words, at least half
   top-1000 words, at least 8 distinct top-1000 words) so the app can
   show a sentence using many mastered words at once: the top 500 by
   distinct top-1000 words, plus a diversity pass (up to 800 total)
   that makes sure every word has at least one long sentence. These
   are kept even when their words already have 3 short sentences.
4. Writes the bank to scripts/sentences.json and to
   src/wordmaster/data/sentences.cljs (compiled in), each entry
   {:text "..." :words ["book" "table"] :source "tatoeba"}, where
   :words is the list of top-1000 words the sentence contains, in
   order of appearance. Words with no sentence are written to
   scripts/uncovered_words.json and printed.

Token normalisation (lowercase, contractions expanded, possessives
stripped) mirrors wordmaster.sentence/normalize-token in the app, so
the words a sentence claims to contain are exactly the words the app
finds in its text.

Re-run with:
  python3 scripts/build_sentences.py
Raw export kept in data-raw/eng_sentences.tsv.bz2 for reproducibility.
"""

import bz2
import json
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
WORDS_JSON = os.path.join(ROOT, "scripts", "words.json")
TATOEBA = os.path.join(ROOT, "data-raw", "eng_sentences.tsv.bz2")
OUT_JSON = os.path.join(ROOT, "scripts", "sentences.json")
OUT_CLJS = os.path.join(ROOT, "src", "wordmaster", "data", "sentences.cljs")
UNCOVERED = os.path.join(ROOT, "scripts", "uncovered_words.json")
PER_WORD = 3
LONG_MIN_WORDS = 16
LONG_MAX_WORDS = 30
LONG_MIN_FRAC = 0.5
LONG_MIN_DISTINCT = 8
LONG_KEEP_TOP = 500
LONG_KEEP_TOTAL = 800

# A capitalised conjunction after a comma/semicolon is a poem-line
# artefact ("...with me, And what can be the use of him..."), not a
# normal sentence — reject those in the long pass.
CONJ_AFTER_PUNCT = re.compile(r"[,;]\s+(And|But|Or|So|Yet|For|Nor)\b")

TOKEN_RE = re.compile(r"[A-Za-z]+(?:'[A-Za-z]+)?")
ALLOWED = set(
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    " ,.'?!;:-()\""
)

# Contractions whose stem is not what naive suffix-stripping gives.
CONTRACTIONS = {
    "can't": "can", "won't": "will", "shan't": "shall", "ain't": "be",
    "couldn't": "could", "shouldn't": "should", "wouldn't": "would",
    "don't": "do", "doesn't": "does", "didn't": "did",
    "isn't": "is", "aren't": "are", "wasn't": "was", "weren't": "were",
    "haven't": "have", "hasn't": "has", "hadn't": "had",
    "i'm": "i", "you're": "you", "we're": "we", "they're": "they",
    "he's": "he", "she's": "she", "it's": "it", "that's": "that",
    "i've": "i", "we've": "we", "they've": "they",
    "i'll": "i", "you'll": "you", "we'll": "we", "they'll": "they",
    "he'll": "he", "she'll": "she", "it'll": "it",
    "i'd": "i", "you'd": "you", "he'd": "he", "she'd": "she",
    "we'd": "we", "they'd": "they", "let's": "let",
    "what's": "what", "who's": "who", "where's": "where",
    "when's": "when", "why's": "why", "how's": "how",
    "there's": "there", "here's": "here",
}


# Sentences that are real Tatoeba entries but demonstrably garbled
# (mistranslations with no coherent parse) — excluded by hand.
# Keys use the same normalisation as the dedupe key below.
DENYLIST = {
    "do not search for what you are worth in the eyes of people but rather try to find it in your conscience is free your status is automatically elevated",
}


def normalize_token(tok):
    t = tok.lower()
    if t in CONTRACTIONS:
        return CONTRACTIONS[t]
    if t.endswith("n't"):
        return t[:-3]
    for suf in ("'s", "'re", "'ve", "'ll", "'d", "'m"):
        if t.endswith(suf):
            return t[: -len(suf)]
    return t


def good_sentence(text):
    """Short, natural, plain sentence — or None with a reason."""
    if not text or not text[0].isupper():
        return False
    if text[-1] not in ".?!":
        return False
    if "..." in text or "  " in text:
        return False
    if any(ch not in ALLOWED for ch in text):
        return False
    if any(ch.isdigit() for ch in text):
        return False
    tokens = TOKEN_RE.findall(text)
    if not 3 <= len(tokens) <= 15:
        return False
    # No shouting / acronym-heavy sentences.
    if any(len(t) > 1 and t.isupper() for t in tokens):
        return False
    return True


def relaxed_good_sentence(text, top):
    """Like good_sentence, but allows digits and all-caps tokens that
    are themselves top-1000 words (USA, DVD, HTML …). Used only in a
    second pass for words the strict pass could not cover — month
    abbreviations (\"born on Dec. 5.\") and acronyms (\"I live in the
    USA.\") only occur in such sentences."""
    if not text or not text[0].isupper():
        return False
    if text[-1] not in ".?!":
        return False
    if "..." in text or "  " in text:
        return False
    if any(ch not in ALLOWED for ch in text):
        return False
    tokens = TOKEN_RE.findall(text)
    if not 3 <= len(tokens) <= 15:
        return False
    if any(len(t) > 1 and t.isupper() and t.lower() not in top for t in tokens):
        return False
    return True


def iter_candidates(text, good_fn, top):
    """Yield (score, sentence, distinct-top-words) for each sentence in
    a Tatoeba entry. Entries sometimes contain two short sentences
    (\"You should have come. It was fun.\"); each half is itself a
    real sentence, so split them — abbreviations (\"Oct. 23\") and
    initials are not split, because the split only happens before a
    capital letter."""
    for part in re.split(r"(?<=[.?!])\s+(?=[A-Z])", text):
        part = part.strip()
        if not good_fn(part):
            continue
        tokens = TOKEN_RE.findall(part)
        normed = [normalize_token(t) for t in tokens]
        in_top = [t for t in normed if t in top]
        if not in_top:
            continue
        frac = len(in_top) / len(tokens)
        if frac < 0.4:
            continue
        distinct = list(dict.fromkeys(in_top))
        score = (
            1 if frac >= 0.6 else 0,
            len(distinct),
            frac,
            -abs(len(tokens) - 8),
        )
        yield score, part, distinct


def iter_long_candidates(text, top):
    """Yield (score, sentence, distinct-top-words) for long single
    sentences (16-30 words) in a Tatoeba entry. Same splitting as
    iter_candidates; kept only when dense in top-1000 words, so the
    app can show one sentence using many mastered words at once."""
    for part in re.split(r"(?<=[.?!])\s+(?=[A-Z])", text):
        part = part.strip()
        if not part or not part[0].isupper():
            continue
        if part[-1] not in ".?!":
            continue
        if "..." in part or "  " in part:
            continue
        if any(ch not in ALLOWED for ch in part):
            continue
        if any(ch.isdigit() for ch in part):
            continue
        if CONJ_AFTER_PUNCT.search(part):
            continue
        tokens = TOKEN_RE.findall(part)
        if not LONG_MIN_WORDS <= len(tokens) <= LONG_MAX_WORDS:
            continue
        if any(len(t) > 1 and t.isupper() for t in tokens):
            continue
        normed = [normalize_token(t) for t in tokens]
        in_top = [t for t in normed if t in top]
        if not in_top:
            continue
        frac = len(in_top) / len(tokens)
        if frac < LONG_MIN_FRAC:
            continue
        distinct = list(dict.fromkeys(in_top))
        if len(distinct) < LONG_MIN_DISTINCT:
            continue
        score = (len(distinct), frac, -abs(len(tokens) - 20))
        yield score, part, distinct


def cljs_str(s):
    return '"' + s.replace("\\", "\\\\").replace('"', '\\"') + '"'


def main():
    with open(WORDS_JSON) as f:
        words = [w["word"] for w in json.load(f)]
    top = set(words)
    assert len(words) == 1000, len(words)

    # Pass 1: stream Tatoeba, keep the best-scoring variant of each
    # distinct sentence (normalised text as the dedupe key).
    best = {}
    seen = 0
    with bz2.open(TATOEBA, "rt", encoding="utf-8", errors="replace") as f:
        for line in f:
            parts = line.rstrip("\n").split("\t", 2)
            if len(parts) != 3:
                continue
            text = parts[2].strip()
            if not good_sentence(text):
                continue
            seen += 1
            for score, part, distinct in iter_candidates(
                text, good_sentence, top
            ):
                key = re.sub(r"[^a-z ]", "", part.lower())
                key = re.sub(r"\s+", " ", key).strip()
                if key in DENYLIST:
                    continue
                prev = best.get(key)
                if prev is None or score > prev[0]:
                    best[key] = (score, part, distinct)

    candidates = sorted(best.values(), key=lambda c: (c[0], c[1]), reverse=True)
    print(f"candidates kept: {len(candidates)} (from {seen} short clean sentences)")

    # Pass 2: walk best-first, giving each word up to PER_WORD sentences.
    per_word_count = {w: 0 for w in words}
    bank = []
    bank_texts = set()
    for _score, text, distinct in candidates:
        if all(per_word_count[w] >= PER_WORD for w in words):
            break
        if text in bank_texts:
            continue
        needed = [w for w in distinct if per_word_count[w] < PER_WORD]
        if not needed:
            continue
        bank.append({"text": text, "words": distinct, "source": "tatoeba"})
        bank_texts.add(text)
        for w in distinct:
            per_word_count[w] += 1

    covered = [w for w in words if per_word_count[w] > 0]
    uncovered = [w for w in words if per_word_count[w] == 0]

    # Second pass, relaxed rules, for the words the strict pass missed
    # (month abbreviations and acronyms). Same real Tatoeba sentences,
    # just allowing digits and all-caps tokens that are top-1000 words.
    if uncovered:
        uncovered_set = set(uncovered)
        relaxed = {}
        with bz2.open(TATOEBA, "rt", encoding="utf-8", errors="replace") as f:
            for line in f:
                parts = line.rstrip("\n").split("\t", 2)
                if len(parts) != 3:
                    continue
                text = parts[2].strip()
                if not relaxed_good_sentence(text, top):
                    continue
                for score, part, distinct in iter_candidates(
                    text, lambda t: relaxed_good_sentence(t, top), top
                ):
                    if not any(w in uncovered_set for w in distinct):
                        continue
                    key = re.sub(r"[^a-z ]", "", part.lower())
                    key = re.sub(r"\s+", " ", key).strip()
                    if key in DENYLIST:
                        continue
                    prev = relaxed.get(key)
                    if prev is None or score > prev[0]:
                        relaxed[key] = (score, part, distinct)
        added = 0
        for _score, text, distinct in sorted(
            relaxed.values(), key=lambda c: (c[0], c[1]), reverse=True
        ):
            if all(per_word_count[w] >= PER_WORD for w in uncovered):
                break
            if text in bank_texts:
                continue
            needed = [
                w for w in distinct if w in uncovered_set and per_word_count[w] < PER_WORD
            ]
            if not needed:
                continue
            bank.append({"text": text, "words": distinct, "source": "tatoeba"})
            bank_texts.add(text)
            added += 1
            for w in distinct:
                per_word_count[w] += 1
        print(f"relaxed pass: {len(relaxed)} candidates, added {added} sentences")
        covered = [w for w in words if per_word_count[w] > 0]
        uncovered = [w for w in words if per_word_count[w] == 0]

    # Long pass: keep long, dense sentences so one sentence can use
    # many mastered words at once (a 15-word sentence can hold at most
    # 15 distinct words). These are added even when their words already
    # have 3 short sentences: top 500 by distinct top-1000 words, then
    # a diversity pass up to 800 total that gives every word at least
    # one long sentence where one exists.
    long_best = {}
    with bz2.open(TATOEBA, "rt", encoding="utf-8", errors="replace") as f:
        for line in f:
            parts = line.rstrip("\n").split("\t", 2)
            if len(parts) != 3:
                continue
            text = parts[2].strip()
            for score, part, distinct in iter_long_candidates(text, top):
                key = re.sub(r"[^a-z ]", "", part.lower())
                key = re.sub(r"\s+", " ", key).strip()
                if key in DENYLIST:
                    continue
                prev = long_best.get(key)
                if prev is None or score > prev[0]:
                    long_best[key] = (score, part, distinct)
    long_sorted = sorted(long_best.values(), key=lambda c: (c[0], c[1]), reverse=True)
    long_per_word = {w: 0 for w in words}
    long_added = []
    for _score, text, distinct in long_sorted:
        if len(long_added) >= LONG_KEEP_TOP:
            break
        if text in bank_texts:
            continue
        long_added.append({"text": text, "words": distinct, "source": "tatoeba"})
        bank_texts.add(text)
        for w in distinct:
            long_per_word[w] += 1
    for _score, text, distinct in long_sorted:
        if len(long_added) >= LONG_KEEP_TOTAL:
            break
        if text in bank_texts:
            continue
        if not any(long_per_word[w] == 0 for w in distinct):
            continue
        long_added.append({"text": text, "words": distinct, "source": "tatoeba"})
        bank_texts.add(text)
        for w in distinct:
            long_per_word[w] += 1
    bank.extend(long_added)
    print(f"long pass: {len(long_best)} candidates, added {len(long_added)} sentences "
          f"(top {LONG_KEEP_TOP} + diversity to {LONG_KEEP_TOTAL})")
    print(f"long sentences: max distinct top-1000 words "
          f"{max(len(s['words']) for s in long_added)}; "
          f"words with a long sentence: "
          f"{sum(1 for w in words if long_per_word[w] > 0)}/1000")

    print(f"bank: {len(bank)} sentences")
    print(f"coverage: {len(covered)}/1000 words")
    print(f"uncovered ({len(uncovered)}): {uncovered}")

    with open(OUT_JSON, "w") as f:
        json.dump(bank, f, indent=1)
    with open(UNCOVERED, "w") as f:
        json.dump(uncovered, f, indent=1)

    with open(OUT_CLJS, "w") as f:
        f.write(
            "(ns wordmaster.data.sentences\n"
            "  \"Real English sentences for Sentence of the Day.\n\n"
            "   Source: Tatoeba English sentences (CC-BY),\n"
            "   https://downloads.tatoeba.org/exports/per_language/eng/,\n"
            "   filtered to natural sentences containing top-1000 words:\n"
            "   short ones (3-15 words, up to 3 per word) plus long dense\n"
            "   ones (16-30 words, at least 8 distinct top-1000 words) so\n"
            "   one sentence can use many mastered words at once.\n"
            "   Generated by scripts/build_sentences.py — do not edit by hand.\n"
            "   Each entry: {:text \\\"...\\\" :words [top-1000 words it contains]\n"
            "   :source \\\"tatoeba\\\"}.\"\n"
            "  )\n\n"
            "(def sentences\n  ["
        )
        for i, s in enumerate(bank):
            words_cljs = " ".join(cljs_str(w) for w in s["words"])
            f.write(
                "\n   {:text %s :words [%s] :source \"tatoeba\"}"
                % (cljs_str(s["text"]), words_cljs)
            )
        f.write("])\n")
    print(f"wrote {OUT_JSON} and {OUT_CLJS}")


if __name__ == "__main__":
    main()
