(ns modern-cljs.login
  (:require-macros [hiccups.core :as h])
  (:require [domina :as dom]
            [domina.events :as ev]
            [hiccups.runtime]))

(defn validate-form []
  ;get elements by-id
  (let [email    (dom/by-id "email")
        password (dom/by-id "password")]
    ;get values with value  el
    (if (and (> (count (dom/value email)) 0)
             (> (count (dom/value password)) 0))
      true
      (do (js/alert "Please complete the form.")
        false))))

;;deffn to attach validate-form to onsubmit event of form
(defn ^:export init []
  ;verify js/documen exists and it has a getElementById property
  (if (and js/document
           (aget js/document "getElementById"))
    (ev/listen! (dom/by-id "submit") :click validate-form)))

;;(set! (.-onload js/window) init)
