(ns app.routes.home
  (:use compojure.core
        app.views.pages)
  (:require [app.views.layout :as layout]
            [app.util :as util]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defn angular [] 
  (get-raw-page "index.html")
  )

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/angular" [] (angular))
  )


;;(ctrl post-facebook )