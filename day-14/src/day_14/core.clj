(ns day-14.core
  (:use [day-14.algo]
        [day-14.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 14 - Advent of Code - Reindeer Olympics"
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-2 (get-input input-name)))))