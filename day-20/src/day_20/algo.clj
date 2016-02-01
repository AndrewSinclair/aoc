(ns day-20.algo
  (:require [clojure.math.numeric-tower :as math]))

(declare r-sum-divisors)

;https://crossclj.info/fun/clojure.contrib.lazy-seqs/primes.html
(def primes
  (concat 
   [2 3 5 7]
   (lazy-seq
    (let [primes-from
    (fn primes-from [n [f & r]]
      (if (some #(zero? (rem n %))
          (take-while #(<= (* % %) n) primes))
        (recur (+ n f) r)
        (lazy-seq (cons n (primes-from (+ n f) r)))))
    wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
      6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
      2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))

(defn next-prime [n]
  (->>  primes
        (filter (partial < n))
        first))

(defn closed-form-sum
  [prime exponent]
  (quot
    (dec (math/expt prime (inc exponent)))
    (dec prime)))

(defn maximize-exponent
  [n p]
  (loop [k 0]
    (let [p-to-k (math/expt p (inc k))]
      (cond
        (= n p-to-k)
          [p (inc k)]
        (not (= 0 (rem n p-to-k)))
          [p k]
        :else
          (recur (inc k))))))

(def m-sum-divisors (memoize
  (fn [n]
    (r-sum-divisors n))))

(defn r-sum-divisors
  ([n]
    (if (.isProbablePrime (BigInteger/valueOf n) 5)
      (closed-form-sum n 1)
      (r-sum-divisors n 2)))
  ([n p]
    (if (= n p)
      (closed-form-sum n 1)
      (if (= 0 (rem n p))
        (let [[prime exp] (maximize-exponent n p)
              factor      (math/expt prime exp)]
          (if (= factor n)
            (closed-form-sum prime exp)
            (* (closed-form-sum prime exp)
               (m-sum-divisors (quot n factor)))))
        (recur n (next-prime p))))))

(defn sum-of-divisors-of-n-less-than-k
  [n k]
  (reduce +
    (for [i (range 2 k)
          :when (= 0 (rem n i))]
      i)))
		
(defn do-algo-1
  [puzzle-input]
  (let [total-elf-visits (quot puzzle-input 10)]
    (->>  (range)
          (drop 2)
          (filter #(->> % m-sum-divisors (<= total-elf-visits)))
          first)))

(defn do-algo-2
  [puzzle-input]
  (let [total-elf-visits (quot puzzle-input 11)]
    (->>  (range)
          (drop 2)
          (filter #(< total-elf-visits (- (m-sum-divisors %) (sum-of-divisors-of-n-less-than-k % (quot % 50)))))
          first)))
