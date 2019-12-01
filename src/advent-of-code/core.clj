(ns advent-of-code.core
  (:require [clojure.string :as s]))

(defn calculate-fuel [mass]
  (- (Math/floor (/ mass 3)) 2))

(defn calculate-total-fuel []
  (reduce
   (fn [total-fuel mass]
     (+ total-fuel (calculate-fuel (Integer/parseInt mass))))
   0
   (s/split
    (slurp "resources/input.txt")
    #"\n")))
