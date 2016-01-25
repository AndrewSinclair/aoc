(ns day-19.algo)

(defn insert-into [my-list item n]
  "insert item into my-list at position n"
  (let [low  (take n my-list)
        high (drop n my-list)]
    (concat low [item] high)))

(defn create-combos
  [splits element replacement]
  (let [num-splits   (count splits)
        elements (replicate (dec num-splits) element)]
    (for [i (range 0 num-splits)]
      (insert-into elements replacement i))))

(defn replacement-fn
  [element replacement molecule]
  (let [splits     (clojure.string/split molecule (re-pattern element))
        combos     (create-combos splits element replacement)]
    (map #(apply str (map str splits %)) combos)))


;; TODO
(defn to-replacement-fns
  "returns an array of functions. each of those return an array of the start molecule after 1 transition")
  [[element replacements]]
  (map #(partial replacement-fn element) )
  )

(defn do-algo-1
  "Distinct number of outputs after trying all the transitions"
  [chem-transitions start-molecule]
  (let [replacements (flatten (map to-replacement-fns chem-transitions))]
    (-> replacements
        (map #(% start-molecule))
        flatten
        distinct
        count)))


(defn do-algo-2
  [chem-transitions start-molecule]
  nil)
