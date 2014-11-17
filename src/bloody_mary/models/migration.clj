(ns bloody-mary.models.migration
  (:require [clojure.java.jdbc :as sql]
            [bloody-mary.models.review :as review]))

(defn migrated? []
  (-> (sql/query review/spec
    [(str "select count(*) from information_schema.tables "
      "where table_name='reviews'")])
  first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands review/spec
      (sql/create-table-ddl :reviews
        [:id :serial "PRIMARY KEY"]
        [:title :varchar "NOT NULL"]
        [:venue :varchar "NOT NULL"]
        [:rim_score :integer "NOT NULL"]
        [:garnish_score :integer "NOT NULL"]
        [:spice_score :integer "NOT NULL"]
        [:booze_score :integer "NOT NULL"]
        [:mouthfeel_score :integer "NOT NULL"]
        [:slug :varchar "NOT NULL UNIQUE"]
        [:photo_url :varchar "NOT NULL"]
        [:story :text "NOT NULL"]
        [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))


; DROP TABLE
; (ns bloody-mary.models.migration
;   (:require [clojure.java.jdbc :as sql]
;             [bloody-mary.models.review :as review]))
; (sql/db-do-commands review/spec
;       (sql/drop-table-ddl :reviews))
