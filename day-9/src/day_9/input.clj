(ns day-9.input
  (:require [clojure.string :as str]))

(defn parse-travel-locations
  [words]
  (let [location-a (first words)
        location-b (nth words 2)
        distance   (Integer/parseInt (nth words 4))]
    {(keyword location-a) [(keyword location-b) distance]}))

(defn parse-input
  [line]
  (let [words (str/split line #" ")]
    (parse-travel-locations words)))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (doall (assoc (apply merge-with into (map parse-input (line-seq reader))) :Straylight [nil 0] ))))