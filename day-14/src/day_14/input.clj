(ns day-14.input
  (:require [clojure.string :as str]))

(defn parse-input
  [line]
  (let [numbers     (re-seq #"\d+" line)
        speed       (first numbers)
        travel-time (second numbers)
        rest-time   (nth numbers 2)]
    (apply vector (map #(Integer/parseInt %) [speed travel-time rest-time]))))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
   (doall (map parse-input (line-seq reader)))))