(ns day-13.core
  (:use [day-13.algo]
        [day-13.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 13 - Advent of Code - Optimize the happiness of the seating arrangements"
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-2 (get-input input-name)))))