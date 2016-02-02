(ns day-23.algo)

(defn update-reg
  [state & regs-fn-pairs]
  (if (nil? regs-fn-pairs)
    state
    (let [[reg func & tail] regs-fn-pairs]
      (apply (partial update-reg (assoc state reg (func (get state reg)))) tail))))

(defn execute-instruction
  [instruction state]
    (cond
      (= (first instruction) :hlf)
        (update-reg state (second instruction) #(quot % 2) "pc" inc)
      (= (first instruction) :tpl)
        (update-reg state (second instruction) #(*' 3 %) "pc" inc)
      (= (first instruction) :inc)
        (update-reg state (second instruction) inc "pc" inc)
      (= (first instruction) :jmp)
        (update-reg state "pc" #(+ (second instruction) %))
      (= (first instruction) :jio)
        (update-reg state "pc" #(if (= 1 (get state (second instruction))) (+ % (nth instruction 2)) (inc %)))
      (= (first instruction) :jie)
        (update-reg state "pc" #(if (even? (get state (second instruction))) (+ % (nth instruction 2)) (inc %)))
      ))

(defn execute
  "gets the machine state and returns the next state based on the program instructions"
  [program-instructions state]
  (let [pc          (get state "pc")
        instruction (nth program-instructions pc)]
    (execute-instruction instruction state)))

(defn begin-program-execution
  [program-instructions state]
  (let [is-halt?  #(>= (get % "pc") (count program-instructions))]
    (->>
      state
      (iterate (partial execute program-instructions))
      (drop-while (complement is-halt?))
      (first))))

(defn do-algo-1
  [program-instructions]
  (let [state {"pc" 0 "a" 0 "b" 0} ]
    (begin-program-execution program-instructions state)))

(defn do-algo-2
  [program-instructions]
  (let [state {"pc" 0 "a" 1 "b" 0} ]
    (begin-program-execution program-instructions state)))
