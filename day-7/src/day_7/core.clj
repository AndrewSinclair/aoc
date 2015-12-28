(ns day-7.core
  (:use [day-7.algo]
        [day-7.input])
  (:gen-class))

(def input-name "resources/input.txt")

(def my-inputs (get-input "resources/input-test.txt"))

(defn -main
  "Day 7 - Advent of Code - Assemble 'Logic' circuits and calculate the output"
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    ;(println (do-algo-2 (get-input input-name)))
    ))