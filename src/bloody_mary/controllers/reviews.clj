(ns bloody-mary.controllers.reviews
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [bloody-mary.views.reviews :as view]
            [bloody-mary.models.review :as model]))

(defn create [title venue photo_url story rim_score garnish_score spice_score booze_score mouthfeel_score]
  (model/create title venue photo_url story
    (int (read-string rim_score))
    (int (read-string garnish_score))
    (int (read-string spice_score))
    (int (read-string booze_score))
    (int (read-string mouthfeel_score)))
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
  (POST "/" [title venue photo_url story rim_score garnish_score spice_score booze_score mouthfeel_score]
    (create title venue photo_url story rim_score garnish_score spice_score booze_score mouthfeel_score))
  (GET ["/:slug"] [slug] (show slug)))
