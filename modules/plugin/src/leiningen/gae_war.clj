(ns leiningen.gae-war
  "Prepares a the Google App Engine application for deployment."
  (:use [leiningen.core.main :only [abort]]
        [leiningen.clean :only [delete-file-recursively]]
        [clojure.string :only [replace-first join]])
  (:require leiningen.compile leiningen.jar
            [leiningen.core.project :as project]
            [leiningen.core.classpath :as classpath]
            [clojure.java.io :as io])
  (:import java.io.File))


(defn- sanitize [str] (clojure.string/replace str "-" "_"))

(defn- copy-dependency [src-path dest-path]
  (io/copy (io/file src-path) (io/file dest-path)))

(defn- copy-dependencies [dependencies target-dir]
  (doseq [dep dependencies]
    (copy-dependency dep (File. target-dir (.getName dep)))))

(defn- make-relative-to [file relative-to]
  (let [src-path (.getAbsolutePath file)
        relative-to-path (.getAbsolutePath relative-to)]
    (replace-first src-path (str relative-to-path File/separator) "")))

(defn- copy-war-files [src-war-path target-war-path]
  (let [files (rest (file-seq src-war-path))]
    (doseq [file files]
      (let [target-file (File. target-war-path (make-relative-to file src-war-path))]
        (if (.isDirectory file) 
          (.mkdir target-file)
          (io/copy file target-file))))))

(defn gae-war [project]
  (let [prj-application (:name project)
        prj-display-name (:name project)
        prj-servlet "servlet"
        dependencies (classpath/resolve-dependencies :dependencies (project/unmerge-profiles project [:default])) ; FIXME: Does this work?
        war-dir (File. (or (:appengine-app-war-root project) "war"))
        target-path (File. (:target-path project))
        target-war-path (File. target-path "war")
        target-web-inf-path (File. target-war-path "WEB-INF")
        target-lib-path (File. target-web-inf-path "lib")]
    (println "Building " prj-display-name " for deployment in " (.getAbsolutePath target-war-path))
    (let [project (if (contains? project :aot)
                      project
                      (assoc project
                        :keep-non-project-classes true
                        :aot [(symbol (format "%s.%s"
                                              (sanitize prj-application)
                                              prj-servlet))]))
          jar-file-name (leiningen.jar/get-jar-filename project)]
      (when (nil? (leiningen.compile/compile project))
        (when (.exists target-war-path)
          (delete-file-recursively target-war-path))
        (.mkdirs target-lib-path)
        (copy-war-files war-dir target-war-path)
        (leiningen.jar/jar (merge project {:omit-source true}))
        (copy-dependency jar-file-name (File. target-lib-path (.getName (File. jar-file-name))))
        (copy-dependencies dependencies target-lib-path)))))

