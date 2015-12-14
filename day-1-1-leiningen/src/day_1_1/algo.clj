(ns day-1-1.algo
  (:use
    [clojure.core.match :refer [match]]))

(defn get-input [fileName]
  (slurp fileName))

(defn do-algo-1
  "Counts the parens and figures out what floor santa is on"
  [input]

  (reduce + 
    (map
      #(if (= % \() 1 -1)
      input)))

(defn do-algo-2
  "Counts the parens and finds the index when santa enters the basement"
  [input]

  (loop [[paren & rest] input
         index 0
         accum 0]
    (if-not (or (neg? accum) (nil? paren))
      (recur rest (inc index) (+ accum (if (= paren \() 1 -1)))
      index
    )
  )
)