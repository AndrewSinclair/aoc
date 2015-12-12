(ns day-4.core
  (:use [day-4.algo]
        [day-4.input])
  (:gen-class))

(defn -main
  "Day 4 - Advent of Code - Create a 'advent coin' miner."
  [& args]
  (do
    (println (do-algo-1 (get-input)))
    (println (do-algo-2 (get-input)))))
