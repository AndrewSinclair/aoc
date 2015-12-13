(ns aoc-day1)

  (defn do-algorithm [input]
    (reduce +
      (map (fn [paren]
             (if (= paren \() 1 -1))
      input)))


  (println (do-algorithm (slurp "../input.txt")))
