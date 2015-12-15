(ns day-6.input
  (:require [clojure.string :as str]))

(defn parse-coords
  "e.g., 660,55 through 986,197"
  [line]
  (let [[_ x1 y1 x2 y2] (re-find #".+?(\d+).+?(\d+).+?(\d+).+?(\d+)" line)]
    {:pt1 {:x x1 :y y1} :pt2 {:x x2 :y y2}}))

(defn parse-input
  [line]
  (let [words (str/split line #" ")
        rectangle (parse-coords line)]
    (if (= (first words) "toggle")
      {:command :toggle :rect rectangle}
      (if (= (second words) "on")
        {:command :on  :rect rectangle}
        {:command :off :rect rectangle}))))

(defn get-input
  "Returns input from a file"
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (reduce conj '() (map parse-input (line-seq reader)))))