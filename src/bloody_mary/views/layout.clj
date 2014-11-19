(ns bloody-mary.views.layout
  (:use [hiccup.core :only (html)]
        [hiccup.element :only (javascript-tag)]
        [hiccup.page :only (html5 include-css)]))

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
    [:div {:id "navbar" :class "navbar"}
      [:div {:class "container"}
        [:div {:class "navbar-header"}
          [:div {:class "simple-logo" }
            [:a {:href "/"}
              [:img {:src "../simple-logo.png" }]]]
          [:a {:class "navbar-brand" :href "/"}
            [:img { :class "logo" :src "../logo.png"}]]]
        (if (not (= title "Oakland Bloody Mary"))
        [:ul {:class "nav navbar-nav navbar-right"}
          [:li
            [:a {
              :id "grid-btn"
              :type "button"
              :href "/"
              :class "btn btn-default navbar-btn navbar-right" }
                [:span {:class "glyphicon glyphicon-th" :aria-hidden "true"}]]]])]]
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
