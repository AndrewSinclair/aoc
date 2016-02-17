(ns day-15.algo)

(defn get-calories
   [ingredient-details ingredient-distribution]
   (apply + (map * ingredient-distribution (map last ingredient-details))))

(defn score-cookie
  "scores the cookie:
   example input:
     details : [[-1 2 3 4] [5 -2 1 3] ...]
     distro  : [23 25 26 26]
  "
  ([ingredient-details ingredient-distribution]
    (score-cookie ingredient-details ingredient-distribution nil))

  ([ingredient-details ingredient-distribution max-calories]
  (let [property-values-per-cookie (map #(map (fn [two] (* %1 two)) %2) ingredient-distribution (map butlast ingredient-details))
        all-property-values        (apply map + property-values-per-cookie)
        property-values            (map #(if (neg? %) 0 %) all-property-values)
        calories                   (get-calories ingredient-details ingredient-distribution)]
    (if (and max-calories (> calories max-calories))
      0
      (apply * property-values)))))

(defn inc-all
  [ingredient-distribution]
  (for [i (range (count ingredient-distribution))
        :let [updated-ing (inc (nth ingredient-distribution i))]]
    (assoc ingredient-distribution i updated-ing)))

(defn choose-best
  "Map ingredient choices to cookie scores, get the index of the highest score, then return the ingredient list at that index"
  ([ingredient-details ingredient-distributions]
    (choose-best ingredient-details ingredient-distributions nil))

  ([ingredient-details ingredient-distributions max-calories]
  (let [cookie-scores (map #(score-cookie ingredient-details % max-calories) ingredient-distributions)
        max-score     (apply max cookie-scores)
        index-of-max  (.indexOf cookie-scores max-score)]
    (nth ingredient-distributions index-of-max))))

(defn choose-best-score
  [ingredient-details ingredient-distributions max-calories]
  (apply max-key #(second %) ingredient-distributions))

(def backtrack-algo 
  (memoize (fn [curr-distribution score total-tsp max-calories inputs]
    (cond
      (= (reduce + curr-distribution) total-tsp)
        [curr-distribution score]
      (= 0 score)
        [curr-distribution 0]
      :else
        (let [all-next-distros     (inc-all curr-distribution)]
          (choose-best-score inputs (map #(backtrack-algo % (score-cookie inputs % max-calories) total-tsp max-calories inputs) all-next-distros) max-calories))))))

(defn calc-best-cookie
  ; Iterative solution - Works when there's no Calories
  ([inputs total-tsp]
    (let [num-ingredients      (count inputs)
          initial-distribution (apply vector (replicate num-ingredients 1))
          best-distribution    (nth (iterate #(choose-best inputs (inc-all %)) initial-distribution) (- total-tsp num-ingredients))]
      (score-cookie inputs (apply vector best-distribution))))

  ; Backtracking solution - Needed for when there's a Calorie restriction
  ([inputs total-tsp max-calories]
    (let [num-ingredients       (count inputs)
          initial-distribution  (apply vector (replicate num-ingredients 1))
          best-distro-and-score (backtrack-algo initial-distribution -1 total-tsp max-calories inputs)]
      (second best-distro-and-score))))

(defn do-algo-1
  "Input ignores calories"
  [inputs total-tsp]
  (calc-best-cookie inputs total-tsp))
  
(defn do-algo-2
  "Input includes calories"
  [inputs total-tsp max-calories]
  (calc-best-cookie inputs total-tsp max-calories))
