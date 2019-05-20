(ns cloj-tp.views.slides
  (:require [re-com.core :as re-com]))

(defn citation
  [text]
  [:q
   {:style {:background "lightgray"}}
   text])

(defn slide
  [{c :children n :next}]
  (let [children (into c [re-com/hyperlink-href
                          :label "Next slide"
                          :href (str "#/" n)])
        box-children (map #(into %1 :key %2) (zipmap children (range 0 (count children))))]
    [re-com/v-box
     :children box-children]))

(defn slide-3 []
  [slide
   {:children [[:h1
                "Re-frame is cool"]]
    :next ""}])

(defn slide-2 []
  [slide
   {:children [[:h1
                ""]]
    :next "slide-3"}])

(defn slide-1 []
  [slide
   {:next "slide-2"
    :children [[:h1
                "Functional programming and Clojure!"]]}])
