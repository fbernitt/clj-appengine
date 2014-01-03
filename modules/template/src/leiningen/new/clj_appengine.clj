(ns leiningen.new.clj-appengine
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "clj-appengine"))

(defn clj-appengine
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' clj-appengine project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)]
             ["src/{{sanitized}}/servlet.clj" (render "servlet.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["war/WEB-INF/web.xml" (render "web.xml" data)]
             ["war/WEB-INF/appengine-web.xml" (render "appengine-web.xml" data)])))

