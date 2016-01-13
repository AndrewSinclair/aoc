(ns day-17.algo
  (:require [clojure.math.combinatorics :as combos]))

(defn get-subset
  [inputs subset]
  (map #(get inputs %) subset))

(defn subsets-summing-to-total
  [inputs total]
  (let [input-length   (count inputs)
        subset-indices (next (combos/subsets (take input-length (range))))
        subsets        (map (partial get-subset inputs) subset-indices)]
    (filter
      #(= total (reduce (fn [a v] (+ a v)) %))
      subsets)))

(defn do-algo-1
  "Subset Sum Algorithm - Brute force Finds all subsets and filters by sum equal to target."
  [inputs eggnog-total]
  (count (subsets-summing-to-total inputs eggnog-total)))

(defn do-algo-2
  "Calculates the Subset Sums, then finds the quantity of subsets using the smallest number of elements."
  [inputs eggnog-total]
  (let [candidate-sums (subsets-summing-to-total inputs eggnog-total)
        smallest-set   (apply min (map count candidate-sums))]
    (count (filter #(= (count %) smallest-set) candidate-sums))))