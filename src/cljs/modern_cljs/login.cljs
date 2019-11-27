(ns modern-cljs.login
  (:require-macros [hiccups.core :as hiccs])
  (:require [domina :as dom]
            [domina.events :as ev]
            [hiccups.runtime]))

(defn validate-email [email]
  (dom/destroy! (dom/by-class "email"))
  (if (not (re-matches (re-pattern (dom/attr email :pattern))
                       (dom/value email)))
    (do
      (dom/prepend! (dom/by-id "loginForm")
                    (hiccs/html [:div.help.email (dom/attr email :title)]))
      false)
    true))

(defn validate-password [password]
  (dom/destroy! (dom/by-class "password"))
  (if (not (re-matches (re-pattern (dom/attr password :pattern))
                       (dom/value password)))
    (do
      (dom/append! (dom/by-id "loginForm")
                   (hiccs/html [:div.help.password (dom/attr password :title)]))
      false)
    true))

(defn validate-form [evt]
  ;get elements by-id
  (let [email     (dom/by-id "email")
        password  (dom/by-id "password")
        email-val (dom/value email)
        password-val  (dom/value password)]
    ;get values with value  el
    (if (or (empty? email-val)
            (empty? password-val))
      (do
        (dom/destroy! (dom/by-class "help"))
        (ev/prevent-default evt)
        (dom/append! (dom/by-id "loginForm")
                     (hiccs/html [:div.help "Please complete the Form."])))
      (if (and (validate-email email)
               (validate-password password))
        true
        (ev/prevent-default evt)))))


;;deffn to attach validate-form to onsubmit event of form
(defn ^:export init []
  ;verify js/documen exists and it has a getElementById property
  (if (and js/document
           (aget js/document "getElementById"))
    (let [email    (dom/by-id "email")
          password (dom/by-id "password")]
      (ev/listen! (dom/by-id "submit") :click (fn [e] (validate-form e)))
      (ev/listen! email :blur (fn [evt] (validate-email email)))
      (ev/listen! password :blur (fn [evt] (validate-password password))))))

;;(set! (.-onload js/window) init)
