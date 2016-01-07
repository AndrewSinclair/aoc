(ns day-14.algo)

(defn total-distance
  [speed travel-time rest-time total-time]
  (let [time-per-trip           (+ travel-time rest-time)
        num-whole-trips         (quot total-time time-per-trip)
        remainder-of-final-trip (rem total-time time-per-trip)
        progress-of-final-trip  (min remainder-of-final-trip travel-time)
        total-time-travelling   (+ (* travel-time num-whole-trips) progress-of-final-trip)]
     (* total-time-travelling speed)))

(defn do-algo-1
  ""
  [inputs]
  (let [total-time 2503]
   (apply max (map #(apply total-distance (conj % total-time)) inputs))))

(defn do-algo-2
  ""
  [inputs]
  nil)