(ns day-14.algo)

(defn total-distance
  [total-time speed travel-time rest-time]
  (let [time-per-trip           (+ travel-time rest-time)
        num-whole-trips         (quot total-time time-per-trip)
        remainder-of-final-trip (rem total-time time-per-trip)
        progress-of-final-trip  (min remainder-of-final-trip travel-time)
        total-time-travelling   (+ (* travel-time num-whole-trips) progress-of-final-trip)]
     (* total-time-travelling speed)))

(defn total-distance-one-liner
  "just for fun, this is what the total-distance function would like as a one-liner"
  [a b c d]
    (* (+ (*   (quot d (+ b c)) b )
          (min (rem  d (+ b c)) b))
       a))

(defn get-points
  "returns a vector of each reindeer's points this second"
  [inputs curr-second]
  (let [distances    (map #(apply total-distance curr-second %) inputs)
        max-distance (apply max distances)]
    (apply vector (map #(if (= max-distance %) 1 0) distances))))

(defn do-algo-1
  "Calculates the winner's distance"
  [inputs total-time]
  (apply max (map #(apply total-distance total-time %) inputs)))

(defn do-algo-2
  "Calculates the winner's score by using the new and improved points system"
  [inputs total-time]
  (apply max (apply map + (map #(get-points inputs %) (range 1 (inc total-time))))))