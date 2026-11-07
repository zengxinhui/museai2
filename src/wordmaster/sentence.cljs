(ns wordmaster.sentence
  "Sentence of the Day — real sentences from a corpus, never generated.

   The app is offline with no language model, so correctness lives in
   a curated bank: src/wordmaster/data/sentences.cljs, built by
   scripts/build_sentences.py from Tatoeba English sentences (CC-BY).
   The app only *selects* from the bank — it never glues mastered
   words together into templates.

   Selection: a sentence is eligible when it contains at least 2
   distinct mastered words; if none does, sentences with at least 1
   mastered word are used. If no sentence contains any mastered word,
   this returns nil and the view shows the unlock message.
   Deterministic from the calendar date: the same mastered words give
   the same sentence all day; `offset` (\"Another sentence\") cycles
   to the next eligible sentence.

   A sentence is {:text \"...\" :segments [...] :words [...] :source
   \"tatoeba\"}. Segments are plain strings, or mastered-word maps
   with a :surface key holding the word exactly as it appears in the
   sentence (so \"The\" at the start of a sentence stays capitalised);
   joining the segments reconstructs :text exactly."
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

(defn sentence-of-the-day
  "Returns {:text :segments :words :source} for the given mastered
   words (rank order), or nil when no bank sentence contains any of
   them (including when there are no mastered words). `offset` cycles
   to the next eligible sentence for the 'Another' button."
  [mastered-words day-num offset]
  (let [mastered (set (map :word mastered-words))]
    (when (seq mastered)
      (let [n-mastered (fn [s] (count (filter mastered (:words s))))
            eligible2 (filterv #(>= (n-mastered %) 2) bank/sentences)
            eligible1 (filterv #(>= (n-mastered %) 1) bank/sentences)
            eligible (seq (or (seq eligible2) (seq eligible1)))]
        (when eligible
          (let [entry (nth eligible (mod (+ day-num offset) (count eligible)))
                used (filterv #(contains? (set (:words entry)) (:word %))
                              mastered-words)]
            {:text (:text entry)
             :segments (segments-for (:text entry) used)
             :words used
             :source (:source entry)}))))))
