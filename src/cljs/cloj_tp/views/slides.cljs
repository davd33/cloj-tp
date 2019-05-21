(ns cloj-tp.views.slides
  (:require [re-com.core :as re-com]))

(def some-colors
  ["#F2A2C8" "#6394BF" "#04ADBF" "#F2E52E" "#F2D22E"])

(defn rand-of
  [vector-to-choose-from]
  (get vector-to-choose-from (rand-int (count vector-to-choose-from))))

(defn list-of-cool-concepts
  [cool-concepts]
  [:ul
   {:style {:display "flex"
            :max-width "100vw"
            :flex-wrap "wrap"
            :justify-content "space-around"
            :padding "0"}}
   (for [zipped-concept (zipmap (range 0 (count cool-concepts)) cool-concepts)]
     (let [i (first zipped-concept)
           concept (last zipped-concept)]
       [:li
        {:key i
         :style {:list-style "none"
                 :text-transform "capitalize"
                 :background (rand-of some-colors)
                 :padding "10px"
                 :border-radius "5px"
                 :margin "20px 30px"}}
        concept]))])

(defn citation
  [text]
  [:q
   {:style {:background "lightgray"}}
   text])

(defn slide-container
  [slide]
  [:div
   {:style {:display "flex"
            :text-align "center"
            :justify-content "center"
            :width "100%"}}
   slide])

(defn some-space
  []
  [:div
   {:style {:margin-top "1.5em"}}
   ""])

(defn slide-1 []
  [slide-container
   [re-com/v-box
    :children [[:h1
                "Clojure / ClojureScript"]
               [:em "David Rueda"]
               [some-space]
               [:h3
                {:style {:color (rand-of some-colors)}}
                "... and some functional programming!"]]]])

(defn slide-2 []
  [slide-container
   [re-com/v-box
    :children [[:h1 "Functional Programming"]
               [:em "or a myriad of new concepts"]
               [some-space]
               [list-of-cool-concepts (shuffle ["Pure functions"
                                                "Lambda functions"
                                                "High Order functions"
                                                "Immutability"
                                                "Recursion"
                                                "Composition"
                                                "Lazy evaluation"
                                                "Closures"
                                                "Partial application"])]]]])

(defn slide-3 []
  [slide-container
   [re-com/v-box
    :children [[:h1 "What is clojure?"]
               [citation "Re-frame is cool - slide 3"]]]])
