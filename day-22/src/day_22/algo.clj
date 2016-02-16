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


(defn get-spells
  "Returns all spells applicable on this current turn"
  [attacker defender spells]
  [nil]
  )

(defn update-effects
  [contender]
  nil
  )

(defn simulate-fight
  [attacker defender spells]
  (let [attacker (update-effects attacker)
        defender (update-effects defender)
        offenses (if (wizard? attacker) (get-spells attacker defender spells) [swing-weapon])]
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
              (simulate-fight defender attacker spells))))))))

(defn do-algo-1
  []
  nil)

(defn do-algo-2
  []
  nil)