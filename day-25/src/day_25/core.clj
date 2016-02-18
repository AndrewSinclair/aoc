(ns day-25.core
  (:use [day-25.input]
        [day-25.algo])
  (:gen-class))

(defn -main
  "Advent Of Code - Day 25"
  [& args]
  (do
    (println (do-algo-1 2981 3075))
    (println (do-algo-2 2981 3075))))

