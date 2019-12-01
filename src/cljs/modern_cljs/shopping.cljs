(ns modern-cljs.shopping
  (:require-macros [hiccups.core :as h])
  (:require [domina :as dom]
            [domina.events :as ev]
            [hiccups.runtime :as hiccupsrt]
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [cljs.reader :refer [read-string]]))

(defn calculate [evt]
  (let [quantity (dom/value (dom/by-id "quantity"))
        price    (dom/value (dom/by-id "price"))
        tax      (dom/value (dom/by-id "tax"))
        discount (dom/value (dom/by-id "discount"))]
    (remote-callback :calculate
                     [quantity price tax discount]
                     #(dom/set-value! (dom/by-id "total") (.toFixed % 2)))
    (ev/prevent-default evt)))

(defn add-help []
  (dom/append! (dom/by-id "shoppingForm")
               (h/html [:div.help "Click to calculate"andler])))

(defn remove-help []
  (dom/destroy! (dom/by-class "help")))

(defn ^:export init []
  (when (and js/document
           (aget js/document "getElementById"))
    (ev/listen! (dom/by-id "calc") :click (fn [evt] (calculate evt)))
    (ev/listen! (dom/by-id "calc") :mouseover add-help)
    (ev/listen! (dom/by-id "calc") :mouseout remove-help)))

;;(set! (.-onload js/window) init)
