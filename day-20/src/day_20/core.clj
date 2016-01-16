(ns day-20.core
  (:use [day-20.algo])
  (:gen-class))

(def input 33100000)

(defn -main
  "Day 20 - Advent of Code - Elves are delivering presents and you have to factor integers."
  [& args]
  (do
    (println (do-algo-1 input))
    (println (do-algo-2 input))))

