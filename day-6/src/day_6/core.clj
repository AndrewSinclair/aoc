(ns day-6.core
  (:use [day-6.algo]
        [day-6.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 6 - Advent of Code - Draw patterns in 1000 by 1000 grid of 'lights'"
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
   ; (println (do-algo-2 (get-input input-name)))
    ))
