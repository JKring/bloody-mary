(ns bloody-mary.test.models.review
  (:require [clojure.test :refer :all]
            [bloody-mary.models.review :refer :all]))

; Not sure how to mock the database interface...
; (deftest review
;   (testing "addition"
;     (is (= (1) (
;       create
;       "Awesome Story"
;       "New Easy is Coo"
;       "https://google.com"
;       "<p>this is a story all about how</p>"
;       {
;         :rim_score       "1"
;         :garnish_score   "2"
;         :spice_score     "3"
;         :booze_score     "4"
;         :mouthfeel_score "5" })))))
