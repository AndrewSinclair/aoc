(ns day-19.algo)

(defn do-replace
  [molecule idx length replacement]
  (if (< idx 0)
    nil
    (let [low  (.substring molecule 0 idx)
          high (.substring molecule (+ length idx))]
      (str low replacement high))))

(defn replacement-fn
  [element replacement molecule]
  (let [element-length (count element)]
    (loop [i    0
           accu []]
      (let [index-of-element  (.indexOf molecule element i)
            next-string (do-replace molecule index-of-element element-length replacement)]
        (if next-string
          (recur (inc index-of-element) (cons next-string accu))
          accu)))))

(defn to-replacement-fns
  "returns an array of functions. each of those return an array of the start molecule after 1 transition"
  [[element replacements]]
  (map #(partial replacement-fn element %) replacements))

(defn do-algo-1
  "Distinct number of outputs after trying all the transitions"
  [[chem-transitions start-molecule]]
  (let [replacements (flatten (map to-replacement-fns chem-transitions))]
    (->> replacements
      (map #(% start-molecule))
      flatten
      distinct
      count)))

(defn do-algo-2
  [[chem-transitions start-molecule]]
  nil)
