(ns advent-of-code.day-4
  (:require [clojure.string :as s]))

(defn double-number? [number]
  (let [groups (sort
                (map
                 (fn [[key value]] (count value))
                 (group-by identity (s/split (str number) #""))))]
    (or (contains? (set groups) 2) (= (last groups) 2))))

(defn to-number [char]
  (Integer/parseInt (str char)))

(defn increasing? [number]
  (let [number-as-string (str number)]
    (every?
     true?
     (map-indexed
      (fn [idx number]
        (if (= idx 0)
          true
          (>= (to-number number) (to-number (nth number-as-string (dec idx))))))
      number-as-string))))

(defn valid-pwd? [number]
  (and (double-number? number) (increasing? number)))

(defn run []
  (count (filter
          valid-pwd?
          (range 240920 789857))))
