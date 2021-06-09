(ns tictactoe.core)

(def board (atom [\space \space \space \space \space \space \space \space \space]))
(def demo-board (atom [\1 \2 \3 \4 \5 \6 \7 \8 \9]))

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
 (let [board (filter #(not (= " " (str %))) board)]
       (or
        (and (= (get board 0) (get board 1)) (= (get board 1) (get board 2)))
        (and (= (get board 3) (get board 4)) (= (get board 4) (get board 5)))
        (and (= (get board 6) (get board 7)) (= (get board 7) (get board 8)))
        (and (= (get board 0) (get board 3)) (= (get board 3) (get board 6)))
        (and (= (get board 1) (get board 4)) (= (get board 4) (get board 7)))
        (and (= (get board 2) (get board 5)) (= (get board 5) (get board 8)))
        (and (= (get board 0) (get board 4)) (= (get board 4) (get board 8)))
        (and (= (get board 0) (get board 4)) (= (get board 4) (get board 8))))))
        ;(and (= mark (get board 2)) (= mark (get board 4)) (= mark (get board 6))))))

(defn tie? [board]
 (= 0 (count (filter #(clojure.string/blank? (str %)) board))))

(defn place-marker [square turn]
 (swap! board assoc square (get-marker turn)))


;(filter #(not (= " " (str %))) [\X \X \X \space \space \space \space \space \space])
(or (= (get board 0) (get board 1)) (= (get board 1) (get board 2)))


