(ns day-19.input)

(defn to-chemical-map
  [text chemical-map]
    (let [[chemical-symbol replacement] (re-find #".+?(\d+).+?(\d+).+?(\d+).+?(\d+)" line)]
      (assoc chemical-map chemical-symbol replacement))
  )

(defn get-input
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (let [input-lines   (line-seq reader)
          chemicals     (reduce to-chemical-map {} (take-while #(complement (= "\n" %)) input-lines))
          initial-input (last input-lines)]
      [chemicals initial-input])))