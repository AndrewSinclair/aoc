(ns day-9.algo)

(defn permutations [s]
  (lazy-seq
    (if (seq (rest s))
      (apply concat (for [x s]
                      (map #(cons x %) (permutations (remove #{x} s)))))
      [s])))

(defn get-cities
  [city-distances]
  (keys city-distances))

(defn find-distance
  [distances city-a city-b]
  (let [get-distance-fn (fn [city-1 city-2] (get (apply assoc {} (get distances city-1)) city-2))
        distance-a      (get-distance-fn city-a city-b)
        distance-b      (get-distance-fn city-b city-a)]
    (or distance-a distance-b)))

(defn calc-distance
  [distances cities]
  (loop [[city-a & tail] cities
         total-distance 0]
    (if (nil? tail)
      total-distance
      (let [city-b (first tail)]
        (recur tail (+ total-distance (find-distance distances city-a city-b)))))))

(defn calc-path
  [metric distances permutations]
  (apply metric (map #(calc-distance distances %) permutations)))

(defn do-algo-1
  "Calculate the minimum weighted Ham-Path (TSP)"
  [city-distances]
  (let [cities            (get-cities city-distances)
        city-permutations (permutations cities)]
    (calc-path min city-distances city-permutations)))

(defn do-algo-2
  "Calculate the maximum weighted Ham-Path (TSP but maxed)"
  [city-distances]
  (let [cities            (get-cities city-distances)
        city-permutations (permutations cities)]
    (calc-path max city-distances city-permutations)))