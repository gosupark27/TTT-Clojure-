(ns tictactoe.unbeatable-ai
 (:require [tictactoe.game-board :as board]))

(defn eval-board-square [mark square board]
 (let [key (if (nil? square) 0 square) ;;delete
       updated-board (assoc board key mark)]
  (println "eval-key" key)
  (cond
   (and (= mark \X) (board/win? updated-board)) -10
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

(defn mini-max [board turn]
 (loop [maximizer? true
        depth turn
        new-board board]
  (let [possible-moves (get-empty-board-square-indices new-board)]
   (println "board:" new-board)
   (println "all empty squares:" possible-moves)
   (println "key:" (first possible-moves))
   (println "depth:" depth)
   (if (or (= 0 (count possible-moves)) (= depth 9) (or (board/win? board) (board/tie? board)))
    (eval-board-square (board/get-marker turn) (first possible-moves) new-board)
    (if maximizer?
     (recur false (inc depth) (assoc new-board (first possible-moves) (board/get-marker depth)))
     (recur true (inc depth) (assoc new-board (first possible-moves) (board/get-marker depth))))))))

 (defn get-best-move [board turn]
  (loop [possible-moves (get-empty-board-square-indices board)
         best-move 0
         best-weight 100]
   (println "num" best-move)
   (if (= 0 (count possible-moves))
    best-move
    (let [get-weight (mini-max (assoc board (first possible-moves) (board/get-marker turn)) turn)
          square (first possible-moves)
          ]
     (if (< get-weight best-weight)
      (recur (rest possible-moves) square get-weight)
      (recur (rest possible-moves) best-move best-weight)))
    )))

