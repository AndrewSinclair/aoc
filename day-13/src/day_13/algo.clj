(ns day-13.algo)

(defn permutations [s]
  "Found this on Clojure Docs somewhere"
  (lazy-seq
    (if (seq (rest s))
      (apply concat (for [x s]
                      (map #(cons x %) (permutations (remove #{x} s)))))
      [s])))

(defn calc-happiness
  [happiness-scores arrangement add-self?]
  (let [first-person (first arrangement)]
    (loop [[person & tail] arrangement
          accum 0]
      (if (nil? tail)
        (if add-self?
          accum
          (+ accum
            (last (first (filter #(and (= (first %) first-person) (= (second %) person)) happiness-scores)))
            (last (first (filter #(and (= (first %) person) (= (second %) first-person)) happiness-scores)))))
        (let [
          second-person (first tail)
          accum
            (+ accum
              (last (first (filter #(and (= (first %) person) (= (second %) second-person)) happiness-scores)))
              (last (first (filter #(and (= (first %) second-person) (= (second %) person)) happiness-scores))))]
          (recur tail accum))))))

(defn get-people
  [happiness-scores]
  (distinct (map first happiness-scores)))

(defn do-algo-1
  "Search all permutations of the seating arrangment to maximize happiness scores."
  [happiness-scores]
  (let [people (get-people happiness-scores)]
    (apply max (map #(calc-happiness happiness-scores % false) (permutations people)))))

(defn do-algo-2
  "Search all permutations of the seating arrangment to maximize happiness scores,
  but also add yourself as a +-0 dummy value at the end of every permutation."
  [happiness-scores]
  (let [people (get-people happiness-scores)]
    (apply max (map #(calc-happiness happiness-scores % true) (permutations people)))))
