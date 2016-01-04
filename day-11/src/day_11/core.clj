(ns day-11.core
  (:use [day-11.algo])
  (:gen-class))

(def get-input "cqjxjnds")

(defn -main
  "Day 11 - Advent of Code - Iterate passwords based on given requirements"
  [& args]
  (do
    (println (do-algo-1 get-input))
    (println (do-algo-2 get-input))
  ))
