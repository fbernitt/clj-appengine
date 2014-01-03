(ns leiningen.gae-dev-server
  (:use [leiningen.gae-war :only [gae-war]]
        [clj-appengine.util])
  (:require [leiningen.core.main :as lein])
  (:import [java.io File]))

(defn- start-dev-server [project]
  (let [sdk (appengine-home project)
        dev-server-sh (.getAbsolutePath (File. sdk "bin/dev_appserver.sh"))
        war (war-path project)]
    (run dev-server-sh [war])))

(defn gae-dev-server [project]
  (gae-war project)
  (start-dev-server project))
