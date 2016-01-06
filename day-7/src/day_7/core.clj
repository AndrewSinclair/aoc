(ns day-7.core
  (:use [day-7.algo]
        [day-7.input])
  (:gen-class))

(def input-name "resources/input.txt")
(def input-name-2 "resources/input2.txt")

(def my-inputs (get-input "resources/input-test.txt"))

(defn -main
  "Day 7 - Advent of Code - Assemble 'Logic' circuits and calculate the output
  I manually updated the input and used the same algorithm in order to do part 2 today"
  [& args]
  (do
    (println (do-algo-1 (get-input input-name)))
    (println (do-algo-1 (get-input input-name-2)))))