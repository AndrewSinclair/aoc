(ns day-7.algo)

(defn get-gate-fn
  [gate]
  (let [unary-combinator  (fn [op-fn] (fn [input-a _ value-table] (op-fn (get-value value-table input-a))))
        binary-combinator (fn [op-fn] (fn [input-a input-b value-table] (op-fn (get-value value-table input-a input-b))))]
    (cond
      (= gate :not)
        (unary-combinator bit-not)
      (= gate :and)
        (binary-combinator bit-and)
      (= gate :or)
        (binary-combinator bit-or)
      (= gate :lshift)
        (binary-combinator bit-shift-left) ;how do I make sure this is a 16 bit integer?
      (= gate :rshift)
        (binary-combinator bit-shift-right))))

(defn create-initial-value-table
  [commands]
  (into {} (map
    (fn
      [command]
      (let [key          (:output command)
            get-value-fn (fn [input-key] (fn [value-table] (if (number? input-key) input-key (get value-table input-key))))
            input-a      (get-value-fn (first (:input command)))
            input-b      (get-value-fn (second (:input command)))
            gate-fn      (get-gate-fn (:gate command))
            value        (fn [value-table] (gate-fn (input-a value-table) (input-b value-table)))]
        [key value]))
    commands)))

(defn do-algo-1
  ""
  [commands]
  (:a
    (loop [value-table create-initial-value-table
           curr-output :a]
      ;(if curr output is a number return that)
      ;(else find the value in the value-table)
)))

(defn do-algo-2
  ""
  [commands]
  nil)