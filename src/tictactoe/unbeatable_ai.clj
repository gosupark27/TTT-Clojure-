(ns tictactoe.unbeatable-ai
 (:require [tictactoe.game-board :as board]))

;(defn eval-board-square [square board]
; (board/place-marker square @board/turn)
; (cond
;  (board/win? updated-board) 10
;  (board/tie? updated-board) 0
;  :else nil
;  ))

(defn eval-board-square [mark square board]
 (let [updated-board (assoc board square mark)]
  (cond
   (and (= mark \O) (board/win? updated-board)) -10
   (board/win? updated-board) 10
   (board/tie? updated-board) 0)))

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

