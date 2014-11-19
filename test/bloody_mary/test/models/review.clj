(ns bloody-mary.test.models.review
  (:require [clojure.test :refer :all]
            [bloody-mary.models.review :refer :all]))

(deftest review
  (testing "slugify"
    (is (= "this-is-a-slug" (slugify "This 1is a @Slug!")))))


; Not sure how to mock the database interface...
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


