(ns bloody-mary.views.layout
  (:use [hiccup.core :only (html)]
        [hiccup.element :only (javascript-tag)]
        [hiccup.page :only (html5 include-css)])
  (:require [bloody-mary.views.nav :as nav]))

(defn common [title & body]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
      [:meta {:name "description" :content "On a lifelong journey to find the best Bloody Mary in Oakland."}]
      [:meta {:name "keywords"
        :content (str title ",Bloody Mary,Oakland,Best Bloody Mary in Oakland,Bloody Mary Review")}]
      [:meta {:name "author" :content "Jake Kring"}]
      [:title title]
      (include-css "/base.css")
      (include-css "/bootstrap.min.css")
      (include-css "http://fonts.googleapis.com/css?family=Arvo")]
    [:body
      (nav/mobile (= title "Oakland Bloody Mary"))
      (nav/desktop (= title "Oakland Bloody Mary"))
      [:div {:id "content" :class "container"} body]
      [:div {:class "footer"}
        [:p
          [:a {:href "https://github.com/jkring/bloody-mary"}
            [:span "⚒  "] " in Oakland"]]]]))

(defn four-oh-four []
  (common "Page Not Found"
    [:div {:class "row"}
      [:div {:class "col-md-6"}
        [:div {:class "jumbotron"}
          [:h1 "Ruh-Roh..."]
          [:p "The page you requested could not be found!"]]]
      [:div {:class "col-md-6"}
        [:img {:src "https://s3.amazonaws.com/bloody-mary/submit.jpeg" :class "img-rounded"}]]]))
