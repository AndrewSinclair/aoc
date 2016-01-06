(ns day-1-1.core
  (:use [day-1-1.algo])
  (:gen-class))

(defn get-input [fileName]
  (slurp fileName))

(defn -main
  "Advent of Code - Day 1 - Counts parentheses"
  [& args]
  (let [input (get-input "resources/input.txt")]
    (do
      (println (do-algo-1 input))
      (println (do-algo-2 input)))))
