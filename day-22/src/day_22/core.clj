(ns day-22.core
  (:use [day-22.algo]
        [day-22.input])
  (:gen-class))

(defn -main
  "Advent of Code - Day 22 - RPG part deux"
  [& args]
  (do
    (println (do-algo-1 wizard fighter))
    (println (do-algo-2 wizard-hard fighter))))
