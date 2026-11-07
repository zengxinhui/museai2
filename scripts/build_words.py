#!/usr/bin/env python3
"""Build the WordMaster word list.

1. Takes the first 1000 words of data-raw/20k.txt — the
   "Google 10,000 English" list (first20hours/google-10000-english),
   frequency-ordered from Google's Trillion Word Corpus.
2. Attaches a concise definition to each word, primarily from WordNet
   (Princeton, via NLTK): the synset whose lemma for this word has the
   highest SemCor frequency count, i.e. the most common sense.
   Words WordNet does not cover (mostly function words like "the",
   "of", "and") are fetched from the free Dictionary API
   (https://dictionaryapi.dev, Wiktionary data), one request at a time
   with a delay, then the Wiktionary REST endpoint. Anything still
   missing is written to scripts/missing.json for a curated entry in
   scripts/fallbacks.json; re-run to pick those up.
3. Writes the compiled-in dataset to src/wordmaster/data/words.cljs
   (strict rank order 1..1000) and an intermediate JSON copy to
   scripts/words.json. Re-running resumes from scripts/words.json:
   words that already have a meaning are not re-fetched.

WordNet setup (one-off, NLTK's own downloader is blocked by the
egress proxy, so fetch the corpus zip directly):
  python3 -m venv /tmp/wordnet-venv
  /tmp/wordnet-venv/bin/pip install nltk
  mkdir -p /tmp/nltk_data/corpora && chmod 700 /tmp/nltk_data /tmp/nltk_data/corpora
  curl -L -o /tmp/nltk_data/corpora/wordnet.zip \\
    https://raw.githubusercontent.com/nltk/nltk_data/gh-pages/packages/corpora/wordnet.zip
  chmod 600 /tmp/nltk_data/corpora/wordnet.zip
  NLTK_DATA=/tmp/nltk_data /tmp/wordnet-venv/bin/python scripts/build_words.py

A copy of the corpus zip is kept in data-raw/wordnet.zip for
reproducibility.
"""

import json
import os
import re
import sys
import time
import urllib.request

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
LIST = os.path.join(ROOT, "data-raw", "20k.txt")
OUT_JSON = os.path.join(ROOT, "scripts", "words.json")
OUT_CLJS = os.path.join(ROOT, "src", "wordmaster", "data", "words.cljs")
FALLBACKS = os.path.join(ROOT, "scripts", "fallbacks.json")
MISSING = os.path.join(ROOT, "scripts", "missing.json")
COUNT = 1000

META_DEF = re.compile(
    r"^(the name of|the letter|abbreviation of|plural of|past tense|"
    r"past participle|present participle|third-person singular|"
    r"alternative (spelling|form)|obsolete form|misspelling|"
    r"contraction of|initialism of|acronym of)",
    re.I,
)


def clean(text):
    text = re.sub(r"<[^>]*>", "", text or "")
    return re.sub(r"\s+", " ", text).strip()


def shorten(defn, max_len=180):
    if len(defn) <= max_len:
        return defn
    cut = defn.rfind(" ", 0, max_len)
    return defn[: cut if cut > 40 else max_len].rstrip() + "…"


def wordnet_definition(word):
    from nltk.corpus import wordnet as wn

    synsets = wn.synsets(word)
    if not synsets:
        return None
    w = word.lower()

    def freq(s):
        return max(
            (lemma.count() for lemma in s.lemmas() if lemma.name().lower() == w),
            default=0,
        )

    best = max(synsets, key=freq)
    gloss = best.definition().split(";")[0].strip().strip('"')
    return shorten(clean(gloss)) or None


def fetch_json(url, tries=5):
    last = None
    for i in range(tries):
        req = urllib.request.Request(url, headers={"User-Agent": "WordMaster-build-script/1.0"})
        try:
            with urllib.request.urlopen(req, timeout=20) as res:
                return json.load(res)
        except urllib.error.HTTPError as e:
            if e.code == 404:
                return None
            if e.code == 429:
                wait = int(e.headers.get("Retry-After", "10"))
                print(f"  429 for {url}, waiting {wait}s (try {i+1}/{tries})")
                time.sleep(wait)
                last = e
                continue
            last = e
            time.sleep(2 * (i + 1))
        except Exception as e:  # noqa: BLE001 - network flakiness, retry
            last = e
            time.sleep(2 * (i + 1))
    print(f"  giving up on {url}: {last}")
    return None


