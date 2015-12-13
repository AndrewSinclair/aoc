(ns day-4.algo
  (:require [digest]))

(defn check-difficulty
  [difficulty hashed]
  (let [n-zeros (apply str (replicate difficulty "0"))]
    (.startsWith hashed n-zeros)))

(defn do-mining
  [input difficulty]
  (inc
    (last 
      (for [nonce (range)
            :let [hashed (digest/md5 (str input nonce))]
            :while (not (check-difficulty difficulty hashed))]
        nonce))))


(defn do-algo-1
  "miner that finds nonce with difficulty 5 using md5"
  [input]
  (do-mining input 5))

(defn do-algo-2
  "miner that finds nonce with difficulty 6 using md5"
  [input]
  (do-mining input 6))
