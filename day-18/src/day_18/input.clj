(ns day-18.input)

(defn map-line
  [line]
  (apply vector (map #(if (= % \#) :on :off) line)))

(defn get-input
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (apply vector (map map-line (line-seq reader)))))