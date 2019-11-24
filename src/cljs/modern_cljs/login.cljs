(ns modern-cljs.login
  (:require [domina :refer [by-id value]]))

(defn validate-form []
  ;get elements by-id
  (let [email    (by-id "email")
        password (by-id "password")]
    ;get values with value  el
    (if (and (> (count (value email)) 0)
             (> (count (value password)) 0))
      true
      (do (js/alert "Please complete the form.")
        false))))

;;deffn to attach validate-form to onsubmit event of form
(defn init []
  ;verify js/documen exists and it has a getElementById property
  (if (and js/document
           (.-getElementById js/document))
    (let [login-form (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form) validate-form))))

(set! (.-onload js/window) init)
