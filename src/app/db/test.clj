(ns app.db.test
  (:require [clojurewerkz.cassaforte.client :as client])
  (:use clojurewerkz.cassaforte.cql
        clojurewerkz.cassaforte.query))

(client/connect! ["127.0.0.1"])
(use-keyspace "newkey")

;;(insert :posts {:name "Alex" :city "Munich"})
;;(create-table "users"(column-definitions {:name :varchar :age  :int :primary-key [:name]}))

(defn do-this []
  (client/execute "INSERT INTO users (name, age) VALUES ('John', 29);")
  (select "users")
  )

(defn new-post-table [table-name]
  (create-table table-name
              (column-definitions {:id :varchar
                                   :title  :varchar
                                   :body     :text
                                   :primary-key [:id]})))

(defn new-subscribe-table [table-name]
  (create-table table-name
              (column-definitions {:post_id :varchar
                                   :user_id :varchar
                                   :primary-key [:post_id :user_id]})))


(defn uuid [] (str (java.util.UUID/randomUUID)))
(defn insert-post [title body]
  (client/execute (str "INSERT INTO myposts (id, title, body) VALUES ('" (uuid) "','" title "','"body "')")))

(defn show-posts [] (select "myposts"))

(defn add-subscription [user-id post-id]
  (client/execute (str "INSERT INTO subscriptions1 (user_id, post_id) VALUES ('" user-id "','" post-id "')")))

(defn show-subscriptions []
  (select "subscriptions1")
  )
