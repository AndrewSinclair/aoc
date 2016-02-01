(ns day-21.core
  (:use [day-21.input]
        [day-21.algo])
  (:gen-class))

(defn -main
  "Advent of Code - Day 21 - RPG simulation"
  [& args]
  (do
    (println (do-algo-1))
    (println (do-algo-2))))
