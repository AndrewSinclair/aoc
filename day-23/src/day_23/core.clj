(ns day-23.core
  (:use [day-23.input]
        [day-23.algo])
  (:gen-class))

(def file-name "resources/input.txt")

(defn -main
  "Advent of Code - Day 23"
  [& args]
  (do
    (println (do-algo-1 (get-input file-name))
    (println (do-algo-2 (get-input file-name))))))

