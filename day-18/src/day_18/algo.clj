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
      and (>= (first  %) 0)
          (>= (second %) 0)
          (<  (first  %) max-grid-size)
          (<  (second %) max-grid-size))
      neighbors))
    )

(defn update-x-y
  [grid [x y value] _]
  (let [grid          (if (> (count grid) y) grid (assoc grid y []))
        row-y         (nth grid y)
        updated-row-y (assoc row-y x value)]
    (assoc grid y updated-row-y)))

(defn update-x-y-fixed-corners
  [grid [x y value] max-grid-size]
  (let [value         (if (and
                            (or (= x 0) (= x (dec max-grid-size)))
                            (or (= y 0) (= y (dec max-grid-size))))
                        :on
                        value)
        grid          (if (> (count grid) y) grid (assoc grid y []))
        row-y         (nth grid y)
        updated-row-y (assoc row-y x value)]
    (assoc grid y updated-row-y)))

(defn get-x-y
  [grid x y]
  (nth (nth grid y) x))

(defn map-neighbor-vals
  [grid x y max-grid-size]
  (map #(get-x-y grid (first %) (second %)) (get-neighbor-coords x y max-grid-size)))

(defn next-state
  [grid x y max-grid-size]
  (let [neighbor-vals (map-neighbor-vals grid x y max-grid-size)
        count-on      (count (filter #(= % :on) neighbor-vals))
        curr-state    (get-x-y grid x y)]
    (cond
      (and (= curr-state :on)
           (not (or (= count-on 2) (= count-on 3))))
        :off
      (and (= curr-state :off) (= count-on 3))
        :on
      :else
        curr-state)))

(defn update-grid
  [grid max-grid-size update-algo]
  (let [cells (for [x (range max-grid-size)
                    y (range max-grid-size)
                    :let [cell-value (next-state grid x y max-grid-size)]]
                [x y cell-value])]
    (reduce #(update-algo %1 %2 max-grid-size) [] cells)))

(defn do-algo-1
  [grid max-grid-size num-iterations]
  (count (filter #(= % :on) (flatten 
  (nth (iterate #(update-grid % max-grid-size update-x-y) grid) num-iterations)))))

(defn modify-start-cells
  [grid max-grid-size]
  (reduce #(update-x-y %1 %2 nil)
    grid
    [[0 0 :on]
     [0 (dec max-grid-size) :on]
     [(dec max-grid-size) 0 :on]
     [(dec max-grid-size) (dec max-grid-size) :on]]))

(defn do-algo-2
  [grid max-grid-size num-iterations]
  (let [start-grid (modify-start-cells grid max-grid-size)]
    (count (filter #(= % :on) (flatten 
    (nth (iterate #(update-grid % max-grid-size update-x-y-fixed-corners) start-grid) num-iterations))))))
