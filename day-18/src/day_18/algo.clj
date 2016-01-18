(ns day-18.algo)

(defn get-neighbor-coords
  [x y max-grid-size]
  (let [neighbors [
              [(inc x) (inc y)]
              [(inc x) y      ]
              [(inc x) (dec y)]
              [ x      (inc y)]
              [ x      (dec y)]
              [(dec x) (inc y)]
              [(dec x) y      ]
              [(dec x) (dec y)]]]
    (filter #(
      (and (>= (first  %) 0)
           (>= (second %) 0)
           (<  (first  %) max-grid-size)
           (<  (second %) max-grid-size))
      neighbors))))

(defn update-x-y
  [grid x y value]
  (let [row-y (nth grid y)
        updated-row-y (assoc row-y x value)]
    (assoc grid y updated-row-y)))

(defn get-x-y
  [grid x y]
  (nth (nth grid y) x))

(defn map-neighbor-vals
  [grid x y max-grid-size]
  (map #(get-x-y grid (first %) (second %)) (get-neighbor-coords x y max-grid-size)))

(defn update-state
  [grid x y max-grid-size]
  (let [neighbor-vals (map-neighbor-vals grid x y max-grid-size)
        count-on      (count (filter #(= % :on)  neighbor-vals))
        count-off     (count (filter #(= % :off) neighbor-vals))
        curr-state    (get-x-y x y)]
    (cond
      (not (and (= curr-state :on) (or (= count-on 2) (= count-on 3))))
        (update-x-y grid x y :off)
      (and (= curr-state :off) (= count-on 3))
        (update-x-y grid x y :on)
      :else
        grid)))

(defn update-grid
  [grid max-grid-size]
  (for [x (range max-grid-size)
        y (range max-grid-size)
        :let [grid (update-state grid x y max-grid-size)]]
    grid))

(defn do-algo-1
  [grid max-grid-size num-iterations]
  (count (filter #(= % :on) (flatten 
  (nth (iterate #(update-grid % max-grid-size) grid) num-iterations)))))

(defn do-algo-2
  [inputs]
  nil)