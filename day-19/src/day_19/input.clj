(ns day-19.input)

(defn to-chemical-multi-map
  [chemical-map text]
    (let [[_ chemical-symbol replacement] (re-find #"(\w+)\s+=>\s+(\w+)" text)]
      (let [multi-part (get chemical-map chemical-symbol)]
        (if multi-part
          (assoc chemical-map chemical-symbol (conj multi-part replacement))
          (assoc chemical-map chemical-symbol [replacement])))))

(defn get-input
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (let [input-lines    (line-seq reader)
          chemicals      (reduce to-chemical-multi-map {} (take-while #(not (= "" %)) input-lines))
          start-molecule (last input-lines)]
      [chemicals start-molecule])))