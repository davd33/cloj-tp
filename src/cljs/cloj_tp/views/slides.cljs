(ns cloj-tp.views.slides
  (:require [cloj-tp.events :as events]
            [re-com.core :as re-com]
            [re-frame.core :as re-frame]
            [cloj-tp.subs :as subs]
            [day8.re-frame-10x.view.event :as event]))

(defn px
  [how-many]
  (str how-many "px"))

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
            :margin "auto"
            :height "100vh"
            :width "100vw"}}
   slide])

(defn some-space
  []
  [:div
   {:style {:margin-top "1.5em"}}
   ""])

(defn fun-h1 [title]
  [:h1
   {:style {:max-width "80vw"
            :margin "0 auto"
            :text-shadow (str "5px 5px 0 " (rand-of some-colors))}}
   title])

(defn v-box
  [component]
  [:div
   {:style {:margin-top "10vh"}}
   component])

(defn index-slide []
  [slide-container
   (v-box
    [re-com/v-box
     :children [[fun-h1 "Clojure / ClojureScript"]
                [:em "David Rueda"]
                [some-space]
                [:h3
                 {:style {:color (rand-of some-colors)}}
                 "... and some "
                 [:strong "FUN"]
                 " ctional programming!"]]])])

(defn a-modern-lisp-slide []
  [slide-container
   (v-box
    [re-com/v-box
     :children [[fun-h1 "Clojure is a modern LISP"]
                [some-space]
                [:h3 "Homoiconic"]
                [:strong "You program in a Lisp by creating and assembling Lisp data structures."]
                [some-space]
                [some-space]
                [:img
                 {:alt "structure vs semantics"
                  :style {:width (px (* 2 400))
                          :height (px (* 2 202))
                          :margin "auto"}
                  :src "./images/clojure/structure-and-semantics.png"}]]])])

(defn functional-programming-slide []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "Functional Programming"]
                      [:em "or a myriad of new concepts"]
                      [some-space]
                      [list-of-cool-concepts (shuffle ["Pure functions"
                                                       "Lambda functions"
                                                       "Higher Order functions"
                                                       "Immutability"
                                                       "Recursion"
                                                       "Composition"
                                                       "Lazy evaluation"
                                                       "Closures"
                                                       "Partial application"])]]])])

(defn concurrent-programming-slide []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "Designed for concurrent programming"]
                      [some-space]
                      [citation (str "Rich Hickey designed Clojure to specifically address "
                                     "the problems that develop from shared access to mutable state. "
                                     "In fact, Clojure embodies a very clear conception of state that "
                                     "makes it inherently safer for concurrency than most popular "
                                     "programming languages. It's safe all the way down to its "
                                     "meta-freaking-physics.")]]])])

(defn tasks-slide []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "Tasks"]
                      [:em "how to manage concurrency"]
                      [some-space]
                      [list-of-cool-concepts (shuffle ["Future"
                                                       "Fire and forget"
                                                       "Delay"
                                                       "wait until my command"
                                                       "Promise"
                                                       "come back with the ticket"])]]])])

(defn a-non-lisp-eval-slide []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "a non-lisp eval"]
                      [:img
                       {:alt "a non-lisp eval"
                        :style {:width "415px"
                                :height "651px"
                                :margin "auto"}
                        :src "./images/clojure/non-lisp-eval.png"}]]])])

(defn a-lisp-eval-slide []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "a LISP eval"]
                      [:img
                       {:alt "this is a LISP eval"
                        :style {:width "417px"
                                :height "619px"
                                :margin "auto"}
                        :src "./images/clojure/lisp-eval.png"}]]])])

(defn macro-slide []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "Extend clojure with macros"]
                      [:img
                       {:alt   "a sum macro"
                        :style {:width  "174px"
                                :height "643px"
                                :margin "auto"}
                        :src   "./images/clojure/whole-shebang.png"}]]])])

(defn a-lisp-for-the-jvm []
  [slide-container
   (v-box [re-com/v-box
           :children [[fun-h1 "Clojure runs on the JVM!"]
                      [:div
                       {:style {:display "flex"
                                :margin "230px 0"}}
                       [:img
                        {:alt "java"
                         :style {:width (px 255)
                                 :height (px 255)
                                 :margin "auto"}
                         :src "./images/clojure/icons8-java.svg"}]
                       [:img
                        {:alt "clojure"
                         :style {:width (px 256)
                                 :height (px 256)
                                 :margin "auto"}
                         :src "./images/clojure/Clojure_logo.svg"}]]]])])

(def slides {1 index-slide
             2 a-modern-lisp-slide
             3 a-lisp-for-the-jvm
             4 a-non-lisp-eval-slide
             5 a-lisp-eval-slide
             6 macro-slide
             7 functional-programming-slide
             8 concurrent-programming-slide
             9 tasks-slide})

(defn get-slide
  [slide-index]
  (or (get slides slide-index)
      (get slides 1)))
