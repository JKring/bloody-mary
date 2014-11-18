(ns bloody-mary.views.forms
  (:use [hiccup.core :only (h)]
        [hiccup.form :as form]))

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

(defn create-review []
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
          (form-radio-group "score_card[rim_score]" "Rim")
          (form-radio-group "score_card[garnish_score]" "Garnish")
          (form-radio-group "score_card[spice_score]" "Spice")
          (form-radio-group "score_card[booze_score]" "Booze")
          (form-radio-group "score_card[mouthfeel_score]" "Mouthfeel")
          (form-area "story" "Story")
          (submit-button {:class "btn btn-lg btn-success pull-right"} "Submit"))
        [:div {:class "clearfix"}]]]]
    [:div {:class "col-md-6"}
      [:img {:src "https://s3.amazonaws.com/bloody-mary/submit.jpeg" :class "img-rounded"}]]])

