(ns modern-cljs.core.templates.shopping
  (:require [net.cgrand.enlive.html] :refer [deftemplate]))

(deftemplate shopping "shopping.html"
  [quantity price tax discount]
  nil nil)
