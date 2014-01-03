(ns {{name}}.core
  (:require [clj-appengine.core :as ae]))

(defn {{name}}-app-handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world!"})

(ae/def-appengine-app {{name}}-app #'{{name}}-app-handler)

