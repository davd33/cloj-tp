(ns cloj-tp.views.slides
  (:require [re-com.core :as re-com]))

(defn citation
  [text]
  [:q
   {:style {:background "lightgray"}}
   text])

(defn slide-3 []
  [re-com/v-box
   :children [[citation "Re-frame is cool - slide 3"]]])

(defn slide-2 []
  [re-com/v-box
   :children [[citation "Re-frame is cool - slide 2"]]])

(defn slide-1 []
  [re-com/v-box
   :children [[citation "Re-frame is cool - slide 1"]]])
