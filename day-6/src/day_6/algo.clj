(ns day-6.algo)

(defn to-indices
  [command size]
  (let [rect  (:rect command)
        pt1   (:pt1 rect)
        pt2   (:pt2 rect)
        x1    (if (< (:x pt1) (:x pt2)) (:x pt1) (:x pt2))
        y1    (if (< (:y pt1) (:y pt2)) (:y pt1) (:y pt2))
        x2    (if (> (:x pt1) (:x pt2)) (:x pt1) (:x pt2))
        y2    (if (> (:y pt1) (:y pt2)) (:y pt1) (:y pt2))
        horiz (inc (- x2 x1))
        vert  (inc (- y2 y1))]
    (for [x (range horiz)
          y (range vert)
          :let [x0 (+ x x1)
                y0 (+ y y1)
                idx  (+ (* size x0) y0)]]
      idx)))

(defn to-command-fn
  [command algo]
    (if (= algo :1)
      (cond
        (= (:command command) :on)
          (fn [_] :on)
        (= (:command command) :off)
          (fn [_] :off)
        (= (:command command) :toggle)
          (fn [state] (if (= state :on) :off :on)))
      (cond
        (= (:command command) :on)
          (fn [brightness] (inc brightness))
        (= (:command command) :off)
          (fn [brightness] (if (> brightness 1) (dec brightness) 0))
        (= (:command command) :toggle)
          (fn [brightness] (+ 2 brightness))
        )))

(defn apply-commands
  [lights commands size algo]
  (let [to-fn-idx-list (fn [command]
                         {:fn (to-command-fn command algo)
                          :indices (to-indices command size)})
        fn-idx-lists (map to-fn-idx-list commands)]
    (loop [[head & tail] fn-idx-lists
          accum  lights]
      (if (nil? head)
        accum
        (let [update-lights (fn [lights idx] (update-in lights [idx] (:fn head)))]
          (recur tail (reduce update-lights accum (:indices head))))))))

(defn count-lights
  [commands size algo]
  (if (= algo :1)
    (let [lights (vec (repeat (* size size) :off))]
      (count (filter #(= :on %) (apply-commands lights commands size algo))))
    (let [lights (vec (repeat (* size size) 0))]
      (reduce + (apply-commands lights commands size algo)))))

(defn do-algo-1
  "Counts the lights that are on after following the commands"
  [commands size]
  (count-lights commands size :1))

(defn do-algo-2
  "Sums the brightness level of the lights after following the commands"
  [commands size]
  (count-lights commands size :2))