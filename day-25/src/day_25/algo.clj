(ns day-25.algo)

(defn map-grid-cell-num
  [row col]
  (+ (/ (* (dec (+ row col)) (- (+ row col) 2)) 2) col))

(defn iterator
  [x]
  (rem (* 252533 x) 33554393))

(defn do-algo-1
  [row col]
  (let [grid-cell-num (map-grid-cell-num row col)]
    (nth (iterate iterator 20151125) (dec grid-cell-num))))

(defn do-algo-2
  [row col]
  nil)

