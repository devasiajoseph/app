(defproject
  app
  "0.1.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [lib-noir "0.6.2"]
   [compojure "1.1.5"]
   [ring-server "0.2.8"]
   [clabango "0.5"]
   [enlive "1.1.1"]
   [com.taoensso/timbre "2.1.2"]
   [com.postspectacular/rotor "0.1.0"]
   [com.taoensso/tower "1.7.1"]
   [markdown-clj "0.9.26"]
   [postgresql/postgresql "9.1-901.jdbc4"]
   [korma "0.3.0-RC5"]
   [log4j
    "1.2.15"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [http-kit "2.1.3"]
   [clojurewerkz/cassaforte "1.0.1"]
   [cc.qbits/alia "1.6.1"]]
  :ring
  {:handler app.handler/war-handler,
   :init app.handler/init,
   :destroy app.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.1.8"]]}}
  :url
  "http://example.com/FIXME"
  :main
  app.core
  :plugins
  [[lein-ring "0.8.5"]]
  :description
  "FIXME: write description"
  :min-lein-version "2.0.0")