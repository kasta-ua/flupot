(ns flupot.dom
  (:refer-clojure :exclude [map meta time])
  (:require [clojure.core :as core]
            [clojure.string :as str]
            [flupot.core :as flupot]
            [flupot.core.parsing :as p]))

(def tags
  '[a abbr address area article aside audio b base bdi bdo big blockquote body br
    button canvas caption cite code col colgroup data datalist dd del details dfn
    dialog div dl dt em embed fieldset figcaption figure footer form h1 h2 h3 h4 h5
    h6 head header hr html i iframe img input ins kbd keygen label legend li link
    main map mark menu menuitem meta meter nav noscript object ol optgroup option
    output p param picture pre progress q rp rt ruby s samp script section select
    small source span strong style sub summary sup table tbody td textarea tfoot th
    thead time title tr track u ul var video wbr])

(defn kebab-case->camel-case
  "Converts from kebab case to camel case, eg: on-click to onClick"
  [input]
  (let [words      (str/split input #"-")
        capitalize (->> (rest words)
                        (core/map #(apply str (str/upper-case (first %)) (rest %))))]
    (apply str (first words) capitalize)))

(defn- attrs->react [attrs]
  (flupot/clj->js
    (reduce-kv
      (fn [m k v]
        (assoc m
          (case k
            :class "className"
            :for   "htmlFor"
            (kebab-case->camel-case (name k)))
          v))
      {}
      attrs)))

(defmacro define-dom-fns []
  `(do ~@(for [t tags]
           `(flupot/defelement-fn ~t
              :elemf ~(name t)
              :attrf attrs->react))))

(defmacro define-dom-macros []
  `(do ~@(for [t tags]
           `(flupot/defelement-macro ~t
              :elemf ~(name t)
              :attrf attrs->react
              :attrm attrs->react))))

(define-dom-macros)
