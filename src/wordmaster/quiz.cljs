(ns wordmaster.quiz
  "Quiz question generation: show the word, ask 'What does this
   mean?', offer the correct meaning plus 3 distractors drawn from
   the other words' meanings, shuffled."
  (:require [wordmaster.data.words :as data]))

(defn make-question
  "Build one question for `word` from the full word pool.
   Returns {:word word :options [meaning ...] :correct meaning}.
   Always exactly 4 distinct options, correct one included."
  [word all-words]
  (let [others (->> all-words
                    (remove #(= (:id %) (:id word)))
                    (map :meaning)
                    distinct
                    (remove #(= % (:meaning word))))
        distractors (->> others shuffle (take 3))]
    {:word word
     :correct (:meaning word)
     :options (shuffle (conj (vec distractors) (:meaning word)))}))

(defn make-session-questions
  "One question per queued word, in queue order."
  [queue]
  (mapv #(make-question % data/words) queue))
