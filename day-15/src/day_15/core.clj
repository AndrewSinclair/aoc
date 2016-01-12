(ns day-15.core
  (:use [day-15.input]
        [day-15.algo])
  (:gen-class))

(defn -main
  "Day 15 - Advent Of Code - Make the best cookies. Essentially same algorithmic pattern as 0-1 KP"
  [& args]
  (let [teaspoons 100
        calories  500]
    (do
      (println (do-algo-1 inputs teaspoons))
      (println (do-algo-2 inputs teaspoons calories)))))
