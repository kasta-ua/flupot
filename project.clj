(defproject flupot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3211" :scope "provided"]]
  :plugins [[lein-cljsbuild "1.0.5"]]
  :cljsbuild
  {:test-commands {"unit-tests" ["phantomjs" :runner "target/main.js"]}
   :builds {:main {:source-paths ["src"], :compiler {:output-to "target/main.js"}}}}
  :profiles
  {:dev {:plugins [[com.cemerick/clojurescript.test "0.3.3"]]
         :cljsbuild {:builds {:main {:source-paths ["test"]}}}}})
