(ns day-5.core
  (:use [day-5.algo]
        [day-5.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 5 - Advent of Code - Figure out which strings are nice."
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-2 (get-input input-name)))
    ))
