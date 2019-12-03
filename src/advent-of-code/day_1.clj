(ns advent-of-code.day-1
  (:require [clojure.string :as s]))

(defn calculate-fuel [mass]
  (- (int (Math/floor (/ mass 3))) 2))

(defn calculate-fuel-2 [mass]
  (let [fuel (calculate-fuel mass)
        fuel-for-fuel (calculate-fuel fuel)]
    (if (<= fuel-for-fuel 0)
      (max fuel 0)
      (+ fuel fuel-for-fuel (calculate-fuel-2 fuel-for-fuel)))))

(defn calculate-total-fuel []
  (reduce
   (fn [total-fuel mass]
     (+ total-fuel (calculate-fuel-2 (Integer/parseInt mass))))
   0
   (s/split-lines (slurp "resources/input.txt"))))

(defn run []
  (calculate-total-fuel))


