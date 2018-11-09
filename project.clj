(defproject ua.modnakasta/flupot "0.5.3"
  :description "ClojureScript functions for creating React elements"
  :url "https://github.com/weavejester/flupot"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.946" :scope "provided"]
                 [cljsjs/react-dom "16.2.0-3" :scope "provided"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild
  {:builds
   {:main
    {:source-paths ["src"]
     :compiler     {:output-to "target/main.js"}}}})
