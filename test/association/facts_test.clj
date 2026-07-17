(ns association.facts-test
  (:require [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest cip-prt-has-spec-basis
  (let [sb (facts/spec-basis "cip-prt")]
    (is (= 2 (count sb)))
    (is (every? #(= "9411" (:association-rule/isic %)) sb))
    (is (every? #(= "PRT" (:association-rule/country %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "aep")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["cip-prt" "aep"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["aep"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= 2 (count (facts/by-topic "cip-prt" :governance))))
  (is (empty? (facts/by-topic "cip-prt" :labor)))
  (is (empty? (facts/by-topic "aep" :governance))))
