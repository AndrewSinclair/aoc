(ns day-3.algo)

(defn find-next-house
  [direction curr-house]
  (cond
    (= \< direction) [(dec (first curr-house)) (second curr-house)]
    (= \> direction) [(inc (first curr-house)) (second curr-house)]
    (= \^ direction) [(first curr-house) (inc (second curr-house))]
    (= \v direction) [(first curr-house) (dec (second curr-house))]
    ))

(defn update-log
  [house log]
  (conj log house))

(defn unique-santa-visits
  [instructions]
  (loop [[direction & tail] instructions
         curr-house         [0 0]
         visit-log          #{[0 0]}]
    (if-not (nil? direction)
      (let [next-house  (find-next-house direction curr-house)
            updated-log (update-log next-house visit-log)]
        (recur tail next-house updated-log))
      visit-log)))

(defn do-algo-1
  "Figures out how many houses Santa visited based on the Elf's directions."
  [instructions]
  (count (unique-santa-visits instructions)))

(defn do-algo-2
  "Figures out how many houses Santa and the robot visited based on
   alternating consumption of the Elf's directions."
  [instructions]
  nil)