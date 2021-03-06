(ns day-17.core
  (:use [day-17.input]
        [day-17.algo])
  (:gen-class))

(def file-name "resources/input.txt")
(def eggnog-total 150)
  
(defn -main
  "Day 17 - Advent of Code - Calculate the Xmas related thing. It's Subset Sum, guys."
  [& args]
  (do 
    (println (do-algo-1 (get-inputs file-name) eggnog-total))
    (println (do-algo-2 (get-inputs file-name) eggnog-total))))
