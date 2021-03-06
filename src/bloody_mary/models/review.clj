(ns bloody-mary.models.review
  (:require [clojure.java.jdbc :as sql]
            [clojure.string :as string]))

(def spec (or (System/getenv "HEROKU_POSTGRESQL_COPPER_URL")
              "postgresql://localhost:5432/bloody-mary"))

(defn all []
  (into [] (sql/query spec ["select * from reviews order by id desc"])))

(defn find-by-slug [slug]
  (first (into [] (sql/query spec ["select * from reviews where slug = ?" slug]))))

(defn slugify [input]
  (string/replace
    (string/lower-case
      (string/replace input " " "-"))
  #"[^a-z/-]" ""))

(defn create [title venue photo_url story score_card]
  (sql/insert! spec :reviews
    [ :title :venue :slug :photo_url :story
      :rim_score :garnish_score :spice_score :booze_score :mouthfeel_score ]
    [ title venue (slugify venue) photo_url story
      (int (read-string (:rim_score score_card)))
      (int (read-string (:garnish_score score_card)))
      (int (read-string (:spice_score score_card)))
      (int (read-string (:booze_score score_card)))
      (int (read-string (:mouthfeel_score score_card)))]))
