(ns bloody-mary.test.views.cards
  (:use [hiccup.core :only (html)])
  (:require [clojure.test :refer :all]
            [bloody-mary.views.cards :refer :all]))

(deftest cards
  (testing "total-score"
    (is (= 15 (total-score {
      :rim_score       1
      :garnish_score   2
      :spice_score     3
      :booze_score     4
      :mouthfeel_score 5 }))))

  (testing "show-percent"
    (is (= (html (show-percent 20)) (str
      "<div class=\"percent-score-holder\">"
        "<div class=\"percent-score\" style=\"width:20%\">&nbsp;</div>"
        "<div class=\"percent-score-mask\"></div>"
      "</div>"))))

  (testing "show-total"
    (is (= (show-total {
      :rim_score       1
      :garnish_score   2
      :spice_score     3
      :booze_score     4
      :mouthfeel_score 5 }) (show-percent 60))))

  (testing "show-metric"
    (is (= (html(show-metric {
      :text "Rim"
      :description "Keep turning that glass"
      :score 5 })) (str
      "<div class=\"metric\">"
        "<h4>Rim</h4>"
        "<h5>Keep turning that glass</h5>"
        (html (show-percent 100))
      "</div>")))))
