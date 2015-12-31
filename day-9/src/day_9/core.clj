(ns day-9.core
  (:use [day-9.algo]
        [day-9.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 9 - Advent of Code - Travelling Salesperson with Santa's present route" 
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-2 (get-input input-name)))
  ))