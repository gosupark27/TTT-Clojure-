(ns tictactoe.game-board)

(def game-board (atom [\space \space \space \space \space \space \space \space \space]))
(def demo-board (atom [\1 \2 \3 \4 \5 \6 \7 \8 \9]))
(def turn (atom 1))
(def players {:p1 \X :p2 \O})

(defn legal-move? [board square]
 (let [move (get board square)]
  (if-not (nil? move)
   (clojure.string/blank? (str move))
   false)))

(defn get-marker [turn]
 (if (not (zero? (mod turn 2)))
  (get players :p1)
  (get players :p2)))
(defn place-marker [square turn]
 (swap! game-board assoc square (get-marker turn)))

;{:board board :marker marker :square square}

;(defn place-marker [square marker board]
; (swap! game-board assoc square (get-marker turn)))

(defn all-same-marker? [[a b c]]
 (if (= 3 (count (filter #(not (clojure.string/blank? (str %))) [a b c])))
  (and (= a b) (= b c))
  false))

(defn get-combo [board]
 [(vector (get board 0) (get board 1) (get board 2))
  (vector (get board 3) (get board 4) (get board 5))
  (vector (get board 6) (get board 7) (get board 8))
  (vector (get board 0) (get board 3) (get board 6))
  (vector (get board 1) (get board 4) (get board 7))
  (vector (get board 2) (get board 5) (get board 8))
  (vector (get board 0) (get board 4) (get board 8))
  (vector (get board 2) (get board 4) (get board 6))
  ])

(defn win? [board]
 (not (nil? (some true? (map all-same-marker? (get-combo board))))))

(defn tie? [board]
 (= 0 (count (filter #(clojure.string/blank? (str %)) board))))







