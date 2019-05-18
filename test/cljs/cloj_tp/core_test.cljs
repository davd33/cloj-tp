(ns cloj-tp.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [cloj-tp.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
