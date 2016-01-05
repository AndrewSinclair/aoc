(ns day-12.input
  (:require [clojure.data.json :as json]))

(defn get-input
  [file-name]
  (json/read-str (slurp file-name)))