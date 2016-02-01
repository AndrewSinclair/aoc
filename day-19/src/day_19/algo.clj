(ns day-19.algo)

(defn do-replace
  ([molecule element replacement]
   (do-replace molecule (.indexOf molecule element) (count element) replacement))
  ([molecule idx length replacement]
  (if (< idx 0)
    nil
    (let [low  (.substring molecule 0 idx)
          high (.substring molecule (+ length idx))]
      (str low replacement high)))))

(defn replacement-fn
  [element replacement molecule]
  (let [element-length (count element)]
    (loop [i    0
           accu []]
      (let [index-of-element (.indexOf molecule element i)
            next-string      (do-replace molecule index-of-element element-length replacement)]
        (if next-string
          (recur (inc index-of-element) (cons next-string accu))
          accu)))))

(defn to-replacement-fns
  "returns an array of functions. each of those return an array of the start molecule after 1 transition"
  [[element replacements]]
  (map #(partial replacement-fn element %) replacements))

(defn do-vector-replace
  [molecule element replacement]
  (map-indexed #(apply vector (flatten (if (= element %2) (assoc molecule %1 replacement) %2))) molecule))

(defn molecules-equal?
  [molecule-a molecule-b]
  (and (= (count molecule-a) (count molecule-b))
       (every? identity (map #(= %1 %2) molecule-a molecule-b))))

(defn replacements-by-chems
  [molecule element replacements]
  (apply concat (map #(do-vector-replace molecule element %) replacements)))

(defn get-nexts
  "for every atom in the molecule, create a transition.
  return the set of molecules resulting from all the transitions."
  [chem-transitions molecule]
  (distinct (apply concat (map #(replacements-by-chems molecule % (get chem-transitions %)) molecule))))

(defn push-nexts
  [transitions molecule queue depth goal-length]
  (let [nexts (get-nexts transitions molecule)
        queue (->> nexts
                (filter #(<= (count %) goal-length))
                (reduce #(conj %1 (vector %2 (inc depth))) queue))]
    queue))

(defn perform-bfs
  [transitions queue molecule end-molecule]
  (let    [goal-length (count end-molecule)]
    (loop [molecule    molecule
           queue       queue
           depth       0]
      (if (molecules-equal? molecule end-molecule)
        depth
        (let [[molecule depth] (first queue)
              queue            (push-nexts transitions molecule (pop queue) depth goal-length)]
          (recur molecule queue (inc depth)))))))

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
  "Perform Breadth-First-Search starting with 'e' until you create the molecule
  The algorithm will, upon visiting a node, queue the next possible transitions, do a thing, then visit the next node on the queue."
  [[chem-transitions end-molecule]]
  (let [molecule      ["e"]
        empty-queue   clojure.lang.PersistentQueue/EMPTY
        initial-nexts (get-nexts chem-transitions molecule)
        initial-queue (reduce #(conj %1 (vector %2 0)) empty-queue initial-nexts)]
    (perform-bfs chem-transitions initial-queue molecule end-molecule)))
