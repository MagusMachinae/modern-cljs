(ns modern-cljs.login
  (:require-macros [hiccups.core :as hiccs]
                   [shoreleave.remotes.macros :as shore-macros])
  (:require [domina :as dom]
            [domina.events :as ev]
            [hiccups.runtime :as hiccupsrt]
            [modern-cljs.login.validators :as v]
            [shoreleave.remotes.http-rpc :refer [remote-callback]]))

(defn validate-email-domain [email]
  (remote-callback :email-domain-errors
                   [email]
                   #(if %
                      (do
                        (dom/prepend! (dom/by-id "loginForm")
                                      (hiccs/html [:div.help.email "The email domain doesn't exist."]))
                        false)
                      true)))

(defn validate-email [email]
  (dom/destroy! (dom/by-class "email"))
  (if-let [errors (:email (v/user-credential-errors (dom/value email) nil))]
    (do
      (dom/prepend! (dom/by-id "loginForm")
                    (hiccs/html [:div.help.email (first errors)]))
      false)
    (validate-email-domain (dom/value email))))

(defn validate-password [password]
  (dom/destroy! (dom/by-class "password"))
  (if-let [errors (:password (v/user-credential-errors nil (dom/value password)))]
    (do
      (dom/append! (dom/by-id "loginForm")
                   (hiccs/html [:div.help.password (first errors)]))
      false)
    true))

(defn validate-form [evt email password]
  ;get elements by-id
  (if-let [{e-errs :email p-errs :password} (v/user-credential-errors
                                             (dom/value email)
                                             (dom/value password))]
    ;get values with value  el
    (if (or e-errs p-errs)
      (do
        (dom/destroy! (dom/by-class "help"))
        (ev/prevent-default evt)
        (dom/append! (dom/by-id "loginForm")
                     (hiccs/html [:div.help "Please complete the Form."])))
      (ev/prevent-default evt))
    true))



;;deffn to attach validate-form to onsubmit event of form
(defn ^:export init []
  ;verify js/documen exists and it has a getElementById property
  (if (and js/document
           (aget js/document "getElementById"))
    (let [email    (dom/by-id "email")
          password (dom/by-id "password")]
      (ev/listen! (dom/by-id "submit") :click (fn [evt] (validate-form evt email password)))
      (ev/listen! email :blur (fn [evt] (validate-email email)))
      (ev/listen! password :blur (fn [evt] (validate-password password))))))

;;(set! (.-onload js/window) init)
