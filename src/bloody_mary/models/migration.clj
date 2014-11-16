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
        [:slug :varchar "NOT NULL UNIQUE"]
        [:photo_url :varchar "NOT NULL"]
        [:story :text "NOT NULL"]
        [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))

; (ns bloody-mary.models.migration
;   (:require [clojure.java.jdbc :as sql]))
; (defn database-url []
;   (or (System/getenv "DATABASE_URL") "postgresql://localhost:5432/bloody-mary"))
; (sql/with-connection (database-url)
;   (sql/drop-table :reviews))
