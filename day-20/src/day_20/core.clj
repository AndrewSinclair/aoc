(ns day-20.core
  (:gen-class))

; http://rosettacode.org/wiki/Prime_decomposition#Clojure
(defn factors
  "Return a list of factors of N."
  ([n]
    (factors n 2 ()))
  ([n k acc]
    (if (= 1 n)      
      acc
      (if (= 0 (rem n k))        
        (recur (quot n k) k (cons k acc))
        (recur n (inc k) acc)))))
		
(defn compress-to-divisors
  "run length encoding"
  [elements]
  (->> (partition-by identity elements) (map (juxt first count))))
  
(defn sum-of-divisors
  ""
  [divisors]
  0)
  
(defn -main
  "Day 20 - Advent of Code - Elves are delivering presents and you have to factor integers."
  [& args]
  (let [puzzle-input 33100000]
  (first (filter #(-> % factors compress-to-divisors sum-of-divisors (< puzzle-input)) (range)))))
