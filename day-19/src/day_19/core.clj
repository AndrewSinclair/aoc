(ns day-19.core
  (:use [day-19.input]
        [day-19.algo])
  (:gen-class))

(def file-name "resources/input.txt")
(def test-file-name "resources/test.txt")

(defn -main
  "Day 19 - Advent of Code"
  [& args]
  (let [input (get-input file-name)]
    (do
      ;(println (do-algo-1 input))
      (println (do-algo-2 (get-input test-file-name))))))