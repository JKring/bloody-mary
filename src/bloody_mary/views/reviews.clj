(ns bloody-mary.views.reviews
  (:use [hiccup.core :only (h)])
  (:require [bloody-mary.views.layout :as layout]
            [bloody-mary.views.cards :as cards]
            [bloody-mary.views.forms :as forms]))

(defn show-review [review]
  [:div {:class "col-sm-3"}
    [:a {:href (h (:slug review))}
      [:img {:src (h (:photo_url review))
             :class "img-responsive mary"}]]
    [:h4 {:class "venue"}
      [:a {:href (h (:slug review))}
        (h (:venue review))]]
    [:h5 [:a {:href (:slug review)} (h (:title review))]]
    (cards/show-total review)])

(defn all [reviews]
  [:div {:class "row"}
    (map (fn [review] (show-review review)) reviews)])

(defn index [reviews]
  (layout/common "Oakland Bloody Mary"
    (all reviews)))

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
        (cards/all review)]]))

(defn submit []
  (layout/common "Submit a Bloody Mary"
    (forms/create-review)))
