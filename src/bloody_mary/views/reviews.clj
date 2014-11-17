(ns bloody-mary.views.reviews
  (:use [hiccup.core :only (h)]
        [hiccup.form :as form])
  (:require [bloody-mary.views.layout :as layout]))

(defn total-score [review]
  (+
      (:rim_score review)
      (:garnish_score review)
      (:spice_score review)
      (:booze_score review)
      (:mouthfeel_score review)))

(defn percent-score [review]
  (* 4 (total-score review)))

(defn score-card [percent]
  [:div {:class "percent-score-holder"}
      [:div {:class "percent-score"
             :style (str "width:" percent "%")} "&nbsp;"]
      [:div {:class "percent-score-mask"}]])

(defn score-card-for-metric [metric]
  [:div {:class "metric"}
    [:h4 (:text metric)]
    [:h5 (:description metric)]
    (score-card (* 20 (:score metric)))])

(defn all-score-cards [review]
  (map score-card-for-metric [
    {:text "Rim" :description "Keep turning that glass" :score (:rim_score review)}
    {:text "Garnish" :description "How pickled are the pickles?" :score (:garnish_score review)}
    {:text "Spice" :description "Grams of Horseradish per Glass" :score (:spice_score review)}
    {:text "Booze" :description "An objective measure of BAC" :score (:booze_score review)}
    {:text "Mouthfeel" :description "The thicker & chunkier the better" :score (:mouthfeel_score review)}
  ]))

(defn display-review [review]
  [:div {:class "col-sm-3"}
    [:a {:href (h (:slug review))}
      [:img {:src (h (:photo_url review))
             :class "img-responsive mary"}]]
    [:h4 {:class "venue"}
      [:a {:href (h (:slug review))}
        (h (:venue review))]]
    [:h5 [:a {:href (:slug review)} (h (:title review))]]
    (score-card (percent-score review))])

(defn display-reviews [reviews]
  [:div {:class "row"}
    (map (fn [review] (display-review review)) reviews)])

(defn index [reviews]
  (layout/common "Oakland Bloody Mary"
    (display-reviews reviews)))

(defn show [review]
  (layout/common (h (str (:venue review) " Bloody Mary"))
    [:div {:class "row"}
      [:div {:class "col-sm-3"}
        [:img {:src (h (:photo_url review))
               :class "img-responsive"}]]
      [:div {:class "col-sm-6"}
        [:h1 (h (:venue review))]
        [:h3 (h (:title review))]
        (:story review)]
      [:div {:class "col-sm-3"}
        (all-score-cards review)]]))

(defn form-group [name text field]
  [:div {:class "form-group"}
    (label {:class "col-sm-2 control-label"} name text)
    [:div {:class "col-sm-10"} field ]])

(defn form-field [name text]
  (form-group name text
    (text-field {:class "form-control"} name)))

(defn form-radio-button [name score]
  (label {:class "checkbox-inline"} name
    (radio-button {:id (str name "_" score) :name name :value score} name)))

(defn form-radio-group [name text]
  [:div {:class "form-group"}
    (label {:class "col-sm-2 control-label"} name text)
    (map #(form-radio-button name %) [1 2 3 4 5])])

(defn form-area [name text]
  (form-group name text
    (text-area {:class "form-control" :rows "10"} name)))

(defn review-form []
  [:div {:class "row"}
    [:div {:class "col-md-6"}
      [:div {:class "panel panel-success"}
        [:div {:class "panel-heading"}
          [:h3 {:class "panel-title"} "Post a Review"]]
      [:div {:class "panel-body"}
        (form-to {:class "form-horizontal"} [:post "/"]
          (form-field "title" "Title")
          (form-field "venue" "Venue")
          (form-field "photo_url" "Photo")
          (form-radio-group "rim_score" "Rim")
          (form-radio-group "garnish_score" "Garnish")
          (form-radio-group "spice_score" "Spice")
          (form-radio-group "booze_score" "Booze")
          (form-radio-group "mouthfeel_score" "Mouthfeel")
          (form-area "story" "Story")
          (submit-button {:class "btn btn-lg btn-success pull-right"} "Submit"))
        [:div {:class "clearfix"}]]]]
    [:div {:class "col-md-6"}
      [:img {:src "https://s3.amazonaws.com/bloody-mary/submit.jpeg" :class "img-rounded"}]]])


(defn submit []
  (layout/common "Oakland Bloody Mary"
    (review-form)))
