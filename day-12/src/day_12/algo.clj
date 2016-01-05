(ns day-12.algo)

(defprotocol JsonElem
  (json-elem [in] "This is not implementation, this is Doc text"))

(extend-protocol JsonElem
  clojure.lang.PersistentVector
    (json-elem [in]
      ;do vector
      (map json-elem in))
  clojure.lang.PersistentArrayMap
    (json-elem [in]
      ;do hashmap
      (if (some #(= (second %) "red") in)
        nil
        )
  java.lang.Object
    (json-elem [in]
      "found default")
)

(defn filter-out-red
  [elem]

  )

(defn do-algo-1
  "I used regexr.com and a spreadsheet to solve this one..."
  [input]
  191164)

(defn do-algo-2
  [input]
  (reduce + (get-numbers (filter-out-red input))))