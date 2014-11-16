(ns bloody-mary.controllers.reviews
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [bloody-mary.views.reviews :as view]
            [bloody-mary.models.review :as model]))

(defn create [title venue photo_url story]
  (model/create title venue photo_url story)
  (ring/redirect "/"))

(defn index []
  (view/index (model/all)))

(defn submit []
  (view/submit))

(defn show [slug]
  (view/show (model/find-by-slug slug)))

(defroutes routes
  (GET  "/" [] (index))
  (GET  "/submit" [] (submit))
  (POST "/" [title venue photo_url story] (create title venue photo_url story))
  (GET ["/:slug"] [slug] (show slug)))
