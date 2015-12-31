(ns day-10.core
  (:use [day-10.algo]
        [day-10.input])
  (:gen-class))

(defn -main
  "Day 10 - Advent of Code - Play the look-and-say game." 
  [& args]
  (do
    ;(println (do-algo-1 get-input))
    (println (do-algo-2 get-input))
  ))