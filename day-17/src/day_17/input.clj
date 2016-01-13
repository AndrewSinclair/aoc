(ns day-17.input)

(defn get-inputs
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
   (apply vector (map #(Integer/parseInt %) (line-seq reader)))))