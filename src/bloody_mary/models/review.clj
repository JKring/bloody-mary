(ns bloody-mary.models.review
  (:require [clojure.java.jdbc :as sql]
            [clojure.string :as string]))

(def spec (or (System/getenv "DATABASE_URL")
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

(defn create [title venue photo_url story]
  (sql/insert! spec :reviews
    [:title :venue :slug :photo_url :story]
    [title venue (slugify venue) photo_url story]))
