# WordMaster — the 1,000 most frequent English words

A single-page app (ClojureScript + shadow-cljs + Reagent, React 19) that
teaches the 1,000 most frequent English words in strict frequency order.
Each word goes from `new` to `mastered` by passing a 4-choice quiz; mastered
words enter a spaced-repetition schedule (1, 2, 4, 8, 16, 32 days, 32-day
cap). A Sentence of the Day is built from words you have mastered. All
progress lives in the browser's `localStorage` — there is no server and no
account.

## Run it

```sh
npm install
scripts/watch.sh watch app    # dev build + server at http://localhost:3000
scripts/watch.sh release app  # production build (advanced compilation)
scripts/watch.sh compile test # unit tests, then: node public/js/test.js
```

**Why `scripts/watch.sh` instead of plain `npx shadow-cljs watch app`?**
In this VM, shadow-cljs resolves its Maven dependencies through an old
wagon that cannot use the egress proxy (direct TLS to repo1.maven.org
fails with "Unsupported or unrecognized SSL message"). The dependencies
are all cached in `~/.m2/repository` (populated by
`scripts/resolve_maven.py`, which downloads via Python urllib through the
proxy), so the wrapper runs the compiler directly with a hand-built
classpath — the exact equivalent of `npx shadow-cljs watch app` once the
classpath is supplied. On a machine with normal Maven access, plain
`npx shadow-cljs watch app` works.

Java is not on `PATH` by default here; the wrapper sets
`JAVA_HOME=/home/hatch/workspace/tools/jdk-21.0.13+9`.

### Pre-commit hook: don't commit a dev build of main.js

`public/js/main.js` is tracked in git, but only as a compiled
release build (advanced compilation, ~1.1 MB). The dev server
overwrites it with an unoptimised dev build (5+ MB), which must
never be committed.

`scripts/hooks/pre-commit` enforces this: on `git commit`, if
`public/js/main.js` is staged, the hook rejects the commit when
the staged copy looks like a dev build — it contains
`var COMPILED = false;`, or it is larger than 3 MB. Commits that
don't touch `main.js`, and staged release builds, pass through.

The hook is tracked in the repo, but git hooks don't travel with
a clone — enable it once per checkout with:

```sh
git config core.hooksPath scripts/hooks
```

To commit a new release build instead:

```sh
scripts/watch.sh release app
git add public/js/main.js
git commit
```

Note: that `git add` prints an "ignored paths" hint and exits 1
because `public/js/` is gitignored — the tracked file is still
staged; check with `git status`. To drop an accidental dev build
from the worktree instead: `git checkout -- public/js/main.js`.
(`git commit --no-verify` bypasses the hook; only use it if you
mean it.)

## How learning works (SRS rules)

- **Session queue:** at most 10 words per session — due reviews first
  (mastered words whose next-review date has arrived, earliest first),
  then new words in strict frequency-rank order. More than 10 due means
  10 reviews and no new words that session.
- **Quiz:** the word is shown, "What does this mean?", 4 meanings to
  choose from (the correct one + 3 random distractors from the 1,000).
  Immediate green/red feedback, auto-advance after ~800 ms, no going back.
- **Pass:** a new word becomes `mastered` (due again tomorrow); a
  mastered word's interval doubles (1 → 2 → 4 → 8 → 16 → 32, capped).
- **Fail:** a new word stays `new`; a mastered word stays `mastered` but
  its interval resets to 1 day (due tomorrow).
- **Saving:** progress is written to `localStorage` (`wordmaster-words-v1`)
  after every answer, so closing the tab mid-session loses nothing.

## Sentence of the Day

Real sentences, not templates: the app selects from a bank of 2,475
sentences from **Tatoeba** (English sentences, CC-BY — see
`scripts/build_sentences.py`): short natural sentences (3–15 words,
up to 3 per word) plus long dense ones (16–30 words, at least 8
distinct top-1000 words), all containing top-1000 words. Selection
maximises the mastered words used: every sentence is scored by how
many distinct mastered words it contains, and the pick comes from
the top tier (the best score, or one below it, topped up to at least
5 entries so there is always something to cycle through). A single
real sentence cannot hold 20 distinct mastered words until many
words are mastered — with the first 200 words mastered the pick
uses about 19, with the first 50 about 15, with the first 5 all 5;
the view shows "Uses N mastered words" so the number visibly grows
as more words are mastered. If no sentence contains any mastered
word it says "Master a word to unlock sentences!". Selection is
deterministic from the calendar date (same words → same sentence all
day), with an "Another sentence" button to cycle through the tier.
Mastered words are highlighted in place, with their meanings listed
below, and the view notes the Tatoeba (CC-BY) source. Coverage:
997/1000 words have at least one sentence; the 3 uncovered words
(`rss`, `feb`, `jul` — see `scripts/uncovered_words.json`) are
abbreviations that do not occur as standalone words in natural
sentences. One demonstrably garbled Tatoeba entry is excluded by a
small denylist in `scripts/build_sentences.py`.

