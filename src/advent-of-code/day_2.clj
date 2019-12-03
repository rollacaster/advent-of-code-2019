(ns advent-of-code.day-2
  (:require [clojure.string :as s]))

(defn handle-op-code [[opcode pos1 pos2 target] program]
  (let [n1 (nth program pos1)
        n2 (nth program pos2)
        op (if (= opcode 1) + *)]
    (assoc program target (op n1 n2))))

(defn compute [program]
  (reduce
   (fn [result idx]
     (let [computation (nth (partition 4 result) idx)
           opcode (first computation)]
       (cond
         (= opcode 99) result
         :else (handle-op-code computation result))))
   program
   (range (Math/floor (/ (count program) 4)))))

(defn prepare-data []
  (for [noun (range 100)
        verb (range 100)]
    (let [data (into [] (map
                         (fn [d] (Integer/parseInt (s/replace d "\n" "")))
                         (s/split
                          (slurp "resources/day2.txt")
                          #",")))]
      (when (= 19690720
               (nth
                (compute
                 (-> data
                     (assoc 1 noun)
                     (assoc 2 verb)))
                0))
        (println "noun" noun "verb" verb)))))

(defn run []
  (compute (prepare-data)))

