(ns cloj-tp.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as gevents]
   [goog.history.EventType :as EventType]
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [cloj-tp.events :as events]))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn add-slides-keyboard-events
  []
  (re-frame/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[::events/previous-slide] ; 'l'
                   [{:which 72}]]
                  [[::events/next-slide] ; 'h'
                   [{:which 76}]]]}]))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (re-frame/dispatch [::events/set-active-panel :home-panel])
    (re-frame/dispatch [::events/set-re-pressed-example nil])
    (re-frame/dispatch
     [::rp/set-keydown-rules
      {:event-keys [[[::events/set-re-pressed-example "Hello, world!"]
                     [{:which 72} ;; h
                      {:which 69} ;; e
                      {:which 76} ;; l
                      {:which 76} ;; l
                      {:which 79} ;; o
                      ]]]

       :clear-keys
       [[{:which 27} ;; escape
         ]]}]))

  (dotimes [n (count cloj-tp.views.slides/slides)]
    (let [slide-name (str "slide-" (inc n))]
      (defroute (str "/" slide-name) []
        (re-frame/dispatch [::events/set-active-panel slide-name])
        (add-slides-keyboard-events))))

  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel :about-panel]))


  ;; --------------------


  (hook-browser-navigation!))
