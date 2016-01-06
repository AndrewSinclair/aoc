(ns day-7.algo)

(defn get-gate-fn
  [gate]
  (cond
    (= gate :not)
      (fn [input-a _      ] (bit-not input-a))
    (= gate :and)
      (fn [input-a input-b] (bit-and input-a input-b))
    (= gate :or)
      (fn [input-a input-b] (bit-or input-a input-b))
    (= gate :lshift)
      (fn [input-a input-b] (bit-shift-left input-a input-b))
    (= gate :rshift)
      (fn [input-a input-b] (bit-shift-right input-a input-b))
    :else ; numeric constant
      (fn [input-a _      ] input-a)))

(defn get-recursively-value
  ([commands value]
    (first (get-recursively-value (first (filter #(= (:output %) value) commands)) {} commands)))

  ([command lookup-table commands]
    (let [new-key       (:output command)
          input-a       (first  (:input command))
          input-b       (second (:input command))
          get-recursive (fn [input lookup-table]
            (cond
              (nil? input)
                [nil lookup-table]
              (not (nil? (get lookup-table input)))
                [(get lookup-table input) lookup-table]
              (number? input)
                [input (assoc lookup-table new-key input)]
              :else
                (get-recursively-value (first (filter #(= (:output %) input) commands)) lookup-table commands)))
          [value-a lookup-table] (get-recursive input-a lookup-table)
          [value-b lookup-table] (get-recursive input-b lookup-table)
          gate-fn     (get-gate-fn (:gate command))
          gated-value (gate-fn value-a value-b)]
      [gated-value (assoc lookup-table new-key gated-value)])))

(defn do-algo-1
  "Recursively figure out the bit circuit"
  [commands]
  (get-recursively-value commands "a"))
