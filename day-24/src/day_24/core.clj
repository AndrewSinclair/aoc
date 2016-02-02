(ns day-24.core
  (:use [day-24.input]
        [day-24.algo])
  (:gen-class))

(defn -main
  "Advent Of Code - Day 24"
  [& args]
  (do
    (println (do-algo-1 (get-input file-name)))
    (println (do-algo-2 (get-input file-name)))))