Regenerate with `python3 scripts/build_sentences.py` (downloads the
Tatoeba per-language English export into `data-raw/`).

## Data: the 1,000 words

Source: the **Google 10,000 English** list
([first20hours/google-10000-english](https://github.com/first20hours/google-10000-english),
frequency-ordered from Google's Trillion Word Corpus), truncated to the
first 1,000 — `data-raw/20k.txt`.

Meanings (`scripts/build_words.py`, reproducible):

1. Primarily **WordNet** (Princeton, via NLTK): the synset whose lemma
   for the word has the highest SemCor frequency count — i.e. the most
   common sense. Corpus zip kept in `data-raw/wordnet.zip`.
2. Words WordNet does not cover (mostly function words) come from the
   free **Dictionary API** (dictionaryapi.dev, Wiktionary data), then the
   Wiktionary REST endpoint, one gentle request at a time.
3. **135 curated fallbacks** in `scripts/fallbacks.json` — hand-written
   after spot-checking the first 200 words. WordNet's most-frequent
   sense is often wrong for short words (e.g. `i` → iodine, `or` →
   Oregon, `a` → ångström, `us` → the USA), so the most frequent
   function words, single letters, and abbreviations are defined by
   hand. Re-running the script applies `fallbacks.json` first.

Regenerate with:

```sh
NLTK_DATA=/tmp/nltk_data /tmp/wordnet-venv/bin/python scripts/build_words.py
```

(see the script header for the one-off WordNet setup). Verified:
1,000 words, ranks 1–1000 unique and in order, no duplicate words,
every word with a non-empty meaning, 0 missing.

## Project structure

```
shadow-cljs.edn            builds: :app (browser) and :test (node-test)
public/index.html          shell + CSS
src/wordmaster/
  core.cljs                Reagent views: Home, Quiz, Sentence, Stats
  state.cljs               pure SRS logic (queue builder, pass/fail)
  storage.cljs             localStorage save/load, merge by :id
  quiz.cljs                4-choice question generation
  sentence.cljs            Sentence of the Day: selects real Tatoeba
                           sentences containing mastered words
  data/words.cljs          the 1,000 words, compiled in
  data/sentences.cljs      2,475 Tatoeba sentences, compiled in
test/wordmaster/
  srs_test.cljs            cljs.test: the 8 critical spec cases + extras
scripts/
  build_words.py           word list + meanings pipeline
  build_sentences.py       Tatoeba sentence bank pipeline
  uncovered_words.json     words with no bank sentence (rss, feb, jul)
  sentences.json           sentence bank (intermediate JSON)
  fallbacks.json           curated definitions (135)
  resolve_maven.py         populate ~/.m2 via proxy-friendly downloads
  classpath.py / watch.sh  run shadow-cljs without Maven resolution
```

## Tests

`scripts/watch.sh compile test && node public/js/test.js` — 16 tests,
61 assertions, 0 failures. Covers: new pass/fail, mastered pass
doubling incl. the 32-day cap, mastered fail reset, and the three queue
cases (5 due + 10 new → 10 with all due; 0 due + 10 new → 10 in rank
order; 12 due + 5 new → 10 due), plus question shape, sentence
determinism (real Tatoeba sentence, 2+ mastered words in context,
segments reconstruct the text exactly, 1-word fallback, uncovered
word → nil), sentence maximisation (50 mastered words → ≥10 used,
200 → ≥15 used, varies across days, offset cycles, top tier within 2
of the best score), sentence-bank coverage (997/1000 words, long
sentences present, some sentence holds 20+ distinct top-1000 words,
every listed word occurs in its sentence), and dataset sanity (count,
ranks, uniqueness, meanings).
