(ns day-19.input)

(defn capital-char?
  [x]
  (and
    (>= (int x) (int \A))
    (<= (int x) (int \Z))))

(defn split-up
  [molecule]
  (loop [[a & [b & tail :as remaining]] molecule
         accu      []]
    (if (nil? b)
      (conj accu (str a))
      (if (capital-char? a)
        (if (capital-char? b)
          (recur remaining (conj accu (str a)))
          (recur tail      (conj accu (str a b))))
        (recur remaining accu)))))

(defn to-chemical-multi-map
  [chemical-map text]
  (let [[_ chemical-symbol replacement] (re-find #"(\w+)\s+=>\s+(\w+)" text)
        replacement (split-up replacement)
        multi-part (get chemical-map chemical-symbol)]
    (if multi-part
      (assoc chemical-map chemical-symbol (conj multi-part replacement))
      (assoc chemical-map chemical-symbol [replacement]))))


(defn get-input
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (let [input-lines    (line-seq reader)
          chemicals      (reduce to-chemical-multi-map {} (take-while (complement clojure.string/blank?) input-lines))
          start-molecule (filter #(not (= "" %)) (split-up (last input-lines)))]
      [chemicals start-molecule])))
