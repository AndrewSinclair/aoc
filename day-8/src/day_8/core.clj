(ns day-8.core
  (:use [day-8.algo]
        [day-8.input])
  (:gen-class))

(def input-name "resources/input.txt")

(defn -main
  "Day 8 - Advent of Code - Code Representation versus In-Memory Representation of Strings"
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-2 (get-input input-name)))
  ))