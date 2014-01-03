(ns clj-appengine.util
  (:require [leiningen.core.main :as lein])
  (:import [java.io File]))

(def not-nil? (comp not nil?))

(defn war-path [project]
 (.getAbsolutePath (File. (:target-path project) "war")))

(defn appengine-home [project]
  (let [path (cond
              (not-nil? (:appengine-home project)) (:appengine-home)
              (not-nil? (System/getenv "APPENGINE_HOME")) (System/getenv "APPENGINE_HOME")
              :else (lein/abort "no Google App Engine SDK defined. Either set :appengine-home in project.clj or set environment variable APPENGINE_HOME"))]
        (File. path)))

(defn run
  "Executes cmd with opts and returns the exit code. Waits for child process to finish."
  [cmd opts]
  (-> (ProcessBuilder. (into-array (cons cmd opts))) 
    .inheritIO
    .start
    .waitFor))


