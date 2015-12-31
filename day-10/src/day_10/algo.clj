(ns day-10.algo)

(defn algo-fn
  [number]
  (let [partitioned-chars (map #(list (count %) (first %)) (partition-by identity number))]
    (reduce str (map #(str (first %) (second %)) partitioned-chars))))

(defn do-algo-1
  "Counts the digits after playing the look-and-say game 40 times"
  [number]
  (count (nth (iterate algo-fn number) 40)))

(defn do-algo-2
  "Counts the digits after playing the look-and-say game 50 times"
  [strings]
  (count (nth (iterate algo-fn number) 50)))