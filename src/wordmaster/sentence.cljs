(ns wordmaster.sentence
  "Sentence of the Day — real sentences from a corpus, never generated.

   The app is offline with no language model, so correctness lives in
   a curated bank: src/wordmaster/data/sentences.cljs, built by
   scripts/build_sentences.py from Tatoeba English sentences (CC-BY).
   The app only *selects* from the bank — it never glues mastered
   words together into templates.

   Selection: every bank sentence is scored by how many DISTINCT
   mastered words it contains, and the pick comes from the top tier
   — sentences scoring the maximum, or one below it (at most 25,
   best score first, then text order so the order is stable). When
   that tier has fewer than 5 entries it is topped up with the
   next-best scores, so there are always a few different sentences
   to cycle through. The
   day number picks one entry of that tier, so the sentence is
   stable all day but varies across days; `offset` (\"Another
   sentence\") cycles through the tier. With only a few mastered
   words the best available sentence is shown rather than nothing
   (e.g. with 5 mastered words no real sentence can hold more than
   5 of them); the view shows how many mastered words the sentence
   uses, so that number visibly grows as more words are mastered.
   If no sentence contains any mastered word, this returns nil and
   the view shows the unlock message.

   A sentence is {:text \"...\" :segments [...] :words [...]
   :n-mastered n :source \"tatoeba\"}. Segments are plain strings, or
   mastered-word maps with a :surface key holding the word exactly
   as it appears in the sentence (so \"The\" at the start of a
   sentence stays capitalised); joining the segments reconstructs
   :text exactly. :words are the mastered words used, in rank order;
   :n-mastered is how many distinct mastered words the sentence
   contains."
  (:require [wordmaster.data.sentences :as bank]))

(defn day-number
  "Days since the Unix epoch for a local calendar date."
  ([] (day-number (js/Date.)))
  ([d]
   (let [midnight (js/Date. (.getFullYear d) (.getMonth d) (.getDate d))]
     (quot (.getTime midnight) 86400000))))

;; Contractions whose stem is not what naive suffix-stripping gives.
;; Mirrors scripts/build_sentences.py, so the words a bank sentence
;; claims to contain are exactly the words found in its text.
(def contractions
  {"can't" "can" "won't" "will" "shan't" "shall" "ain't" "be"
   "couldn't" "could" "shouldn't" "should" "wouldn't" "would"
   "don't" "do" "doesn't" "does" "didn't" "did"
   "isn't" "is" "aren't" "are" "wasn't" "was" "weren't" "were"
   "haven't" "have" "hasn't" "has" "hadn't" "had"
   "i'm" "i" "you're" "you" "we're" "we" "they're" "they"
   "he's" "he" "she's" "she" "it's" "it" "that's" "that"
   "i've" "i" "we've" "we" "they've" "they"
   "i'll" "i" "you'll" "you" "we'll" "we" "they'll" "they"
   "he'll" "he" "she'll" "she" "it'll" "it"
   "i'd" "i" "you'd" "you" "he'd" "he" "she'd" "she"
   "we'd" "we" "they'd" "they" "let's" "let"
   "what's" "what" "who's" "who" "where's" "where"
   "when's" "when" "why's" "why" "how's" "how"
   "there's" "there" "here's" "here"})

(defn normalize-token
  "Lowercase a word token and expand contractions / strip possessives:
   \"Don't\" -> \"do\", \"Tom's\" -> \"tom\", \"we're\" -> \"we\"."
  [token]
  (let [t (.toLowerCase token)]
    (if-let [c (get contractions t)]
      c
      (cond
        (.endsWith t "n't") (subs t 0 (- (count t) 3))
        (.endsWith t "'s") (subs t 0 (- (count t) 2))
        (.endsWith t "'re") (subs t 0 (- (count t) 3))
        (.endsWith t "'ve") (subs t 0 (- (count t) 3))
        (.endsWith t "'ll") (subs t 0 (- (count t) 3))
        (.endsWith t "'d") (subs t 0 (- (count t) 2))
        (.endsWith t "'m") (subs t 0 (- (count t) 2))
        :else t))))

(defn text-words
  "Distinct normalised words in a sentence's text, in order."
  [text]
  (distinct (map normalize-token
                 (re-seq #"[A-Za-z]+(?:'[A-Za-z]+)?" text))))

(defn- segments-for
  "Split text into segments: plain strings, plus the mastered-word
   records used, each with :surface set to the word as it appears."
  [text used-words]
  (let [by-word (into {} (map (juxt :word identity) used-words))]
    (mapv (fn [piece]
            (if (re-matches #"[A-Za-z]+(?:'[A-Za-z]+)?" piece)
              (if-let [w (get by-word (normalize-token piece))]
                (assoc w :surface piece)
                piece)
              piece))
          (re-seq #"[A-Za-z]+(?:'[A-Za-z]+)?|[^A-Za-z]+" text))))

(def ^:private top-tier-size 25)
(def ^:private min-tier-size 5)

(defn top-tier
  "Bank sentences scored by distinct mastered words they contain,
   best first (then text order for stability). Returns [entry score]
   pairs for the top tier: scores of the maximum, or one below it,
   capped at `top-tier-size` entries. When that tier has fewer than
   `min-tier-size` entries it is topped up with the next-best
   scores, so there are always a few different sentences to cycle
   through. Empty when nothing scores."
  [mastered-words]
  (let [mastered (set (map :word mastered-words))]
    (when (seq mastered)
      (let [scored (mapv (fn [s]
                           [s (count (filter mastered (:words s)))])
                         bank/sentences)
            best (apply max 0 (map second scored))
            sorted (sort-by (fn [[s n]] [(- n) (:text s)]) scored)
            within1 (filter #(>= (second %) (max 1 (dec best))) sorted)]
        (when (pos? best)
          (vec (if (>= (count within1) min-tier-size)
                 (take top-tier-size within1)
                 (take min-tier-size sorted))))))))

(defn sentence-of-the-day
  "Returns {:text :segments :words :n-mastered :source} for the given
   mastered words (rank order), or nil when no bank sentence contains
   any of them (including when there are no mastered words). The pick
   is the day's entry of the top tier; `offset` cycles through the
   tier for the 'Another' button."
  [mastered-words day-num offset]
  (let [tier (top-tier mastered-words)]
    (when (seq tier)
      (let [[entry _] (nth tier (mod (+ day-num offset) (count tier)))
            used (filterv #(contains? (set (:words entry)) (:word %))
                          mastered-words)]
        {:text (:text entry)
         :segments (segments-for (:text entry) used)
         :words used
         :n-mastered (count used)
         :source (:source entry)}))))
