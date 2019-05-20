(ns cloj-tp.views.home
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [breaking-point.core :as bp]
            [cloj-tp.subs :as subs]))

(defn display-re-pressed-example []
  (let [re-pressed-example (re-frame/subscribe [::subs/re-pressed-example])]
    [:div

     [:p
      [:span "Re-pressed is listening for keydown events. A message will be displayed when you type "]
      [:strong [:code "hello"]]
      [:span ". So go ahead, try it out!"]]

     (when-let [rpe @re-pressed-example]
       [re-com/alert-box
        :alert-type :info
        :body rpe])]))

(defn link-to-about-page []
  [:div
   {:style {:color "white"
            :background-color "darkblue"}}
   [re-com/hyperlink-href
    :label "go to Slides"
    :href "#/slide-1"]
   " "
   [re-com/hyperlink-href
    :label "go to About Page"
    :href "#/about"]])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[link-to-about-page]
              [display-re-pressed-example]
              [:div
               [:h3 (str "screen-width: " @(re-frame/subscribe [::bp/screen-width]))]
               [:h3 (str "screen: " @(re-frame/subscribe [::bp/screen]))]]
              ]])
