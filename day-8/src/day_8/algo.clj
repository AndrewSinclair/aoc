(ns day-8.algo)

(defn difference-of-sums
  [string-counts]
  (reduce + (map #(- (first %) (second %)) string-counts)))

(defn count-actual
  [string]
  (count string))


(def extra-slash #"\\\\")
(def hex-char    #"\\x..")
(def extra-quote #"\\\"")

(defn count-in-memory
  "Replaces special chars with dots so they aren't special anymore. Then counts all
  remaining characters"
  [string]
  (let [string      (clojure.string/replace string extra-slash ".")
        string      (clojure.string/replace string hex-char    ".")
        string      (clojure.string/replace string extra-quote ".")
        final-count (- (count string) 2 )]
    final-count))

(def encode-quote #"\"")
(def encode-slash #"\\")

(defn count-encoded
  "Replaces special chars with dots so they aren't special anymore. Then counts all
  remaining characters"
  [string]
  (let [string      (clojure.string/replace string encode-quote "..")
        string      (clojure.string/replace string encode-slash "..")
        final-count (+ (count string) 2)]
    final-count))

(defn make-string-counts-vs-in-memory
  "returns a tuple like: [actual in-memory]"
  [string]
  (let [actual-count    (count-actual string)
        in-memory-count (count-in-memory string)]
    [actual-count in-memory-count]))

(defn make-string-counts-vs-encoded
  "returns a tuple like: [encoded actual]"
  [string]
  (let [actual-count    (count-actual  string)
        encoded-count   (count-encoded string)]
    [encoded-count actual-count]))

(defn do-algo-1
  "Counts the string characters and takes into account escape characters"
  [strings]
  (difference-of-sums (map make-string-counts-vs-in-memory strings)))

(defn do-algo-2
  "Counts the string characters and takes into account encoding the characters"
  [strings]
  (difference-of-sums (map make-string-counts-vs-encoded strings)))