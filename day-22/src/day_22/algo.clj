(ns day-22.algo)

(defn calc-damage
  [attack defense]
  (let [diff (- attack defense)]
    (if (pos? diff)
      diff
      1)))

(defn dead?
  [contender]
  (>= 0 (:hp contender)))

(defn swing-weapon
  [attacker defender]
  (let [attack     (:atk attacker)
        defense    (if (first (filter #(= (first %) :armor) (:effects defender))) 7 0) 
        damage     (calc-damage attack defense)
        hp-updated (- (:hp defender) damage)]
    [attacker (assoc defender :hp hp-updated)]))

(defn update-stat
  [contender stat delta]
  (assoc contender stat (+ (get contender stat) delta)))

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
  dmg : 4"
  [attacker defender]
  (let [attacker   (update-stat attacker :mana -53)
        defender   (update-stat defender :hp    -4)]
    [attacker defender 53]))

(defn cast-spell-1
  "damage opponent and heal
  mana: 73
  heal:  2
  dmg :  2"
  [attacker defender]
  (let [attacker (-> attacker
                   (update-stat :mana -73)
                   (update-stat :hp    +2))
        defender (update-stat defender :hp -2)]
    [attacker defender 73]))

(defn cast-spell-2
  "gain armor
  mana : 113
  armor: 7
  turns: 6"
  [attacker defender]
  (let [attacker (-> attacker
                   (update-stat :mana  -113)
                   (add-effect  :armor    6))] ; 6 is duration
    [attacker defender 113]))

(defn cast-spell-3
  "damage over time
  mana : 173
  dmg  :   3
  turns:   6"
  [attacker defender]
  (let [attacker (update-stat attacker :mana   -173)
        defender (add-effect defender  :damage    6)] ; 6 is duration
    [attacker defender 173]))

(defn cast-spell-4
  "mana regen over time
  mana  : 229
  regen : 101
  turns :   5"
  [attacker defender]
  (let [attacker (-> attacker
                   (update-stat :mana     -229)
                   (add-effect  :recharge    5))] ; 5 is duration
    [attacker defender 229]))

(defn add-if
  [value pred? coll]
  (if pred? (conj coll value) coll))

(defn not-has-effect?
  [contender effect]
  ;If the contender does have the effect, but it will expire next turn, then it's considered to not have it!
  ; This point matters for part 2!
  (empty? (filter #(and (= effect (first %)) (> (second %) 1)) (:effects contender))))

(defn get-spells
  "Returns all spells applicable on this current turn"
  [attacker defender mana-total results]
  (let [curr-mana (:mana attacker)]
    (->>
      []
      (add-if cast-spell-0 (and (>= curr-mana 53) (<= (+ mana-total 53) results)))
      (add-if cast-spell-1 (and (>= curr-mana 73) (<= (+ mana-total 73) results)))
      (add-if cast-spell-2 (and (not-has-effect? attacker :armor) (>= curr-mana 113) (<= (+ mana-total 113) results)))
      (add-if cast-spell-3 (and (not-has-effect? defender :damage) (>= curr-mana 173) (<= (+ mana-total 173) results)))
      (add-if cast-spell-4 (and (not-has-effect? attacker :recharge) (>= curr-mana 229) (<= (+ mana-total 229) results))))))

(defn update-durations
  [effects]
  (map #(assoc % 1 (dec (second %))) effects))

(defn remove-completed
  [effects]
  (filter #(pos? (second %)) effects))

(defn resolve-effect
  ":armor 7def, :damage -3hp, or :recharge +101mana"
  [contender effect include-hard?]
  (cond
    (and (= (first effect) :hard) include-hard?)
      (update-stat contender :hp -1)
    (= (first effect) :damage)
      (update-stat contender :hp -3)
    (= (first effect) :recharge)
      (update-stat contender :mana +101)
    (= (first effect) :armor)
      contender
    :else
      contender))

(defn update-effects
  ([contender include-hard?]
    (let [effects      (:effects contender)
          next-effects (->> effects update-durations remove-completed)]
      (assoc (reduce #(resolve-effect %1 %2 include-hard?) contender effects) :effects next-effects)))
  ([contender]
    (update-effects contender nil)))

(defn enqueue-list
  [queue spells wizard fighter mana-total]
  (reduce #(conj %1 {
                  :spell %2
                  :wizard wizard
                  :fighter fighter
                  :mana-total mana-total})
    queue spells))

(defn simulate-fight
  [queue results]
  (if (empty? queue)
    results
    (let [state      (first queue)
          queue      (pop queue)
          wizard     (:wizard state)
          fighter    (:fighter state)
          mana-total (:mana-total state)
          spell      (:spell state)
          wizard     (update-effects wizard true)
          fighter    (update-effects fighter)]
      (cond
        (dead? wizard)
          (recur queue results)
        (dead? fighter)
          (recur queue (min mana-total results))
        :else
          (let [[wizard fighter mana-cost] (spell wizard fighter)
                mana-total (+ mana-total mana-cost)]
            (if (dead? fighter)
              (recur queue (min mana-total results))
              ; now fighter swings his turn
              (let [wizard  (update-effects wizard)
                    fighter (update-effects fighter)]
                (if (dead? fighter)
                  (recur queue (min mana-total results))
                  (let [[fighter wizard] (swing-weapon fighter wizard)]
                    (if (dead? wizard)
                      (recur queue results)
                      (let [spells (get-spells wizard fighter mana-total results)]
                        (if (pos? (count spells))
                          (let [queue-with-spells (enqueue-list queue spells wizard fighter mana-total)]
                            (recur queue-with-spells results))
                          (recur queue results)))))))))))))

(defn start-fight
  [wizard fighter]
  (let [queue (enqueue-list clojure.lang.PersistentQueue/EMPTY (get-spells wizard fighter 0 9999999) wizard fighter 0)]
    (simulate-fight queue 9999999)))

(defn do-algo-1
  [wizard fighter]
  (start-fight wizard fighter))

(defn do-algo-2
  [wizard fighter]
  (start-fight wizard fighter))
