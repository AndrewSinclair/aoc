(ns day-12.algo)

(defprotocol JsonElemFilterRed
  (red-filter [ele]
  "Recursively searches and replaces any JSON object with a red attribute to nil."))

(extend-protocol JsonElemFilterRed
  clojure.lang.PersistentVector
    (red-filter [ele]
      (apply vector (map red-filter ele)))

  java.util.Map
    (red-filter [ele]
      (if (some #(= (second %) "red") ele)
        nil
        (let [values (map red-filter (vals ele))]
          (zipmap (keys ele) values))))

  java.lang.Object
    (red-filter [ele] ele))

(defn get-numbers
  "I'm treating the json like a string here, so I do regex-replace and string split
  then map Integer.parseInt over the numbers"
  [elem]
  (map #(Integer/parseInt %)
    (butlast
      (clojure.string/split
        (clojure.string/replace (str elem) #".*?(-?\d+)" "$1\n")
        #"\n"))))

(defn do-algo-1
  "Sum up all the numbers that appear in the JSON"
  [input]
  (reduce + (get-numbers input)))

(defn do-algo-2
  "Filter out the JSON objects with red attributes,
  then sum up all the remaining numbers that appear"
  [input]
  (reduce + (get-numbers (red-filter input))))
