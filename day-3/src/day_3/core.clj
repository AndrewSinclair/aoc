(ns day-3.core
  (:use [day-3.algo])
  (:gen-class))

(import '(java.io BufferedReader FileReader))

(defn get-input [file-name]
  (line-seq (BufferedReader. (FileReader. file-name))))

(defn -main
  "Advent of Code - day 3 solutions."
  [& args]
  (let [input (get-input "resources/input.txt")]
    (do
      (println (do-algo-1 input))
      (println (do-algo-2 input)))))
