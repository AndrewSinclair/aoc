(ns day-21.algo
  (:require [clojure.math.combinatorics :as combo]))

(defn calc-damage
  [attack defense]
  (let [diff  (- attack defense)]
    (if (> diff 0)
      diff
      1)))

(defn simulate-fight
  [attacker defender]
  (let [damage      (calc-damage (:atk attacker) (:def defender))
        defender-hp (- (:hp defender) damage)
        defender    (assoc defender :hp defender-hp)]
    (if (<= defender-hp 0)
      (:id attacker)
      (recur defender attacker))))

(defn choose
  ([n coll]
    (combo/combinations coll n))

  ([n m coll]
    (apply concat
      (for [i (range n (inc m))]
        (combo/combinations coll i)))))

(defn cross
  ([coll1 coll2]
  (map #(apply concat %) (apply concat (map (fn [outer] (map (fn [inner] (vector inner outer)) coll1)) coll2))))

  ([coll1 coll2 coll3]
  (cross (cross coll1 coll2) coll3)))

(defn equip-item
  [player loadout]
  (loop [[item & tail] loadout
      stats  player]
  (let [[item-gp item-attack item-defense] item
        {play-gp :gp play-attack :atk play-defense :def :or {play-gp 0}} stats
        next-stats (->
                     stats
                     (assoc :gp  (+ play-gp item-gp))
                     (assoc :atk (+ play-attack item-attack))
                     (assoc :def (+ play-defense item-defense)))]
    (if tail
      (recur tail next-stats)
      next-stats))))

(defn equip-loadout
  [player loadouts]
  (map (partial equip-item player) loadouts))

(defn algo
  [weapons armors rings player boss optimization-fn desired-winner]
  (let [loadouts      (cross (choose 1 weapons)
                             (choose 0 1 armors)
                             (choose 0 2 rings))
        player-builds (equip-loadout player loadouts)]
    (->> player-builds
         (filter #(= desired-winner (simulate-fight % boss)))
         (apply optimization-fn :gp))))

(defn do-algo-1
  [weapons armors rings player boss]
  (algo weapons armors rings player boss min-key :player))

(defn do-algo-2
  [weapons armors rings player boss]
  (algo weapons armors rings player boss max-key :boss))