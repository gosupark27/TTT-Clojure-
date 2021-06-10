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

(defn all-same-marker? [[a b c]]
 (if (= 3 (count (filter #(not (clojure.string/blank? (str %))) [a b c])))
  (and (= a b) (= b c))
  false))

;(def combo (conj (partition 3 @demo-board) (partition-by #(= 0 (mod (.indexOf @demo-board %) 3)) @demo-board)))

;(def combo [(vector (get @board 0) (get @board 1) (get @board 2))
;            (vector (get @board 3) (get @board 4) (get @board 5))
;            (vector (get @board 6) (get @board 7) (get @board 8))
;            (vector (get @board 0) (get @board 3) (get @board 6))
;            (vector (get @board 1) (get @board 4) (get @board 7))
;            (vector (get @board 2) (get @board 5) (get @board 8))
;            (vector (get @board 0) (get @board 4) (get @board 8))
;            (vector (get @board 2) (get @board 4) (get @board 6))
;            ])

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
 (not (nil? (some true? (map all-same-marker? (get-combo board))))
  )
 )

;(let [board (filter #(not (= " " (str %))) board)]
; (if (or (= 0 (count board)) (> 3 (count board)))
;  false
;  (or
;   (and (= (get board 0) (get board 1)) (= (get board 1) (get board 2)))
;   (and (= (get board 3) (get board 4)) (= (get board 4) (get board 5)))
;   (and (= (get board 6) (get board 7)) (= (get board 7) (get board 8)))
;   (and (= (get board 0) (get board 3)) (= (get board 3) (get board 6)))
;   (and (= (get board 1) (get board 4)) (= (get board 4) (get board 7)))
;   (and (= (get board 2) (get board 5)) (= (get board 5) (get board 8)))
;   (and (= (get board 0) (get board 4)) (= (get board 4) (get board 8)))
;   (and (= (get board 0) (get board 4)) (= (get board 4) (get board 8))))
;  ))

(defn tie? [board]
 (= 0 (count (filter #(clojure.string/blank? (str %)) board))))

(defn place-marker [square turn]
 (swap! board assoc square (get-marker turn)))


;(filter #(not (= " " (str %))) [\X \X \X \space \space \space \space \space \space])
;(or (= (get board 0) (get board 1)) (= (get board 1) (get board 2)))


;(when (some (map all-x? combos) :win))




