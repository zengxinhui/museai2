(ns wordmaster.state
  "Pure SRS logic: session queue building and pass/fail transitions.
   Kept free of atoms and side effects so it is unit-testable.
   Intervals: 1, 2, 4, 8, 16, 32 days; 32 is the cap; a failed
   review resets to 1 day.")

(def day-ms 86400000)
(def max-interval 32)
(def session-cap 10)

(defn new-word
  "A fresh word map, as stored in the static dataset."
  [id word meaning rank]
  {:id id :word word :meaning meaning :rank rank
   :state :new :review-days 0 :next-review-date nil})

(defn due?
  "A mastered word is due when its next review date has arrived."
  [word now]
  (and (= :mastered (:state word))
       (some? (:next-review-date word))
       (<= (:next-review-date word) now)))

(defn due-words
  "Mastered words due for review, earliest review date first,
   ties broken by frequency rank."
  [words now]
  (->> words
       (filter #(due? % now))
       (sort-by (juxt :next-review-date :rank))))

(defn new-words
  "Words not yet mastered, in strict frequency-rank order."
  [words]
  (filter #(= :new (:state %)) words))

(defn build-session-queue
  "The session queue: due reviews first, then new words in strict
   rank order, at most `session-cap` (10) words in total.
   Due > 10 means 10 due and no new words that session."
  [words now]
  (let [due (due-words words now)
        n-new (max 0 (- session-cap (count due)))]
    (vec (concat (take session-cap due)
                 (take n-new (new-words words))))))

(defn handle-answer
  "Apply a quiz answer to one word and return the updated word map.
   - new + pass      -> :mastered, interval 1, due tomorrow
   - mastered + pass -> interval doubled, capped at 32, due accordingly
   - mastered + fail -> interval reset to 1, due tomorrow, stays mastered
   - new + fail      -> unchanged (must pass before entering mastery)"
  [word passed? now]
  (if passed?
    (if (= :new (:state word))
      (assoc word :state :mastered
                  :review-days 1
                  :next-review-date (+ now day-ms))
      (let [days (min max-interval (* 2 (:review-days word 1)))]
        (assoc word :review-days days
                    :next-review-date (+ now (* days day-ms)))))
    (if (= :mastered (:state word))
      (assoc word :review-days 1
                  :next-review-date (+ now day-ms))
      word)))

(defn counts
  "Summary counts for the home / stats views."
  [words now]
  {:total (count words)
   :mastered (count (filter #(= :mastered (:state %)) words))
   :new (count (filter #(= :new (:state %)) words))
   :due (count (due-words words now))})

(defn upcoming
  "Map of yyyy-mm-dd -> count of mastered words due on that day,
   for the next `n-days` days starting today (local dates)."
  [words now n-days]
  (let [fmt (fn [ms]
              (let [d (js/Date. ms)]
                (str (.getFullYear d) "-"
                     (let [m (inc (.getMonth d))] (if (< m 10) (str "0" m) (str m))) "-"
                     (let [day (.getDate d)] (if (< day 10) (str "0" day) (str day))))))
        start (let [d (js/Date. now)]
                (.setHours d 0 0 0 0)
                (.getTime d))
        days (into {} (map (fn [i] [(fmt (+ start (* i day-ms))) 0])
                           (range n-days)))]
    (reduce (fn [acc w]
              (if (and (= :mastered (:state w)) (:next-review-date w))
                (let [k (fmt (:next-review-date w))]
                  (if (contains? acc k) (update acc k inc) acc))
                acc))
            days
            words)))
