(ns day-17.algo
	(:require [clojure.math.combinatorics :as combos]))

(defn do-algo-1
  [inputs]
  ; super inefficient but sufficient for current problem size I think
  (count
    (filter #(= 150 (reduce (fn [a v] (+ a v)) %))
      (next (combos/subsets inputs)))))
	  
(defn do-algo-2
  [inputs]
  )