(ns day-18.core
  (:use [day-18.input]
        [day-18.algo])
  (:gen-class))

(def file-name "resources/input.txt")
(def test-file-name "resources/test.txt")

(defn -main
  "Day 18 - Advent of Code - Game of Life but with Christmas Lights"
  [& args]
  (do
    (println (do-algo-1 (get-input file-name) 100 100))
    (println (do-algo-2 (get-input file-name) 100 100))))
