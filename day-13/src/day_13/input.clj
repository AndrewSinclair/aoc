(ns day-13.input
  (:require [clojure.string :as str]))

(defn parse-input
  [line]
  (let [names        (re-seq #"[A-Z]\w+" line)
        name-1       (first  names)
        name-2       (second names)
        is-lose      (re-find #"lose" line)
        happiness    (re-find #"\d+"  line)
        happy-amount (Integer/parseInt (if is-lose (str "-" happiness) happiness))]
    [name-1 name-2 happy-amount]))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
   (doall (map parse-input (line-seq reader)))))