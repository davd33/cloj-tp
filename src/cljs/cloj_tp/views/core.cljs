(ns cloj-tp.views.core
  (:require [cloj-tp.subs :as subs]
            [cloj-tp.views.about :refer [about-panel]]
            [cloj-tp.views.home :refer [home-panel]]
            [re-com.core :as re-com]
            [cloj-tp.views.slides :refer [slide-1 slide-2 slide-3]]
            [re-frame.core :as re-frame]
            [cloj-tp.events :as events]))

(defn page-not-found
  [panel-name]
  (if (and panel-name (clojure.string/includes? panel-name "slide"))
    (do ; we set the slide index back to 1
      (re-frame/dispatch [::events/set-slide-panel 1])
      [slide-1])
    [:div
     "Page not found"
     [re-com/hyperlink-href
      :label "go home"
      :href "#/"]]))

(defn- panels [panel-name]
  (case panel-name
    "slide-1"     [slide-1]
    "slide-2"     [slide-2]
    "slide-3"     [slide-3]
    :home-panel  [home-panel]
    :about-panel [about-panel]
    [page-not-found panel-name]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
