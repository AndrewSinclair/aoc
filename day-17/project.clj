(defproject day-17 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
				 [org.clojure/math.combinatorics "0.1.1"]]
  :main ^:skip-aot day-17.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
