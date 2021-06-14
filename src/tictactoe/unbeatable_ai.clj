(ns tictactoe.unbeatable-ai
 (:require [tictactoe.game-board :as board]))

(defn eval-board-square [mark square board]
 (let [updated-board (assoc board square mark)]
  (cond
   (and (= mark \O) (board/win? updated-board)) -10
   (board/win? updated-board) 10
   :else 0)))

(defn get-empty-board-square-indices [board]
 (loop [empty-square-indices []
        index 0]
  (if (>= index (count board))
   empty-square-indices
   (if (clojure.string/blank? (str (nth board index)))
    (recur (conj empty-square-indices index) (inc index))
    (recur empty-square-indices (inc index)))
   )))

;(defn mini-max [board]
; (.indexOf board \space))
;(defn mini-max [board]
; (loop [possible-moves (get-empty-board-square-indices board)
;        best-move -1
;        maximizer? true
;        marker \X]
;  (let [
;        ;possible-moves-keys (map #(keyword (str %)) possible-moves)
;        empty-square-weights (map #(eval-board-square marker % board) possible-moves)
;        square-to-weights (map #(vector % %2) possible-moves empty-square-weights)
;        maximizer (filter #(<= 0 (second %)) square-to-weights)
;        minimizer (filter #(>= (second %) 0) square-to-weights)]
;   (println "possible moves: " possible-moves)
;   (println "possible move keys: " possible-moves-keys)
;   (println "empty square weights: " empty-square-weights)
;   (println "square to weights " square-to-weights)
;   (println "maximizer: " maximizer)
;   (println "minimizer: " minimizer)
;   (println "best move: " best-move)
;   (if (= 0 (count possible-moves))
;    best-move
;    (if maximizer?
;     (recur (rest possible-moves) (first (first maximizer)) false \O)
;     (recur (rest possible-moves) (first minimizer) true \X))))
;
;  ))
(defn mini-max [board turn]
 (loop [maximizer? true
        depth turn
        new-board board]
  (let [possible-moves (get-empty-board-square-indices new-board)]
   (if (or (<= depth 9) (or (board/win? board) (board/tie? board)))
    (eval-board-square (board/get-marker turn) -1 new-board)
    (if maximizer?
     (recur false (inc depth) (assoc new-board (first possible-moves) (board/get-marker turn)))
     (recur true (inc depth) (assoc new-board (first possible-moves) (board/get-marker turn))))))))

 (defn get-best-move [board turn]
  (loop [possible-moves (get-empty-board-square-indices board)
         best-move -1
         best-weight ##Inf]
   (let [get-square-to-weight (minimax (assoc board (first possible-moves) (board/get-marker turn)) turn)
         square (first get-square-to-weight)
         weight (second get-square-to-weight)]
    (if (= 0 (count possible-moves))
     best-move
     (if (< weight best-weight)
      (recur (rest possible-moves) square weight)
      (recur (rest possible-moves) best-move best-weight))))))

