(ns day-12.core
  (:use [day-12.input]
        [day-12.algo])
  (:gen-class))

(def file-name "resource/input.txt")

(defn -main
  "Advent of Code - Day 12 - Json File"
  [& args]
  (do
    ;(println (do-algo-1 (get-input file-name)))
    (println (do-algo-2 (get-input file-name)))
    ))
