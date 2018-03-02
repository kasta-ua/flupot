(ns flupot.dom
  (:refer-clojure :exclude [map meta time])
  (:require-macros [flupot.dom :as dom])
  (:require cljsjs.react
            [clojure.string :as str]
            [flupot.core :as flupot]))

(defn kebab-case->camel-case
  "Converts from kebab case to camel case, eg: on-click to onClick"
  [input]
  (let [words      (str/split (name input) #"-")
        capitalize (->> (rest words)
                        (map #(apply str (str/upper-case (first %)) (rest %))))]
    (apply str (first words) capitalize)))


(defn- attrs->react [attrs]
  (reduce-kv
   (fn [o k v]
     (let [k (name k)]
       (case k
         "class" (aset o "className" v)
         "for"   (aset o "htmlFor" v)
         (aset o (kebab-case->camel-case k) (clj->js v)))
       o))
   (js-obj)
   attrs))

(dom/define-dom-fns)
