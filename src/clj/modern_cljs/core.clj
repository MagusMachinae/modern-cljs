(ns modern-cljs.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [modern-cljs.login :as l]
            [modern-cljs.templates.shopping :refer [shopping]]
            [shoreleave.middleware.rpc :as rpc]))

;; defroutes macro defines a functions that composes individual route functions.
;;The request map is passed to each function in turn until a non-nil response is returned.
(defroutes handler
  ;to serve document root addr
  (GET  "/" []
        "<p>Compojure online</p>")
  (POST "/login" [email password]
        (l/authenticate-user email password))
  (POST "/shopping" [quantity price tax discount]
        (shopping quantity price tax discount))
  ;serve static pages saved in resources/public
  (route/resources "/")
  (route/not-found "Page not found."))



(def app
  (-> (var handler)
      (rpc/wrap-rpc)
      (handler/site)))
