(ns day-15.input
  (:require [clojure.string :as str]))

(defn parse-input
  [line]
  )

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
   (doall (map parse-input (line-seq reader)))))