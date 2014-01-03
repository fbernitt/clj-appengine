(ns {{name}}.servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use {{servlet-package}}.core)
  (:use [ring.util.servlet :only [make-service-method]]))
;  (:use [clj-appengine.servlet :only [make-servlet-service-method]]))

(defn -service 
  [this request response]
;  ((make-servlet-service-method {{servlet-package}}-app) this request response))
  ((make-service-method {{name}}-app) this request response))

