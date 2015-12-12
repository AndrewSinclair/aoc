(ns day-4.algo
  (:require [digest]))

(defn md5
  [msg]
  (digest/md5 msg))

(defn do-mining
  [input difficulty]
  ;(first (for [nonce [(range)]
  ;      :let [hashed (to-hex-string (md5 (str input nonce)))]
  ;      :while (not check-difficulty difficulty hashed)]
  ;  nonce)))
  "TODO")

(defn do-algo-1
  "miner that finds nonce with difficulty 5 using md5"
  [input]
  (do-mining input 5))

(defn do-algo-2
  "miner that finds nonce with difficulty 6 using md5"
  [input]
  (do-mining input 6))