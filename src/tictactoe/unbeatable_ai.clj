(ns tictactoe.unbeatable-ai
 (:require [tictactoe.game-board :as board]))

(defn eval-board-square [mark board]
  (cond
   (and (= mark \O) (board/win? board)) -10
   (board/win? board) 10
   (board/tie? board) 0))

(defn get-empty-board-square-indices [board]
 (loop [empty-square-indices []
        index 0]
  (if (>= index (count board))
   empty-square-indices
   (if (clojure.string/blank? (str (nth board index)))
    (recur (conj empty-square-indices index) (inc index))
    (recur empty-square-indices (inc index)))
   )))

;(defn mini-max [board depth maximizer?]
;  (let [possible-moves (get-empty-board-square-indices board)]
;   ;(println "board:" board)
;   ;(println "all empty squares:" possible-moves)
;   ;(println "depth:" depth)
;   (if (or (= 0 (count possible-moves)) (= depth 9) (board/win? board) (board/tie? board))
;    (if (= 10 (eval-board-square (board/get-marker depth) board))
;     (- 10 depth)
;     (- depth 10))
;    (if maximizer?
;     (loop [remaining-moves (get-empty-board-square-indices board)
;            maxWeight -1000]
;      ;(println "remaining moves:" remaining-moves)
;      (def weight (mini-max (assoc board (first possible-moves) (board/get-marker depth)) (inc depth) false))
;      (if (empty? remaining-moves)
;       maxWeight
;       (recur (rest remaining-moves) (max maxWeight weight))))
;     (loop [remaining-moves (get-empty-board-square-indices board)
;            minWeight 1000]
;      ;(println "remaining moves:" remaining-moves)
;      (def weight (mini-max (assoc board (first possible-moves) (board/get-marker depth)) (inc depth) true))
;      (if (empty? remaining-moves)
;       minWeight
;       (recur (rest remaining-moves) (min minWeight weight)))
;      )))))
(defn mini-max [board depth maximizer?]
  (let [possible-moves (get-empty-board-square-indices board)]
   (if (or (= 0 (count possible-moves)) (= depth 9) (board/win? board) (board/tie? board))
        (if (= 10 (eval-board-square (board/get-marker depth) board))
         (- 10 depth)
         (- depth 10))
    (if maximizer?
     (mini-max (assoc board (first possible-moves) (board/get-marker depth)) (inc depth) false)
     (mini-max (assoc board (first possible-moves) (board/get-marker depth)) (inc depth) true)))))

 (defn get-best-move [board turn]
  (loop [possible-moves (get-empty-board-square-indices board)
         best-move 0
         best-weight -1000]
   ;(println "all possible moves" possible-moves)
   (if (= 0 (count possible-moves))
    best-move
    (let [get-weight (mini-max (assoc board (first possible-moves) (board/get-marker turn)) turn true)
          square (first possible-moves)
          ]
     (println "square:" square "weight:" get-weight "best-weight:" best-weight "best-move:" best-move)
     (if (> get-weight best-weight)
      (recur (rest possible-moves) square get-weight)
      (recur (rest possible-moves) best-move best-weight)))
    )))

