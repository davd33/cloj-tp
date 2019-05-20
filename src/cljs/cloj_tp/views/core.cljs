(ns cloj-tp.views.core
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [breaking-point.core :as bp]
   [re-pressed.core :as rp]
   [cloj-tp.subs :as subs]
   [cloj-tp.views.slides :refer [slide-1 slide-2 slide-3]]
   [cloj-tp.views.about :refer [about-panel]]
   [cloj-tp.views.home :refer [home-panel]]
   ))

(defn h []
  (print (zipmap (map  ))))

(defn- panels [panel-name]
  (case panel-name
    :slide-1 [slide-1]
    :slide-2 [slide-2]
    :slide-3 [slide-3]
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div
     "Page not found"
     [re-com/hyperlink-href
      :label "go home"
      :href "#/"]]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
