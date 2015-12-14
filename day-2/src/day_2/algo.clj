(ns day-2.algo
  (:use [clojure.string :only [split]]))

(defn calc-box-area
  [[l w h]]
  (* 2 (+ (* l w) (* l h) (* w h))))

(defn calc-smallest-area
  [[l w h]]
  (min (* l w) (* l h) (* w h)))

(defn calc-box-volume
  [[l w h]]
  (* l h w))

(defn calc-smallest-perimeter
  [[l w h]]
  (min
    (* 2 (+ l h))
    (* 2 (+ l w))
    (* 2 (+ w h))))

(defn get-dimensions
  [inputs]
  (map (fn [line] (map #(Integer/parseInt %) (split line #"x"))) inputs))

(defn sum [ints] (reduce + ints))

(defn calc-box-wrapping-paper
  [dimension]
  (+ (calc-box-area dimension) (calc-smallest-area dimension)))

(defn calc-box-ribbon
  [dimension]
  (+ (calc-box-volume dimension) (calc-smallest-perimeter dimension)))

(defn do-algo-1
  "Figures out how much wrapping paper the elves need.
  total per box = Total Surface Area + Smallest Side's Area.
  "
  [inputs]
  (let [dimensions (get-dimensions inputs)]
    (sum (map calc-box-wrapping-paper dimensions))))

(defn do-algo-2
  "Figures out how much ribbon the elves need.
  Total per box = Shortest Side's Perimeter + Volume of Box.
  "
  [inputs]
  (let [dimensions (get-dimensions inputs)]
    (sum (map calc-box-ribbon dimensions))))