(ns bloody-mary.views.cards
  (:use [hiccup.core :only (h)]))

(defn total-score [review]
  (+
    (:rim_score review)
    (:garnish_score review)
    (:spice_score review)
    (:booze_score review)
    (:mouthfeel_score review)))

(defn show-percent [percent]
  [:div {:class "percent-score-holder"}
    [:div {:class "percent-score"
           :style (str "width:" percent "%")} "&nbsp;"]
    [:div {:class "percent-score-mask"}]])

(defn show-total [review]
  (show-percent (* 4 (total-score review))))

(defn show-metric [metric]
  [:div {:class "metric"}
    [:h4 (:text metric)]
    [:h5 (:description metric)]
    (show-percent (* 20 (:score metric)))])

(defn all [review]
  (map show-metric [
    {:text "Rim" :description "Keep turning that glass" :score (:rim_score review)}
    {:text "Garnish" :description "How pickled are the pickles?" :score (:garnish_score review)}
    {:text "Spice" :description "Grams of Horseradish per Glass" :score (:spice_score review)}
    {:text "Booze" :description "An objective measure of BAC" :score (:booze_score review)}
    {:text "Mouthfeel" :description "The thicker & chunkier the better" :score (:mouthfeel_score review)}]))
