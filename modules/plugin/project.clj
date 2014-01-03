(defproject clj-appengine "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :eval-in-leiningen true
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.1"]
                 [ring/ring-servlet "1.2.1"]
                 [javax.servlet/servlet-api "2.5"]
                 ;; App Engine libraries
                 [com.google.appengine/appengine-api-1.0-sdk "1.8.8"]
                 [com.google.appengine/appengine-testing "1.8.8"]
                 [com.google.appengine/appengine-tools-sdk "1.8.8"]
])

