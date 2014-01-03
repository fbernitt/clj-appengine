(ns clj-appengine.core
  (:import com.google.apphosting.api.ApiProxy))


(defn appengine-app-id []
  (try
    (-> (ApiProxy/getCurrentEnvironment) .getAppId)
    (catch NullPointerException e
      (throw (RuntimeException. "the server must be running")))))

(defmacro def-appengine-app [app-var-name handler & [args]]
  `(def ~app-var-name ~handler))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
