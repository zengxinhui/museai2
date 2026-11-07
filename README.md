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

Template-based (Option C in the project plan) and fully offline:
sentences are built from your mastered words plus fixed template glue,
deterministic from the calendar date (same words → same sentence all
day), with an "Another sentence" button to cycle. Mastered words are
highlighted, with their meanings listed below. With no mastered words
yet it says "Master a word to unlock sentences!".

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
  sentence.cljs            Sentence of the Day templates
  data/words.cljs          the 1,000 words, compiled in
test/wordmaster/
  srs_test.cljs            cljs.test: the 8 critical spec cases + extras
scripts/
  build_words.py           word list + meanings pipeline
  fallbacks.json           curated definitions (135)
  resolve_maven.py         populate ~/.m2 via proxy-friendly downloads
  classpath.py / watch.sh  run shadow-cljs without Maven resolution
```

## Tests

`scripts/watch.sh compile test && node public/js/test.js` — 13 tests,
39 assertions, 0 failures. Covers: new pass/fail, mastered pass
doubling incl. the 32-day cap, mastered fail reset, and the three queue
cases (5 due + 10 new → 10 with all due; 0 due + 10 new → 10 in rank
order; 12 due + 5 new → 10 due), plus question shape, sentence
determinism, and dataset sanity (count, ranks, uniqueness, meanings).
