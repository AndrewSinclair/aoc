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

(comment "added some pseudocode that should do the trick"
  (defn return-map-made-of-same-keys-and-recursively-mapped-values
    [hashmap]
    (let [keys   (key hashmap)
          values (map recursive-mapper (vals hashmap))]
    (zipmap keys values)))

  (defn return-vector-of-recursively-mapped-values
    [items]
    (vect (map recursive-mapper items)))

  (defn red-attribute?
    [ele]
    (some #(= (second %) "red") ele))

  (defn recursive-mapper
  [ele]
  (cond
    (instance? map ele)
      (if (red-attribute? ele)
        nil
      (return-map-made-of-same-keys-and-recursively-mapped-values ele))
    (instance? vector ele)
      (return-vector-of-recursively-mapped-values ele)
    (instance? (:or string number nil) ele)
      ele)
    )
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