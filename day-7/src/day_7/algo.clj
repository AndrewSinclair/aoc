(ns day-7.algo)

(defn get-gate-fn
  [gate]
  (cond
    (= gate :not)
      (fn [input-a _      ] (bit-and 0x0000FFFF (bit-not input-a)))
    (= gate :and)
      (fn [input-a input-b] (bit-and 0x0000FFFF (bit-and input-a input-b)))
    (= gate :or)
      (fn [input-a input-b] (bit-and 0x0000FFFF (bit-or input-a input-b)))
    (= gate :lshift)
      (fn [input-a input-b] (bit-and 0x0000FFFF (bit-shift-left input-a input-b)))
    (= gate :rshift)
      (fn [input-a input-b] (bit-and 0x0000FFFF (bit-shift-right input-a input-b)))
    :else ; numeric constant
      (fn [input-a _      ] (bit-and 0x0000FFFF input-a))))

(defn get-value-fn
  [input-key]
  (fn
    [value-table]
    (cond
      (number? input-key)
        input-key
      (not (nil? input-key))
        ((get value-table input-key) value-table))))

(defn command-lookup-mapper
  [command]
  (let [output   (:output command)
        input-a  (get-value-fn (first (:input command)))
        input-b  (get-value-fn (second (:input command)))
        gate-fn  (get-gate-fn (:gate command))
        value-fn (fn [value-table] (gate-fn (input-a value-table) (input-b value-table)))]
    [output value-fn]))

(defn table
  [commands]
  (into {} (map command-lookup-mapper commands)))

(defn do-algo-1
  ""
  [commands]
  ((get-value-fn "a") (table commands)))

(defn do-algo-2
  ""
  [commands]
  nil)