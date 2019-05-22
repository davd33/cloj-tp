(ns cloj-tp.views.core
  (:require [cloj-tp.events :as events]
            [cloj-tp.subs :as subs]
            [cloj-tp.views.about :refer [about-panel]]
            [cloj-tp.views.home :refer [home-panel]]
            [cloj-tp.views.slides :refer [get-slide]]
            [re-com.core :as re-com]
            [re-frame.core :as re-frame]))

(defn page-not-found
  [panel-name]
  (if (and panel-name (clojure.string/includes? panel-name "slide"))
    (do ; we set the slide index back to 1
      (re-frame/dispatch [::events/set-slide-panel 1])
      [(get-slide 1)])
    [:div
     "Page not found"
     [re-com/hyperlink-href
      :label "go home"
      :href "#/"]]))

(defn- panels [panel-name]
  (let [slide-panel-regex (re-matches #"slide-([0-9]+)" (str panel-name))]
    (if (not (empty? slide-panel-regex))
      [(get-slide (int (last slide-panel-regex)))]
      (case panel-name
        :home-panel  [home-panel]
        :about-panel [about-panel]
        [page-not-found panel-name]))))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
