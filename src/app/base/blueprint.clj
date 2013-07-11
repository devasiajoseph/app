(ns app.base.blueprint)

(defn is-live? [fn-name] (println "checking : " fn-name) false)
(defn post-to-facebook [post] (println post " posted on facebook" ))

(defn add [a b] (+ a b))

(defmacro if-live [function & args] 
  `(if (is-live? '~function)
     (~function ~@args)
     (println "DEVELOPMENT Skipping:" '~function ~@args)))


(defmacro function [function & args]
  
  )

(defmacro feature [feature-name fn-name]
  
  )


(defn workflow [] )

(defmacro ctrl [feature-name fn-name] 
  )