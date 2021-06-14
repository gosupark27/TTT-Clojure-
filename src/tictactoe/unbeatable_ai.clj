(ns tictactoe.unbeatable-ai
 (:require [tictactoe.game-board :as board]))

(defn eval-board-square [board]
 (or
  (board/win? board)
  (board/tie? board)))

(defn get-empty-board-square-indices [board]
 (loop [empty-square-indices []
        index 0]
  (if (>= index (count board))
   empty-square-indices
   (if (clojure.string/blank? (str (nth board index)))
    (recur (conj empty-square-indices index) (inc index))
    (recur empty-square-indices (inc index)))
   )))

(defn game-over? [possible-moves board]
 (or (= 0 (count possible-moves))
     (eval-board-square board)))

(defn mini-max [board]
 (let [possible-moves (get-empty-board-square-indices board)
       depth (- 9 (count possible-moves))
       me (board/get-marker depth)
       him (if (= me \X) \O \X)
       over? (game-over? possible-moves board)]
  (if over?
   (if (board/win? board)
    (if (= him \X)
     (- depth 10)
     (- 10 depth))
    0)
   (let [new-boards (map #(assoc board % him) possible-moves)
         scores (mapv #(mini-max %) new-boards)]
    (if (= me \X)
     (apply min scores)
     (apply max scores))
    ))))

(defn get-best-move [board]
 (let [possible-moves (get-empty-board-square-indices board)
       mark (board/get-marker (- 10 (count possible-moves)))]
  (loop [possible-moves possible-moves
         best-move (first possible-moves)
         best-weight -10]
   (if (= 0 (count possible-moves))
    best-move
    (let [turn (- 10 (count possible-moves))
          square (first possible-moves)
          new-board (assoc board square mark)
          get-weight (mini-max new-board)]
     (if (> get-weight best-weight)
      (recur (rest possible-moves) square get-weight)
      (recur (rest possible-moves) best-move best-weight)))
    ))))

