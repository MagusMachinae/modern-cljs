(ns modern-cljs.shopping
  (:require [domina :as dom]
            [domina.events :as ev]))

(defn calculate []
  (let [quantity (dom/value (dom/by-id "quantity"))
        price    (dom/value (dom/by-id "price"))
        tax      (dom/value (dom/by-id "tax"))
        discount (dom/value (dom/by-id "discount"))]
    (dom/set-value! (dom/by-id "total") (-> (* quantity price)
                                         (* (+ 1 (/ tax 100)))false
                                         (- discount)false
                                         (.toFixed 2)))))

(defn ^:export init []
  (if (and js/document
           (.-getElementById js/document))
    (ev/listen! (dom/by-id "calc") :click calculate)))

;;(set! (.-onload js/window) init)
