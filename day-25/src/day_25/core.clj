(ns day-25.core
  (:use [day-25.input]
        [day-25.algo])
  (:gen-class))

(defn -main
  "Advent Of Code - Day 25"
  [& args]
  (do
    (println (do-algo-1 (get-input file-name)))
    (println (do-algo-2 (get-input file-name)))))

