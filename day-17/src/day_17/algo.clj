(ns day-17.algo
  (:require [clojure.math.combinatorics :as combos]))

(defn get-subset
  [inputs subset]
  (map #(get inputs %) subset))

(defn do-algo-1
  [inputs eggnog-total]
  (let [input-length   (count inputs)
        subset-indices (next (combos/subsets (take input-length (range))))
        subsets        (map (partial get-subset inputs) subset-indices)]
      (count
        (filter
          #(= eggnog-total (reduce (fn [a v] (+ a v)) %))
          subsets))))

(defn do-algo-2
  [inputs eggnog-total]
  nil)