(ns tictactoe.core)

(def players {:p1 \X :p2 \O})

(defn get-turn [n]
 (if (not (zero? (mod n 2)))
  (get players :p1)
  (get players :p2)))

(defn legal-move? [board square]
 (let [move (get board square)]
  (if (not (nil? move))
   (clojure.string/blank? (str move))
   false)))

(defn win? [board mark]
 (cond
  (and (= mark (get board 0)) (= mark (get board 1)) (= mark (get board 2))) true
  (and (= mark (get board 3)) (= mark (get board 4)) (= mark (get board 5))) true
  (and (= mark (get board 6)) (= mark (get board 7)) (= mark (get board 8))) true
  (and (= mark (get board 0)) (= mark (get board 3)) (= mark (get board 6))) true
  (and (= mark (get board 1)) (= mark (get board 4)) (= mark (get board 7))) true
  (and (= mark (get board 2)) (= mark (get board 5)) (= mark (get board 8))) true
  (and (= mark (get board 0)) (= mark (get board 4)) (= mark (get board 8))) true
  (and (= mark (get board 0)) (= mark (get board 4)) (= mark (get board 8))) true
  (and (= mark (get board 2)) (= mark (get board 4)) (= mark (get board 6))) true
  :else false)
 )

(defn tie? [board]
 (= 0 (count (filter #(clojure.string/blank? (str %)) board))))

