(ns clj-appengine.core-test
  (:require [clojure.test :refer :all]
            [clj-appengine.core :refer :all]))

(deftest appengine-app-id-test
  (testing "AppId is retrievable"
    (is (= "" (appengine-app-id)))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
