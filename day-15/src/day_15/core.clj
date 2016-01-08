(ns day-15.core
  (:use [day-15.algo]
        [day-15.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 15 - Advent of Code - "
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-2 (get-input input-name)))))