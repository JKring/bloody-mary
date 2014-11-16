(ns bloody-mary.web
  (:use [compojure.core :only (defroutes)]
        [ring.adapter.jetty :as ring])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [bloody-mary.controllers.reviews :as reviews]
            [bloody-mary.views.layout :as layout]
            [bloody-mary.models.migration :as schema])
  (:gen-class))

(defroutes routes
  (route/resources "/")
  reviews/routes
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (run-jetty application {:port port
                          :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (start port)))
