(ns day-1-1.core
  (:use [day-1-1.algo])
  (:gen-class))

(defn -main
  "Advent of Code - day 1-1 solution."
  [& args]
  (let [input (get-input "resources/input.txt")]
    (do
      (println (do-algo-1 input))
      (println (do-algo-2 input)))))
