(ns day-7.input
  (:require [clojure.string :as str])
  (:use [day-7.utils]))

(defn parse-gate-output
  [line]
  (second (re-find #"->\s+(\w+)" line)))

(defn parse-gate
  [words output]
  (let [first-word  (first words)
        second-word (second words)
        third-word  (nth words 2)
        first-val   (if (numeric? first-word)  (Integer/parseInt first-word)  first-word)
        second-val  (if (numeric? second-word) (Integer/parseInt second-word) second-word)
        third-val   (if (numeric? third-word)  (Integer/parseInt third-word)  third-word)]
    (cond
      (= first-word "NOT")
        {:gate :not :input [second-val] :output output}
      (= second-word "AND")
        {:gate :and :input [first-val third-val] :output output}
      (= second-word "OR")
        {:gate :or :input [first-val third-val] :output output}
      (= second-word "LSHIFT")
        {:gate :lshift :input [first-val third-val] :output output}
      (= second-word "RSHIFT")
        {:gate :rshift :input [first-val third-val] :output output}
      :else
        {:gate nil :input [first-val] :output output})))

(defn parse-input
  [line]
  (let [words  (str/split line #" ")
        output (parse-gate-output line)]
    (parse-gate words output)))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (reduce conj [] (map parse-input (line-seq reader)))))