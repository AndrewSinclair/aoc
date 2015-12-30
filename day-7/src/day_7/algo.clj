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

(defn get-recursively-value
  [commands]
    (get-recursively-value (first commands) {} commands)

  [command lookup-table commands]
    ;need to find the inputs' values in the table
    ;otherwise it's not there, recursively find it, but always update the table!
    (let [new-key  (:output command)
          input-a  (first  (:input command))
          input-b  (second (:input command))
          [value-a lookup-table]
            (if (nil? input-a)
              (get-recursively-value (find #(= (:output %) input-a) commands) lookup-table commands)
              [input-a (assoc lookup-table new-key input-a)])
          [value-b lookup-table]
            (if (nil? input-b)
              (get-recursively-value (find #(= (:output %) input-b) commands) lookup-table commands)
              [input-b (assoc lookup-table new-key input-b)])
          gate-fn  (get-gate-fn (:gate command))]
      [(gate-fn value-a value-b) lookup-table]))

(defn do-algo-1
  ""
  [commands]
  (get (get-recursively-value commands) "a"))

(defn do-algo-2
  ""
  [commands]
  nil)