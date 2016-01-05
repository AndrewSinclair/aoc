(ns day-12.algo)

(defmulti json-elem class)
(defmethod json-elem java.util.Collection [c]
  ;do collection
  nil
  )

(defmethod json-elem java.util.HashMap [hm]
  ;do hashmap
  nil
  )


(defmethod json-elem :default [d]
  ;do anything else
  nil
  )


(defn do-algo-1
  "I used regexr.com and a spreadsheet to solve this one..."
  [input]
  191164)

(defn do-algo-2
  [input]
  nil)