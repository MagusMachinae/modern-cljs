(ns modern-cljs.login
  (:require [modern-cljs.login.validators :as v]))

(declare validate-email validate-password)

(defn authenticate-user [email password]
  (if (or (boolean (v/user-credential-errors email password))
          (boolean (v/email-domain-errors email)))
    (str "Please complete the form")
    (str email " and " password
         "passed the formal validation but we still have to authenticate you")))
