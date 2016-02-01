(ns day-21.input)

(def weapons
  [[ 8 4 0]
   [10 5 0]
   [25 6 0]
   [40 7 0]
   [74 8 0]])

(def armors
  [[13 0 1]
   [31 0 2]
   [53 0 3]
   [75 0 4]
   [102 0 5]])

(def rings
  [[25 1 0]
   [50 2 0]
   [100 3 0]
   [20 0 1]
   [40 0 2]
   [80 0 3]])

(def player-stats
  {:id :player :hp 100 :atk 0 :def 0})

(def boss-stats
  {:id :boss :hp 104 :atk 8 :def 1})