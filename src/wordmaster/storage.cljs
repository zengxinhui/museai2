(ns wordmaster.storage
  "localStorage persistence. Only learning progress is stored
   (the 1000-word list itself is static, compiled into the app):
   {id {:state :mastered, :review-days n, :next-review-date ms}}.
   On load it is merged onto the static list by :id, so adding
   fields later never loses saved progress."
  (:require [cljs.reader :as reader]
            [wordmaster.data.words :as data]))

(def storage-key "wordmaster-words-v1")

(defn load-progress []
  (try
    (when-let [raw (.getItem js/localStorage storage-key)]
      (let [db (reader/read-string raw)]
        (when (and (map? db) (= 1 (:version db)) (map? (:progress db)))
          (:progress db))))
    (catch :default _ nil)))

(defn load-words
  "Static word list with any saved progress merged in.
   Every word gets the spec's default fields (:state :new,
   :review-days 0, :next-review-date nil); the static dataset
   itself only carries :id/:word/:meaning/:rank."
  []
  (let [progress (load-progress)]
    (mapv (fn [w]
            (merge {:state :new :review-days 0 :next-review-date nil}
                   w
                   (when-let [p (get progress (:id w))]
                     (select-keys p [:state :review-days :next-review-date]))))
          data/words)))

(defn progress-of [words]
  (into {}
        (comp (filter #(= :mastered (:state %)))
              (map (fn [w] [(:id w) (select-keys w [:state :review-days :next-review-date])])))
        words))

(defn save-words!
  "Persist progress for all mastered words. Silent no-op when
   localStorage is unavailable (e.g. private mode)."
  [words]
  (try
    (.setItem js/localStorage storage-key
              (pr-str {:version 1 :progress (progress-of words)}))
    (catch :default _ nil)))
