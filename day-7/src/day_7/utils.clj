(ns day-7.utils)

(defn numeric? [s]
  "http://rosettacode.org/wiki/Determine_if_a_string_is_numeric#Clojure"
  (if-let [s (seq s)]
    (let [s (if (= (first s) \-) (next s) s)
          s (drop-while #(Character/isDigit %) s)
          s (if (= (first s) \.) (next s) s)
          s (drop-while #(Character/isDigit %) s)]
      (empty? s))))