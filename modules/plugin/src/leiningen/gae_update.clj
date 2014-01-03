(ns leiningen.gae-update
  (:use [clj-appengine.util]
        [leiningen.gae-war :only [gae-war]])
  (:import [java.io File]))

(defn- update [project app-name]
  (let [sdk (appengine-home project)
        war (war-path project)
        appcfg-sh (.getAbsolutePath (File. sdk "bin/appcfg.sh"))]
    (run appcfg-sh ["-A" app-name "update" war])))

(defn gae-update [project app-name]
  (gae-war project)
  (update project app-name))

