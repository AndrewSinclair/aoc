(ns day-11.algo)

(defn increasing-straight?
  [password]
  (loop [[letter-1 & tail] password]
    (let [letter-2 (first tail)
          letter-3 (second tail)]
      (if (nil? letter-3) false
        (let [coerce (fn [letter] (- (int letter) (int \a)))
              diff-1 (- (coerce letter-2) (coerce letter-1))
              diff-2 (- (coerce letter-3) (coerce letter-2))]
          (or (and (= diff-1 1) (= diff-2 1))
            (recur tail)))))))

(defn restricted-letters?
  [password]
  (reduce (fn [bool letter] (or bool (some #(= letter %) '(\o \i \l)))) false password))

(defn double-letters?
  [word ignore-letter]
  (loop [letter-a (first word)
        [letter-b & rest] (next word)]
    (cond
      (nil? letter-b)             false
      (= letter-a ignore-letter)  (recur letter-b rest)
      (= letter-a letter-b)       letter-a
      :else                       (recur letter-b rest))))

(defn two-pairs?
  [password]
  (let [letter-a (double-letters? password nil)
        letter-b (double-letters? password letter-a)]
    (and letter-a letter-b)))

(defn letters-to-ints
  [letters]
  (map #(- (int %) (int \a)) letters))

(defn ints-to-letters
  "takes the list of ints from 0-26 and turns them into letters
  note that 26 means a carryover"
  [int-list]
  (let [reversed (reverse int-list)]
    (reverse (
      (fn recursive [[head & tail]]
        (if-not (nil? head)
          (if (= 26 head)
            (cons \a (recursive (cons (inc (first tail)) (next tail))))
            (cons (char (+ head (int \a))) (recursive tail))))) reversed))))

(defn increment
  [old-word]
  (let [reversed-ints (reverse (letters-to-ints old-word))]
    (ints-to-letters (reverse (cons (inc (first reversed-ints)) (next reversed-ints))))))

(defn next-password
  [password n]
  (let [conditions (every-pred
                     increasing-straight?
                     (complement restricted-letters?)
                     two-pairs?)]
    (apply str (nth (filter conditions (iterate increment password)) n))))

(defn do-algo-1
  "Find the next password based on requirements"
  [password]
  (next-password password 0))

(defn do-algo-2
  "Find the second-next password based on requirements"
  [password]
  (next-password password 1))
