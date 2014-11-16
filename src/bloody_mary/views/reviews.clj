(ns bloody-mary.views.reviews
  (:use [hiccup.core :only (h)]
        [hiccup.form :as form])
  (:require [bloody-mary.views.layout :as layout]))

(defn display-review [review]
  [:div {:class "col-sm-4"}
        [:h2 {:class "venue"}
          [:a {:href (h (:slug review))} (h (:venue review))]]
        [:a {:href (h (:slug review))}
          [:img {:src (h (:photo_url review)) :class "img-responsive mary"}]]])

(defn display-reviews [reviews]
  [:div {:class "row"}
   (map (fn [review] (display-review review)) reviews)])

(defn index [reviews]
  (layout/common "Oakland Bloody Mary"
    (display-reviews reviews)))

(defn show [review]
  (layout/common (h (:venue review))
    [:div {:class "row"}
      (display-review review)
      [:div {:class "col-sm-8"}
        [:h1 (h (:title review))]
        [:p (h (:story review))]]]))

(defn form-group [name label field]
  [:div {:class "form-group"}
    (form/label {:class "col-sm-2 control-label"} name label)
    [:div {:class "col-sm-10"} field ]])

(defn form-field [name label]
  (form-group name label
    (form/text-field {:class "form-control"} name)))

(defn form-area [name label]
  (form-group name label
    (form/text-area {:class "form-control" :rows "10"} name)))

(defn review-form []
  [:div {:class "row"}
    [:div {:class "col-md-6"}
      [:div {:class "panel panel-success"}
        [:div {:class "panel-heading"}
          [:h3 {:class "panel-title"} "Post a Review"]]
      [:div {:class "panel-body"}
        (form/form-to {:class "form-horizontal"} [:post "/"]
          (form-field "title" "Title")
          (form-field "venue" "Venue")
          (form-field "photo_url" "Photo")
          (form-area "story" "Story")
          (form/submit-button {:class "btn btn-lg btn-success pull-right"} "Submit"))
        [:div {:class "clearfix"}]]]]
    [:div {:class "col-md-6"}
      [:img {:src "https://s3.amazonaws.com/bloody-mary/submit.jpeg" :class "img-rounded"}]]])


(defn submit []
  (layout/common "Oakland Bloody Mary"
    (review-form)))
