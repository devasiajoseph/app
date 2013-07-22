(ns app.views.pages 
  (:use net.cgrand.enlive-html)
  )

(def view-path "app/views/templates/")
(defn base [] (html-resource (str view-path "layout.html")))

(defn transform-page [page] (emit* (transform (base) [:div#container] (content page))))

(defn get-html [html-page] (html-resource (str view-path html-page)))

(defn get-page [html-page] (transform-page (get-html html-page)))

(defn get-raw-page [html-page] (emit* (get-html html-page)))
