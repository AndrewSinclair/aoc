(ns day-24.algo
  (:require [clojure.math.combinatorics :as combo]))

(defn get-filtered
  [input sum-target max-eles]
  (let [combos (combo/combinations input max-eles)]
    (filter #(= (apply + %) sum-target) combos)))

(defn algo
  [input sum-target max-eles]
  (let [subsets (get-filtered input sum-target max-eles)]
    (apply min (map #(apply * %) subsets))))

(defn do-algo-1
  [input]
  (let [sum-target (/ (apply + input) 3)
        max-eles   6] ; I got this number from analysing the data with a spreadsheet
    (algo input sum-target max-eles)))


(defn do-algo-2
  [input]
  (let [sum-target (/ (apply + input) 4)
        max-eles   5 ; I got this number from analysing the data with a spreadsheet
        subsets    (get-filtered input sum-target max-eles)]
    (algo input sum-target max-eles)))

