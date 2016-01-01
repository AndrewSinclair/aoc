(ns day-10.algo)

(defn look-and-say
  [int-list]
  (let [partitioned (partition-by identity int-list)
        seq-length  (map #(list (count %) (first %)) partitioned)]
    (flatten seq-length)))

(defn make-int-list
  [number]
  (map #(- (int %) (int \0)) number))

(defn do-algo-1
  "Counts the digits after playing the look-and-say game 40 times"
  [number]
  (let [int-list (make-int-list number)]
    (count (nth (iterate look-and-say int-list) 40))))

(defn do-algo-2
  "Counts the digits after playing the look-and-say game 50 times. Takes about 20 seconds on modern hardware."
  [number]
  (let [int-list (make-int-list number)]
    (count (nth (iterate look-and-say int-list) 50))))
