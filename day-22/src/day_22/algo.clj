(ns day-22.algo)

(defn calc-damage
  [attack defense]
  (let [diff (- attack defense)]
    (if (> diff 0)
      diff
      1)))

(defn wizard?
  [contender]
  (= (:id contender) :player))

(defn dead?
  [contender]
  (>= 0 (:hp contender)))

(defn swing-weapon
  [attacker defender]
  (let [attack     (:atk attacker)
        defense    (:def defender)
        damage     (calc-damage attack defense)
        hp-updated (- (:hp defender) damage)]
    [attacker (assoc defender :hp hp-updated)]))

(defn update-stat
  [contender stat delta]
  (assoc contender stat (+ (:stat contender) delta)))

(defn add-effect
  "type can be one of
  :armor
  :damage
  :recharge"
  [contender type duration]
  (let [effects (:effects contender)
        effects (conj effects [type duration])]
    (assoc contender :effects effects)))

(defn cast-spell-0
  "damage opponent
  mana: 53
  dmg : 5"
  [attacker defender]
  (let [attacker (update-stat attacker :mana -53)
        defender (update-stat defender :hp    -5)]
    [attacker defender]))

(defn cast-spell-1
  "damage opponent and heal
  mana: 73
  heal:  2
  dmg :  2"
  [attacker defender]
  (let [attacker (-> attacker
                   (update-stat :mana -73)
                   (update-stat :hp    +2))
        defender (update-stat defender :hp -5)]
    [attacker defender]))

(defn cast-spell-2
  "gain armor
  mana : 113
  armor: 7
  turns: 6"
  [attacker defender]
  (let [attacker (-> attacker
                   (update-stat :mana  -113)
                   (add-effect  :armor    6))] ; 6 is duration
    [attacker defender]))

(defn cast-spell-3
  "damage over time
  mana : 173
  dmg  :   3
  turns:   6"
  [attacker defender]
  (let [attacker (update-stat attacker :mana   -173)
        defender (add-effect defender  :damage    6)] ; 6 is duration
    [attacker defender]))

(defn cast-spell-4
  "mana regen over time
  mana  : 229
  regen : 101
  turns :   5"
  [attacker defender]
  (let [attacker (-> attacker
                   (update-stat :mana     -229)
                   (add-effect  :recharge    5))] ; 5 is duration
    [attacker defender]))

(defn add-if
  [value pred? coll]
  (conj coll (if pred? value nil)))

(defn not-has-effect?
  [contender effect]
  (filter #(not= effect (first %)) (:effects contender)))

(defn get-spells
  "Returns all spells applicable on this current turn"
  [attacker defender]
  (let [curr-mana (:mana attacker)]
    (->>
      []
      (add-if cast-spell-0 (>= curr-mana 53))
      (add-if cast-spell-1 (>= curr-mana 73))
      (add-if cast-spell-2 (and (has-effect? attacker :armor) (>= curr-mana 113)))
      (add-if cast-spell-3 (and (has-effect? defender :damage) (>= curr-mana 173)))
      (add-if cast-spell-4 (and (has-effect? attacker :recharge (>= curr-mana 229)))))))

(defn update-durations
  [effects]
  (map #(assoc % 1 (dec (second %))) effects))

(defn remove-completed
  [effects]
  (filter #(< 0 (second %)) effects))

(defn resolve-effect
  ":armor 7def, :damage -3hp, or :recharge +101mana"
  [contender effect]
  (cond
    (= (first effect) :armor)
      (assoc contender :def 7)
    (= (first effect) :damage)
      (update-stat contender :hp -3)
    (= (first effect) :recharge)
      (update-stat contender :mana +101)))

(defn update-effects
  [contender]
  (let [effects      (:effects contender)
        next-effects (->> effects update-durations remove-completed)]
    (assoc (reduce resolve-effect contender effects) :effects next-effects)))

(defn simulate-fight
  "needs to return the amount of mana used and winner
  then needs to find all outcomes"
  [attacker defender]
  (let [attacker (update-effects attacker)
        defender (update-effects defender)
        offenses (if (wizard? attacker) (get-spells attacker defender) [swing-weapon])]
    (cond
      (dead? defender)
        (:id attacker)
      (dead? attacker)
        (:id defender)
      :else
        (let [attack-choices (map #(% attacker defender) offenses)]
          (loop [[chosen-attack & tail] attack-choices
                  attacker attacker
                  defender defender]
            (if chosen-attack
              (let [[attacker defender] (chosen-attack attacker defender)]
                (if (dead? defender)
                  (:id attacker)
                  (recur tail attacker defender))
              (simulate-fight defender attacker))))))))

(defn do-algo-1
  []
  nil)

(defn do-algo-2
  []
  nil)