(ns bloody-mary.test.web
  (:require [clojure.test :refer :all]
            [bloody-mary.web :refer :all]))

(deftest test-app
  (testing "addition"
    (is (= (+ 1 2) 3))))
