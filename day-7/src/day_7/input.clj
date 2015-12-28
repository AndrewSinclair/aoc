(ns day-7.input
  (:require [clojure.string :as str]))

(defn parse-gate-output
  [line]
  (re-find #"->\s+(\w+)" line))

(defn parse-input
  [line]
  (let [words  (str/split line #" ")
        output (parse-gate-output line)]
    (cond
      (= (first words) "NOT"))
        {:gate :not :input [(second words)] :output output}
      (= (second words) "AND")
        {:gate :and :input [(first words) (nth 3 words)] :output output}
      (= (second words) "OR")
        {:gate :or :input [(first words) (nth 3 words)] :output output}
      (= (second words) "LSHIFT")
        {:gate :lshift :input [(first words) (nth 3 words)] :output output}
      (= (second words) "RSHIFT")
        {:gate :rshift :input [(first words) (nth 3 words)] :output output}))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (reduce conj [] (map parse-input (line-seq reader)))))