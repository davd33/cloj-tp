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
   {:style {:max-width "80vw"
            :text-align "center"}}
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

(defn fun-h1 [title]
  [:h1
   {:style {:text-shadow (str "5px 5px 0 " (rand-of some-colors))}}
   title])

(defn slide-1 []
  [slide-container
   [re-com/v-box
    :children [[fun-h1 "Clojure / ClojureScript"]
               [:em "David Rueda"]
               [some-space]
               [:h3
                {:style {:color (rand-of some-colors)}}
                "... and some "
                [:strong "FUN"]
                " ctional programming!"]]]])

(defn slide-2 []
  [slide-container
   [re-com/v-box
    :children [[fun-h1 "Functional Programming"]
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
    :children [[fun-h1 "What is clojure?"]
               [some-space]
               [citation (str "Rich Hickey designed Clojure to specifically address "
                              "the problems that develop from shared access to mutable state. "
                              "In fact, Clojure embodies a very clear conception of state that "
                              "makes it inherently safer for concurrency than most popular "
                              "programming languages. It's safe all the way down to its "
                              "meta-freaking-physics.")]]]])

(defn slide-4 []
  [slide-container
   [re-com/v-box
    :children [[fun-h1 "Tasks"]
               [:em "how to manage concurrency"]
               [some-space]
               [list-of-cool-concepts (shuffle ["Future"
                                                "Fire and forget"
                                                "Delay"
                                                "wait until my command"
                                                "Promise"
                                                "come back with the ticket"])]]]])

(defn slide-5 []
  [slide-container
   [re-com/v-box
    :children [[fun-h1 "a non-lisp eval"]
               [:img
                {:alt "a non-lisp eval"
                 :src "./images/clojure/non-lisp-eval.png"}]]]])

(defn slide-6 []
  [slide-container
   [re-com/v-box
    :children [[fun-h1 "a LISP eval"]
               [:img
                {:alt "this is a LISP eval"
                 :src "./images/clojure/lisp-eval.png"}]]]])

(defn slide-7 []
  [slide-container
   [re-com/v-box
    :children [[fun-h1 "Extend clojure with macros"]
               [:img
                {:alt "a sum macro"
                 :src "./images/clojure/whole-shebang.png"}]]]])

