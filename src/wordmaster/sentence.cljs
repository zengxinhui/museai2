(ns wordmaster.sentence
  "Sentence of the Day (Option C, template-based, offline).
   Deterministic from the calendar date: the same mastered words
   produce the same sentence all day; 'Another' cycles to the next
   rotation. Sentences are built only from mastered words plus fixed
   template glue — no invented vocabulary beyond the templates.

   A sentence is a vector of segments: plain strings or word maps.
   Word segments render highlighted, with their meaning shown below.")

(defn day-number
  "Days since the Unix epoch for a local calendar date."
  ([] (day-number (js/Date.)))
  ([d]
   (let [midnight (js/Date. (.getFullYear d) (.getMonth d) (.getDate d))]
     (quot (.getTime midnight) 86400000))))

(defn- pick [words day-num offset n]
  (when (seq words)
    (let [start (mod (+ day-num offset) (count words))]
      (vec (take n (drop start (cycle words)))))))

(defn- one-word-templates [w]
  [(str "Today's word is ")
   w
   (str ". It means “" (:meaning w) "”. Try to use it in a sentence of your own today.")
   ])

(defn- two-word-templates [[a b]]
  [[(str "A: Do you know what “") a (str "” means?")
    (str " B: Yes — it means “" (:meaning a) "”.")
    (str " A: And what about “") b (str "”?")
    (str " B: That means “" (:meaning b) "”.")]
   [(str "Remember these two words: ") a (str " and ") b
    (str ". “" (:word a) "” means “" (:meaning a) "”, and “"
         (:word b) "” means “" (:meaning b) "”.")]
   [(str "Today, practise ") a (str " and ") b
    (str ". Say each one out loud, then write a sentence using both.")]])

(defn- three-word-templates [[a b c]]
  [[(str "Today you are practising: ") a (str ", ") b (str " and ") c (str ".")
    (str " “" (:word a) "” means “" (:meaning a) "”; “"
         (:word b) "” means “" (:meaning b) "”; “"
         (:word c) "” means “" (:meaning c) "”.")]
   [(str "A: I learned three new words today: ") a (str ", ") b (str " and ") c
    (str ". B: Well done! Keep using them and they will stay with you.")]])

(defn sentence-of-the-day
  "Returns {:segments [...] :words [...]} for the given mastered
   words (rank order), or nil when there are no mastered words.
   `offset` cycles the rotation for the 'Another' button."
  [mastered-words day-num offset]
  (let [words (vec mastered-words)]
    (when (seq words)
      (cond
        (= 1 (count words))
        (let [ws (pick words day-num offset 1)]
          {:segments (one-word-templates (first ws)) :words ws})

        (= 2 (count words))
        (let [ws (pick words day-num offset 2)
              templates (two-word-templates ws)
              t (nth templates (mod (+ day-num offset) (count templates)))]
          {:segments t :words ws})

        :else
        (let [n (if (zero? (mod (+ day-num offset) 3)) 3 2)
              ws (pick words day-num offset n)]
          (if (= 3 n)
            (let [templates (three-word-templates ws)
                  t (nth templates (mod (+ day-num offset) (count templates)))]
              {:segments t :words ws})
            (let [templates (two-word-templates ws)
                  t (nth templates (mod (+ day-num offset) (count templates)))]
              {:segments t :words ws})))))))
