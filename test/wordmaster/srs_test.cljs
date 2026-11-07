(ns wordmaster.srs-test
  "Unit tests for the SRS transitions and session queue builder —
   the spec's 8 critical cases, plus question and sentence checks."
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [wordmaster.state :as st]
            [wordmaster.quiz :as quiz]
            [wordmaster.sentence :as sentence]
            [wordmaster.data.words :as data]))

(def now 1790000000000) ;; fixed "now" for deterministic tests
(def day st/day-ms)

(defn w
  "Test word: rank r, state, review-days and next-review-date."
  [r & {:keys [state days next] :or {state :new days 0 next nil}}]
  {:id r :word (str "w" r) :meaning (str "meaning " r) :rank r
   :state state :review-days days :next-review-date next})

(deftest new-pass
  (testing "1. New word passed -> mastered, interval 1, due tomorrow"
    (let [u (st/handle-answer (w 1) true now)]
      (is (= :mastered (:state u)))
      (is (= 1 (:review-days u)))
      (is (= (+ now day) (:next-review-date u))))))

(deftest new-fail
  (testing "2. New word failed -> stays new, unchanged"
    (let [orig (w 1)
          u (st/handle-answer orig false now)]
      (is (= orig u))
      (is (= :new (:state u))))))

(deftest mastered-pass-doubling
  (testing "3. Mastered word passed with interval 4 -> 8"
    (let [u (st/handle-answer (w 1 :state :mastered :days 4 :next (- now day)) true now)]
      (is (= :mastered (:state u)))
      (is (= 8 (:review-days u)))
      (is (= (+ now (* 8 day)) (:next-review-date u))))))

(deftest mastered-pass-cap
  (testing "4. Mastered word passed with interval 32 -> stays 32"
    (let [u (st/handle-answer (w 1 :state :mastered :days 32 :next (- now day)) true now)]
      (is (= 32 (:review-days u)))
      (is (= (+ now (* 32 day)) (:next-review-date u))))))

(deftest mastered-fail
  (testing "5. Mastered word failed -> interval reset to 1, stays mastered"
    (let [u (st/handle-answer (w 1 :state :mastered :days 8 :next (- now day)) false now)]
      (is (= :mastered (:state u)))
      (is (= 1 (:review-days u)))
      (is (= (+ now day) (:next-review-date u))))))

(deftest queue-five-due-ten-new
  (testing "6. 5 due + 10 new -> queue of 10, all 5 due included first"
    (let [words (vec (concat (map #(w % :state :mastered :days 1 :next (- now day)) (range 1 6))
                             (map w (range 6 16))))
          q (st/build-session-queue words now)]
      (is (= 10 (count q)))
      (is (= [1 2 3 4 5 6 7 8 9 10] (map :rank q))))))

(deftest queue-no-due
  (testing "7. 0 due + 10 new -> 10 new in rank order"
    (let [words (vec (map w (range 1 21)))
          q (st/build-session-queue words now)]
      (is (= 10 (count q)))
      (is (= (range 1 11) (map :rank q)))
      (is (every? #(= :new (:state %)) q)))))

(deftest queue-twelve-due
  (testing "8. 12 due + 5 new -> 10 due, 0 new"
    (let [words (vec (concat (map #(w % :state :mastered :days 1 :next (- now (* % day))) (range 1 13))
                             (map w (range 13 18))))
          q (st/build-session-queue words now)]
      (is (= 10 (count q)))
      (is (every? #(= :mastered (:state %)) q))
      ;; due words come out earliest-review-date first
      (is (= [12 11 10 9 8 7 6 5 4 3] (map :rank q))))))

(deftest queue-cap-and-overdue
  (testing "queue never exceeds 10; overdue words are still due"
    (let [words (vec (concat (map #(w % :state :mastered :days 2 :next (- now (* 10 day))) (range 1 4))
                             (map w (range 4 30))))
          q (st/build-session-queue words now)]
      (is (= 10 (count q)))
      (is (= [1 2 3] (map :rank (take 3 q)))))))

(deftest due-words
  (testing "only mastered words with next-review-date <= now are due"
    (let [words [(w 1 :state :mastered :days 1 :next (- now day))
                 (w 2 :state :mastered :days 1 :next (+ now day))
                 (w 3 :state :mastered :days 1 :next nil)
                 (w 4)]]
      (is (= [1] (map :rank (st/due-words words now)))))))

(deftest question-shape
  (testing "quiz question: exactly 4 distinct options, correct one present"
    (let [word (first data/words)
          q (quiz/make-question word data/words)]
      (is (= 4 (count (:options q))))
      (is (= 4 (count (distinct (:options q)))))
      (is (contains? (set (:options q)) (:meaning word)))
      (is (= (:meaning word) (:correct q))))))

(deftest sentence-of-the-day
  (testing "no mastered words -> nil; mastered words -> deterministic sentence using them"
    (is (nil? (sentence/sentence-of-the-day [] 100 0)))
    (let [mastered (mapv #(w % :state :mastered :days 1 :next (+ now day)) (range 1 6))
          a (sentence/sentence-of-the-day mastered 100 0)
          b (sentence/sentence-of-the-day mastered 100 0)
          c (sentence/sentence-of-the-day mastered 100 1)]
      (is (= a b) "same day + offset is deterministic")
      (is (seq (:segments a)))
      (is (seq (:words a)))
      (is (every? #(some (fn [x] (= x %)) (:segments a)) (:words a))
          "every word used appears as a highlighted segment")
      (is (not= a c) "offset cycles to a different sentence"))))

(deftest dataset-sanity
  (testing "dataset: 1000 words, ranks 1..1000 in order, unique, all with meanings"
    (is (= 1000 (count data/words)))
    (is (= (range 1 1001) (map :rank data/words)))
    (is (= 1000 (count (distinct (map :word data/words)))))
    (is (every? #(and (string? (:meaning %)) (pos? (count (:meaning %)))) data/words))
    (is (= "the" (:word (first data/words))))))
