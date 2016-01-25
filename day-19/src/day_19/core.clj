(ns day-19.core
  (:use [day-19.input]
        [day-19.algo])
  (:gen-class))

(def file-name "resources/input.txt")

(defn -main
  "Day 19 - Advent of Code"
  [& args]
  (do
    (println (do-algo-1 (get-input file-name)))
    (println (do-algo-2 (get-input file-name)))))
