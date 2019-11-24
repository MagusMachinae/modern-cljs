(ns modern-cljs.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

;; defroutes macro defines a functions that composes individual route functions.
;;The request map is passed to each function in turn until a non-nil response is returned.
(defroutes app-routes
  ;to serve document root addr
  (GET "/" [] "<p>Compojure online</p>")
  ;serve static pages saved in resources/public
  (route/resources "/")
  (route/not-found "Page not found."))

(def handler
  (handler/site app-routes))
