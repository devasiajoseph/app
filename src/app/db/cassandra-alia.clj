(ns app.db.cassandra-alia
  (:require [qbits.alia :as alia]))

(def cluster (alia/cluster "localhost"))
(def session (alia/connect cluster "newkey"))