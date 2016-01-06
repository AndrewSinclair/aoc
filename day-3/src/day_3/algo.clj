(ns day-3.algo
  (:use clojure.set))

(defn find-next-house
  [direction curr-house]
  (cond
    (= \< direction) [(dec (first curr-house)) (second curr-house)]
    (= \> direction) [(inc (first curr-house)) (second curr-house)]
    (= \^ direction) [(first curr-house) (inc (second curr-house))]
    (= \v direction) [(first curr-house) (dec (second curr-house))]
    ))

(defn unique-visits
  "consumes the instructions and logs the current house coords into a set"
  [instructions]
  (loop [[direction & tail] instructions
         curr-house         [0 0]
         visit-log          #{[0 0]}]
    (if-not (nil? direction)
      (let [next-house  (find-next-house direction curr-house)
            updated-log (conj visit-log next-house)]
        (recur tail next-house updated-log))
      visit-log)))
	  
(defn unique-santa-and-robot-visits
  "Figures out how many unique houses were visited by santa and the robot."
  [instructions]
  (let [santa-visit-log (unique-visits (take-nth 2 instructions))
        robot-visit-log (unique-visits (take-nth 2 (next instructions)))]
    (union santa-visit-log robot-visit-log)))

(defn do-algo-1
  "Figures out how many houses Santa visited based on the Elf's directions."
  [instructions]
  (count (unique-visits instructions)))

(defn do-algo-2
  "Figures out how many houses Santa and the robot visited based on
   alternating consumption of the Elf's directions."
  [instructions]
  (count (unique-santa-and-robot-visits instructions)))
