(ns tictactoe.core)

(def board (atom [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020]))

(def turn (atom 1))

(def players {:p1 \X :p2 \O})

(defn get-marker [n]
 (if (not (zero? (mod n 2)))
  (get players :p1)
  (get players :p2)))

(defn legal-move? [board square]
 (let [move (get board square)]
  (if-not (nil? move)
   (clojure.string/blank? (str move))
   false)))

(defn win? [board mark]
 (or
  (and (= mark (get board 0)) (= mark (get board 1)) (= mark (get board 2)))
  (and (= mark (get board 3)) (= mark (get board 4)) (= mark (get board 5)))
  (and (= mark (get board 6)) (= mark (get board 7)) (= mark (get board 8)))
  (and (= mark (get board 0)) (= mark (get board 3)) (= mark (get board 6)))
  (and (= mark (get board 1)) (= mark (get board 4)) (= mark (get board 7)))
  (and (= mark (get board 2)) (= mark (get board 5)) (= mark (get board 8)))
  (and (= mark (get board 0)) (= mark (get board 4)) (= mark (get board 8)))
  (and (= mark (get board 0)) (= mark (get board 4)) (= mark (get board 8)))
  (and (= mark (get board 2)) (= mark (get board 4)) (= mark (get board 6))))
 )

(defn tie? [board]
 (= 0 (count (filter #(clojure.string/blank? (str %)) board))))

(defn place-marker [square turn]
 (swap! board assoc square (get-marker turn)))





