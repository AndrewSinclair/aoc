(ns day-22.input)

(def player-stats
  {:hp 50 :atk 0 :def 0 :mana 500 :effects []})

(def boss-stats
  {:hp 55 :atk 8 :def 0 :effects []})

(def spells
  [{:cost 53  :abilities [{:damage 5}]}
   {:cost 73  :abilities [{:damage 2} {:heal 2}]}
   {:cost 113 :abilities [{:effect {:armor 7} :turns 6 :target :self}]}
   {:cost 173 :abilities [{:effect {:damage 3} :turns 6 :target :opponent}]}
   {:cost 229 :abilities [{:effect {:recharge 101} :turns 5 :target :self}]}])