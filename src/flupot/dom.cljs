(ns flupot.dom
  (:refer-clojure :exclude [map meta time])
  (:require-macros [flupot.dom :as dom])
  (:require cljsjs.react))

(def ^:private attr-opts
  (dom/generate-attr-opts))

(defn- push-child! [args child]
  (if (seq? child)
    (doseq [c child]
      (push-child! args c))
    (.push args child)))

(defn- attrs->react [m]
  (reduce-kv
   (fn [o k v]
     (let [k (name k)]
       (aset o (or (aget attr-opts k) k) (clj->js v))
       o))
   (js-obj)
   m))

(dom/define-dom-fns)
