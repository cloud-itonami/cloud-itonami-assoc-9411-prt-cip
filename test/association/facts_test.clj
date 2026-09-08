(ns association.facts-test
  "What the catalog must hold, asked of the catalog rather than of a count.

  The earlier version of this suite asserted `(= 2 (count sb))`. That is a
  true statement about a catalog with two entries and says nothing about
  either of them -- it passes just as happily over two fabricated rows, and
  the only maintenance it invites is bumping the number. What follows asks
  instead whether every entry can still show where it came from."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.edn :as edn]
            [kotoba.lang.text :as str]
            [association.facts :as facts]))

(def data (edn/read-string (slurp "data/datascript-tx.edn")))

;; The same table scripts/verify-catalog.cljs holds. Duplicated on purpose:
;; the script is the live gate and does not run in this suite, so an entry
;; that invented a provenance keyword would otherwise reach production
;; whenever the network check was skipped.
(def provenance->host
  {:official-cip-org-pt         "cip.org.pt"
   :pt-social-concertation-body "ces.pt"
   :peak-body-businesseurope    "businesseurope.eu"
   :wikipedia-corroborated      "wikipedia.org"})

(def date-bases #{:in-quote :in-quote-under-year-heading
                  :timeline-year-heading :corroborating-quote})

(defn- host-of [u] (second (re-find #"^https?://([^/]+)" (str u))))
(defn- host-matches? [h suffix]
  (and h (or (= h suffix) (str/ends-with? h (str "." suffix)))))

(deftest the-fixture-reads-a-real-catalog
  ;; Every assertion below is a `doseq` or an `every?`, and both are vacuously
  ;; true over nothing at all.
  (is (pos? (count data)))
  (is (= (count data) (count (facts/spec-basis "cip-prt")))
      "the cljc catalog and the data file must hold the same number of entries"))

(deftest cip-prt-has-spec-basis
  (let [sb (facts/spec-basis "cip-prt")]
    (is (every? #(= "9411" (:association-rule/isic %)) sb))
    (is (every? #(= "PRT" (:association-rule/country %)) sb))
    (is (every? #(str/starts-with? (:association-rule/id %) "cip-prt.") sb))
    (is (= (count sb) (count (distinct (map :association-rule/id sb))))
        "ids are what cross-repo queries join on")))

(deftest every-entry-shows-the-span-it-rests-on
  (doseq [e (facts/spec-basis "cip-prt")]
    (testing (:association-rule/id e)
      (is (not (str/blank? (str (:association-rule/source-quote e))))
          "a citation with no quote can only be checked for reachability")
      (is (>= (count (str (:association-rule/source-quote e))) 12)
          "a span that short can be found on a page that is not serving the claim"))))

(deftest provenance-is-corroborated-by-the-host
  (doseq [e (facts/spec-basis "cip-prt")]
    (testing (:association-rule/id e)
      (let [p (:association-rule/url-provenance e)]
        (is (contains? provenance->host p)
            "an undeclared provenance keyword inherits authority for free")
        (is (host-matches? (host-of (:association-rule/url e)) (provenance->host p))
            "the keyword names who is speaking; only the host can confirm it")))))

(deftest corroboration-is-all-three-fields-or-none
  (doseq [e (facts/spec-basis "cip-prt")]
    (testing (:association-rule/id e)
      (let [present (remove nil? [(:association-rule/corroborating-url e)
                                  (:association-rule/corroborating-provenance e)
                                  (:association-rule/corroborating-quote e)])]
        (is (contains? #{0 3} (count present))
            "two of three is a citation nothing can check but a reader believes")
        (when (seq present)
          (let [p (:association-rule/corroborating-provenance e)]
            (is (contains? provenance->host p))
            (is (host-matches? (host-of (:association-rule/corroborating-url e))
                               (provenance->host p)))))))))

(deftest every-date-says-where-it-is-readable-from
  (doseq [e (facts/spec-basis "cip-prt")]
    (testing (:association-rule/id e)
      (if-let [d (:association-rule/established-date e)]
        (do (is (re-matches #"\d{4}(-\d{2}(-\d{2})?)?" d))
            (is (contains? date-bases (:association-rule/date-basis e)))
            (when (= :corroborating-quote (:association-rule/date-basis e))
              (is (some? (:association-rule/corroborating-quote e))
                  "a basis that names a span there is no span for")))
        (is (some? (:association-rule/date-unknown-because e))
            "an honestly-undated entry and a forgotten one must not look alike")))))

(deftest precision-beyond-the-year-comes-from-a-second-source-or-the-sentence
  ;; cip.org.pt's timeline gives years only. Any month or day in this catalog
  ;; therefore has to be read out of a span, not out of the heading -- which is
  ;; the mistake the previous catalog made: it recorded 1974-05 citing only the
  ;; official page, which says "1974".
  (doseq [e (facts/spec-basis "cip-prt")
          :let [d (str (:association-rule/established-date e))]
          :when (> (count d) 4)]
    (testing (:association-rule/id e)
      (is (contains? #{:in-quote :in-quote-under-year-heading :corroborating-quote}
                     (:association-rule/date-basis e))
          (str d " is finer than year precision, so it cannot rest on a heading")))))

(deftest the-catalog-can-report-its-own-gaps
  (is (empty? (facts/unsourced)) "every entry must carry a quote and a date basis")
  (let [srcs (facts/sources)]
    (is (= (count srcs) (count (distinct srcs))))
    (is (>= (count srcs) 4)
        "four voices: CIP, the CES, the European peak body, an encyclopaedia")
    (is (every? #(str/starts-with? % "https://") srcs))))

(deftest more-than-one-voice-is-actually-present
  ;; A catalog can grow entries without gaining a source. That is exactly the
  ;; shape padding has, so it is asserted against rather than assumed.
  (let [hosts (set (map host-of (facts/sources)))]
    (is (>= (count hosts) 4) (str "distinct hosts: " hosts))
    (is (contains? hosts "cip.org.pt") "CIP must speak about itself here")
    (is (some #(str/ends-with? % "ces.pt") hosts)
        "and the statutory social-concertation body must too")))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "aep")))
  (is (nil? (facts/spec-basis "zzz")))
  (is (nil? (facts/spec-basis "cip")) "Ecuador's slug must not resolve here"))

(deftest coverage-is-honest
  (let [c (facts/coverage ["cip-prt" "aep"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["aep"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= (count (facts/spec-basis "cip-prt"))
         (count (facts/by-topic "cip-prt" :governance)))
      "every entry is governance; if that stops being true, say so here")
  (is (pos? (count (facts/by-topic "cip-prt" :labor))))
  (is (pos? (count (facts/by-topic "cip-prt" :europe))))
  (is (empty? (facts/by-topic "cip-prt" :no-such-topic)))
  (is (empty? (facts/by-topic "aep" :governance))))
