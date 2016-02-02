(ns day-22.algo)

(defn calc-damage
  [attack defense]
  (let [diff  (- attack defense)]
    (if (> diff 0)
      diff
      1)))

(defn wizard?
  [fighter]
  (= (:id fighter) :player))

(defn dead?
  [fighter]
  (>= 0 (:hp fighter)))

(defn swing-weapon
  [attacker defender]
  (let [attack (:atk attacker)
        defense (:def defender)
        damage (calc-weapon-damage attack defense)
        hp-updated (- (:hp defender) damage)]
    [attacker (assoc defender :hp hp-updated)]))


(defn cast-spell
  "This needs to choose a spell somehow"
  [attacker defender spells]

  [attacker defender])

(defn simulate-fight
  [attacker defender spells]
  (let [attacker (do-effects attacker)
        defender (do-effects defender)
        offense  (if (wizard? attacker) cast-spell swing-weapon)]
    (cond
      (dead? defender)
        (:id attacker)
      (dead? attacker)
        (:id defender)
      :else
        (let [[attacker defender] (offense attackers defender)]
          (if (dead? defender)
            (:id attacker)
            (recur defender attacker spells))))))

(defn do-algo-1
  []
  nil)

(defn do-algo-2
  []
  nil)