(ns wordmaster.core
  "WordMaster: learn the 1000 most frequent English words.
   Single Reagent atom `db` holds the words (static list + saved
   progress merged in); a watch persists progress to localStorage
   after every change. `ui` holds transient view / session state."
  (:require [reagent.core :as r]
            [reagent.dom.client :as rdom-client]
            [wordmaster.data.words :as data]
            [wordmaster.state :as st]
            [wordmaster.storage :as store]
            [wordmaster.quiz :as quiz]
            [wordmaster.sentence :as sentence]))

;; ---- State ---------------------------------------------------------------

(defonce db (r/atom {:words (store/load-words)}))
(defonce ui (r/atom {:view :home :session nil :sentence-offset 0}))

(add-watch db :persist
           (fn [_ _ _ new-db] (store/save-words! (:words new-db))))

(defn words [] (:words @db))
(defn mastered-words [] (filter #(= :mastered (:state %)) (words)))

;; ---- Session -------------------------------------------------------------

(defn start-session! []
  (let [queue (st/build-session-queue (words) (js/Date.now))]
    (when (seq queue)
      (swap! ui assoc :view :quiz
             :session {:id (str (js/Date.now) "-" (rand-int 1000000))
                       :questions (quiz/make-session-questions queue)
                       :index 0 :picked nil :correct? nil
                       :passed 0 :failed 0 :newly-mastered 0 :done? false}))))

(defn- advance! [session-id]
  ;; Only advance if the user is still in the same session (they may
  ;; have left the quiz while the 800 ms feedback timer was running).
  (when (= session-id (get-in @ui [:session :id]))
    (swap! ui update :session
           (fn [s]
             (if (>= (inc (:index s)) (count (:questions s)))
               (assoc s :done? true :picked nil :correct? nil)
               (-> s (update :index inc) (assoc :picked nil :correct? nil)))))))

(defn answer! [option]
  (let [s (:session @ui)
        q (nth (:questions s) (:index s))
        word (:word q)]
    (when (nil? (:picked s))
      (let [passed? (= option (:correct q))
            was-new? (= :new (:state word))
            now (js/Date.now)]
        ;; Save the result immediately: progress survives a closed tab.
        (swap! db update :words
               (fn [ws]
                 (mapv (fn [w]
                         (if (= (:id w) (:id word))
                           (st/handle-answer w passed? now)
                           w))
                       ws)))
        (swap! ui update :session
               (fn [s]
                 (-> s
                     (assoc :picked option :correct? passed?)
                     (update (if passed? :passed :failed) inc)
                     (update :newly-mastered + (if (and passed? was-new?) 1 0)))))
        (js/setTimeout #(advance! (:id s)) 800)))))

;; ---- Views ---------------------------------------------------------------

(defn nav []
  (let [view (:view @ui)]
    [:nav
     (for [[k label] [[:home "Home"] [:sentence "Sentence of the Day"] [:stats "Stats"]]]
       ^{:key k}
       [:button {:class (when (= view k) "active")
                 :on-click #(swap! ui assoc :view k)}
        label])]))

(defn home-view []
  (let [now (js/Date.now)
        c (st/counts (words) now)
        queue (st/build-session-queue (words) now)]
    [:div
     [:div.card
      [:h2 "Learn the 1,000 most frequent English words"]
      [:p "Words are introduced in strict frequency order. Each session quizzes at most 10 words — due reviews first, then new words."]
      [:p (str "Mastered: " (:mastered c) " of " (:total c)
               " · New words left: " (:new c)
               " · Due for review today: " (:due c))]
      (cond
        (seq queue)
        [:div
         [:p (str "Next session: " (count queue) " words ("
                   (count (filter #(= :mastered (:state %)) queue)) " review, "
                   (count (filter #(= :new (:state %)) queue)) " new).")]
         [:button {:on-click start-session!} "Start session"]]

        (and (zero? (:due c)) (zero? (:new c)))
        [:p "All 1,000 words mastered and nothing due — come back tomorrow!"]

        :else
        [:p "No session available right now."])]
     [:div.card
      [:h3 "How it works"]
      [:p "Pass a quiz to master a word. Mastered words come back after 1, 2, 4, 8, 16, then 32 days (32 is the longest gap). Fail a review and the word is due again tomorrow — it stays mastered."]
      [:p "The Sentence of the Day uses words you have already mastered."]]]))

(defn quiz-view []
  (let [s (:session @ui)]
    (if (or (nil? s) (empty? (:questions s)))
      [:div.card [:p "No session in progress."] [:button {:on-click #(swap! ui assoc :view :home)} "Back to Home"]]
      (if (:done? s)
        [:div.card
         [:h2 "Session complete!"]
         [:p (str "You passed " (:passed s) " of " (count (:questions s)) " words.")]
         (when (pos? (:newly-mastered s))
           [:p (str "Newly mastered: " (:newly-mastered s) " word(s). They will be due for review tomorrow.")])
         [:p [:button {:on-click #(swap! ui assoc :view :home :session nil)} "Back to Home"]
          " "
          [:button {:on-click #(swap! ui assoc :view :sentence :session nil)} "Sentence of the Day"]]]
        (let [q (nth (:questions s) (:index s))
              n (count (:questions s))
              i (:index s)
              picked (:picked s)]
          [:div.card
           [:div.progress [:div {:style {:width (str (* 100 (/ i n)) "%")}}]]
           [:p (str "Word " (inc i) " of " n)]
           [:p "What does this mean?"]
           [:div.word-prompt (:word (:word q))]
           [:div.options
            (for [opt (:options q)]
              ^{:key opt}
              [:button {:class (str "option"
                                    (cond
                                      (and picked (= opt (:correct q))) " correct"
                                      (and picked (= opt picked)) " wrong"
                                      :else ""))
                        :disabled (some? picked)
                        :on-click #(answer! opt)}
               opt])]
           (when picked
             [:p (if (:correct? s)
                   "Correct!"
                   (str "Not quite — “" (:word (:word q)) "” means “" (:correct q) "”."))
              " Next word coming up…"])])))))

(defn sentence-view []
  (let [mastered (vec (mastered-words))
        day-num (sentence/day-number)
        s (sentence/sentence-of-the-day mastered day-num (:sentence-offset @ui))
        today (.toLocaleDateString (js/Date.) js/undefined #js {:weekday "long" :year "numeric" :month "long" :day "numeric"})]
    [:div.card
     [:h2 "Sentence of the Day"]
     [:p.muted today]
     (if (nil? s)
       [:div
        [:p "Master a word to unlock sentences!"]
        [:p "Finish a quiz session first — sentences here are built from the words you have mastered."]
        [:button {:on-click #(swap! ui assoc :view :home)} "Go to Home"]]
       [:div
        [:p {:style {:font-size "19px" :line-height "1.7"}}
         (for [[idx seg] (map-indexed vector (:segments s))]
           (if (string? seg)
             ^{:key idx} [:<> seg]
             ^{:key idx} [:span.hl (:word seg)]))]
        [:div {:style {:margin-top "16px"}}
         [:h3 "Words used"]
         [:ul
          (for [w (:words s)]
            ^{:key (:id w)}
            [:li [:b (:word w)] " — " (:meaning w)])]]
        [:p [:button {:on-click #(swap! ui update :sentence-offset inc)} "Another sentence"]]
        [:p.muted "Sentences are built from your mastered words using fixed templates — the same words give the same sentence all day."]])]))

(defn stats-view []
  (let [now (js/Date.now)
        c (st/counts (words) now)
        upcoming (st/upcoming (words) now 7)]
    [:div
     [:div.card
      [:h2 "Stats"]
      [:div.stat-grid
       [:div.stat [:b (:mastered c)] "words mastered"]
       [:div.stat [:b (:new c)] "new words left"]
       [:div.stat [:b (:due c)] "due today"]
       [:div.stat [:b (:total c)] "total words"]]]
     [:div.card
      [:h3 "Upcoming reviews (next 7 days)"]
      (if (zero? (:mastered c))
        [:p "No mastered words yet — nothing scheduled."]
        [:table
         [:thead [:tr [:th "Date"] [:th "Due"]]]
         [:tbody
          (for [[day n] upcoming]
            ^{:key day}
            [:tr [:td day] [:td n]])]])]]))

(defn app []
  [:div.wrap
   [:header
    [:h1 "WordMaster"]
    [:p "The 1,000 most frequent English words, in frequency order."]]
   [nav]
   (case (:view @ui)
     :home [home-view]
     :quiz [quiz-view]
     :sentence [sentence-view]
     :stats [stats-view]
     [home-view])])

;; React 19 (via Reagent 2.0.1) has no react-dom/render: the old
;; reagent.dom/render only works with React 18 or older, and silently
;; fails with React 19. Use the create-root API instead, keeping the
;; root in a defonce so a second init (e.g. hot reload) re-renders
;; into the same root instead of creating a second one.
(defonce app-root (atom nil))

(defn ^:export init []
  (let [root (or @app-root
                 (let [r (rdom-client/create-root
                           (.getElementById js/document "app"))]
                   (reset! app-root r)
                   r))]
    (rdom-client/render root [app])))
