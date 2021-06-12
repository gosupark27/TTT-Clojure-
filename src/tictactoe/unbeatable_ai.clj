(ns tictactoe.unbeatable-ai
 (:require [tictactoe.game-board :as board]))

(defn eval-board-square [square board]
 (board/place-marker square @board/turn)
 (cond
  (board/win? @board/game-board) 10
  (board/tie? @board/game-board) 0
  :else "POOP"
  ))

(defn get-empty-board-square-indices [board]
 (loop [empty-square-indices []
        index 0]
  (if (>= index (count board))
   empty-square-indices
   (if (clojure.string/blank? (str (nth board index)))
    (recur (conj empty-square-indices index) (inc index))
    (recur empty-square-indices (inc index)))
   )))

(defn mini-max [board]
 (.indexOf board \space))

