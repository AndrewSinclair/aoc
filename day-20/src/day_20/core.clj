(ns day-20.core
  (:require [clojure.math.numeric-tower :as math])
  (:gen-class))

(defn divisors
  [n]
  (filter (comp zero? (partial rem n)) (range 1 (inc (quot n 2)))))

(comment
(def m-factors (memoize
  (fn [n k]
   (if (= 0 (rem n k))
     k
     (recur n (inc k))))))

(defn factors
  "Return a list of factors of N."
  [n k acc]
  (if (= n k)
    acc
    (let [k2 (m-factors n k)]
      (recur n (inc k) (cons k2 acc)))))

(def factors-of-N (memoize (fn [n] (factors n 2 ()))))
)

(comment
"I think this is actually not strictly necessary and is just more computations overall, so don't use it"
(defn compress-to-divisors
  "run length encoding"
  [elements]
  (->> (partition-by identity elements) (map (juxt first count))))
) ; end comment
  
(defn sum-of-divisors
  ""
  [divisors]
  (reduce + divisors))
  
(defn -main
  "Day 20 - Advent of Code - Elves are delivering presents and you have to factor integers."
  [& args]
  ;(let [puzzle-input 3310000] ; Divided by 10
  (let [puzzle-input (quot (first args) 10)]
    (first (filter #(->> % divisors (cons %) sum-of-divisors (<= puzzle-input)) (drop 2 (range))))))
