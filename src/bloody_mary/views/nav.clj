(ns bloody-mary.views.nav
    (:use [hiccup.core :only (html)]))

(defn container [kind & bar]
  [:div {:id (str kind "-navbar") :class "navbar"}
    [:div {:class "container"} bar]])

(defn mobile-logo []
  [:div {:class "simple-logo" }
    [:a {:href "/"}
      [:img {:src "../simple-logo.png" }]]])

(defn mobile-home []
  [:a {:id "home-btn" :href "/" :type "button" :class "navbar-toggle collapsed"}
    [:span {:class "sr-only" } "Toggle navigation"]
    [:span {:class "glyphicon glyphicon-align-justify" :aria-hidden "true"}]])

(defn mobile [home?]
  (container "mobile"
    [:div {:class "navbar-header"}
      (if (not home?) (mobile-home))
      (mobile-logo)]))

(defn desktop-logo []
  [:a {:class "navbar-brand" :href "/"}
    [:img { :class "logo" :src "../logo.png"}]])

(defn desktop-home []
  [:ul {:class "nav navbar-nav navbar-right"}
    [:li
      [:a {:id "home-btn" :href "/" :type "button" :class "btn btn-default navbar-btn navbar-right" }
        [:span {:class "glyphicon glyphicon-th" :aria-hidden "true"}]]]])

(defn desktop [home?]
  (container "desktop"
    [:div {:class "navbar-header"} (desktop-logo)]
    (if (not home?) (desktop-home))))
