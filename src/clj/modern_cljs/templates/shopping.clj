(ns modern-cljs.templates.shopping
  (:require [net.cgrand.enlive-html :refer [deftemplate set-attr]]
            [modern-cljs.remotes :as r]))

(deftemplate shopping "public/shopping.html"
  [quantity price tax discount]
  [:#quantity] (set-attr :value quantity)
  [:#price] (set-attr :value price)
  [:#tax] (set-attr :value tax)
  [:#discount] (set-attr :value discount)
  [:#total] (set-attr :value (format "%.2f" (double (r/calculate quantity price tax discount)))))