def pick_definition(meanings):
    all_defs = []
    for m in meanings or []:
        for d in m.get("definitions") or []:
            text = clean(d.get("definition"))
            if text:
                all_defs.append(text)
    if not all_defs:
        return None
    return shorten(next((d for d in all_defs if not META_DEF.match(d)), all_defs[0]))


def api_definition(word):
    data = fetch_json(
        "https://api.dictionaryapi.dev/api/v2/entries/en/" + urllib.parse.quote(word)
    )
    if isinstance(data, list):
        meanings = [m for e in data for m in e.get("meanings") or []]
        found = pick_definition(meanings)
        if found:
            return found, "dictionaryapi"
    data = fetch_json(
        "https://en.wiktionary.org/api/rest_v1/page/definition/" + urllib.parse.quote(word)
    )
    if isinstance(data, dict):
        defs = [
            clean(d.get("definition"))
            for entry in data.get("en") or []
            for d in entry.get("definitions") or []
        ]
        defs = [d for d in defs if d]
        if defs:
            return shorten(next((d for d in defs if not META_DEF.match(d)), defs[0])), "wiktionary"
    return None, None


def esc_clj(s):
    return s.replace("\\", "\\\\").replace('"', '\\"')


def main():
    with open(LIST, encoding="utf-8") as f:
        words = [line.strip() for line in f if line.strip()][:COUNT]
    assert len(words) == COUNT, f"list has only {len(words)} words"
    assert len(set(words)) == len(words), "duplicate words in list"

    fallbacks = json.load(open(FALLBACKS)) if os.path.exists(FALLBACKS) else {}
    previous = {}
    if os.path.exists(OUT_JSON):
        for r in json.load(open(OUT_JSON)):
            if r.get("meaning"):
                previous[r["word"]] = r

    results = []
    by_source = {}
    for i, word in enumerate(words):
        if word in fallbacks:
            # Curated overrides always win, even over a previous run.
            r = {"word": word, "rank": i + 1, "meaning": fallbacks[word], "source": "fallback"}
        elif word in previous:
            r = previous[word]
        else:
            meaning = wordnet_definition(word)
            if meaning:
                r = {"word": word, "rank": i + 1, "meaning": meaning, "source": "wordnet"}
            else:
                # Function words etc.: one gentle API call, then wait.
                meaning, source = api_definition(word)
                r = {"word": word, "rank": i + 1, "meaning": meaning, "source": source}
                time.sleep(0.8)
        r["rank"] = i + 1
        results.append(r)
        by_source[r["source"] or "missing"] = by_source.get(r["source"] or "missing", 0) + 1
        if (i + 1) % 200 == 0:
            print(f"  {i+1}/{COUNT} done, sources so far: {by_source}")

    missing = [r["word"] for r in results if not r["meaning"]]
    with open(MISSING, "w", encoding="utf-8") as f:
        json.dump(missing, f, indent=2)
        f.write("\n")
    with open(OUT_JSON, "w", encoding="utf-8") as f:
        json.dump(results, f, indent=2, ensure_ascii=False)
        f.write("\n")
    print("sources:", by_source)
    print(f"missing: {len(missing)} {missing[:50]}")
    if missing:
        print(f"\n{len(missing)} words still lack a meaning — add them to "
              "scripts/fallbacks.json and re-run. words.cljs NOT written.")
        sys.exit(1)

    entries = "\n".join(
        f'   {{:id {r["rank"]} :word "{esc_clj(r["word"])}" '
        f':meaning "{esc_clj(r["meaning"])}" :rank {r["rank"]}}}'
        for r in results
    )
    cljs = f"""(ns wordmaster.data.words
  "The 1000 most frequent English words, in strict frequency order.

   Source: Google 10,000 English list (first20hours/google-10000-english),
   frequency-ordered from Google's Trillion Word Corpus, truncated to
   the first 1000. Meanings primarily from WordNet (Princeton, via NLTK,
   most-frequent sense by SemCor lemma count); words WordNet does not
   cover (function words) come from the free Dictionary API
   (dictionaryapi.dev, Wiktionary data) or curated fallbacks in
   scripts/fallbacks.json. Regenerate with:
   NLTK_DATA=/tmp/nltk_data /tmp/wordnet-venv/bin/python scripts/build_words.py")

(def words
  [{entries}
   ])
"""
    os.makedirs(os.path.dirname(OUT_CLJS), exist_ok=True)
    with open(OUT_CLJS, "w", encoding="utf-8") as f:
        f.write(cljs)
    print(f"wrote {OUT_CLJS} ({len(results)} words)")


if __name__ == "__main__":
    main()
