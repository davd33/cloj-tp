(ns cloj-tp.events
  (:require
   [re-frame.core :as re-frame]
   [cloj-tp.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-fx
 ::next-slide
 (fn-traced [cofx _]
            (let [active-panel (str (get-in cofx [:db :active-panel]))
                  slide-matches (re-matches #"slide-([0-9]+)" active-panel)
                  slide-index (int (last slide-matches))]
              (if (not (empty? slide-matches))
                {:dispatch [::set-slide-panel (min (count cloj-tp.views.slides/slides) (inc slide-index))]}))))

(re-frame/reg-event-fx
 ::previous-slide
 (fn-traced [cofx _]
            (let [active-panel (get-in cofx [:db :active-panel])
                  slide-matches (re-matches #"slide-([0-9]+)" active-panel)
                  slide-index (last slide-matches)]
              (if (not (empty? slide-matches))
                {:dispatch [::set-slide-panel (max 1 (dec slide-index))]}))))

(re-frame/reg-event-fx
 ::set-slide-panel
 (fn-traced [_ [_ slide-index]]
            {:dispatch [::set-active-panel (str "slide-" slide-index)]}))

(re-frame/reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
            (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 ::set-re-pressed-example
 (fn [db [_ value]]
   (assoc db :re-pressed-example value)))
