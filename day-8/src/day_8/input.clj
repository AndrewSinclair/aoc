(ns day-8.input
  (:require [clojure.string :as str]))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (reduce conj [] (line-seq reader))))