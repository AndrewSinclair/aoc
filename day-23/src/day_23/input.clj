(ns day-23.input)

(defn third
  [some-seq]
  (nth some-seq 2))

(defn parse-line
  [line]
  (let [tokens      (clojure.string/split line #"\s+")
        instruction (first tokens)]
    (cond
      (= instruction "hlf") [:hlf (second tokens)]
      (= instruction "tpl") [:tpl (second tokens)]
      (= instruction "inc") [:inc (second tokens)]
      (= instruction "jmp") [:jmp (Integer/parseInt (second tokens))]
      (= instruction "jie") [:jie (second tokens) (Integer/parseInt (third tokens))]
      (= instruction "jio") [:jio (second tokens) (Integer/parseInt (third tokens))])))

(defn get-input
  [file-name]
  (with-open [reader (clojure.java.io/reader file-name)]
    (let [input-lines  (line-seq reader)]
      (doall (map parse-line input-lines)))))
