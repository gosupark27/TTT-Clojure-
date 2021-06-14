(ns tictactoe.main
 (:require [tictactoe.game-screen :as screen])
 (:require [tictactoe.game-board :as board])
 (:require [tictactoe.unbeatable-ai :as ai]))

;(defn -main []
; (println "Let's play a game of tic tac toe.\n")
; (screen/print-game-board-to-console @core/demo-board)
; (while (not (or (core/win? @core/game-board) (core/tie? @core/game-board)))
;  (println "\n" @core/game-board "\n")
;  (println "\nPlease enter a square (1 - 9) to place a marker")
;  (let [square (dec (Integer/parseInt (read-line)))]
;   (when-not (core/legal-move? @core/game-board square)
;    (println "Not a legal move. Please enter a square (1 - 9) to place a marker")
;    (def new-square (dec (Integer/parseInt (read-line))))
;    (core/place-marker new-square @core/turn)
;    )
;   (core/place-marker square @core/turn)
;   (swap! core/turn inc))
;  (screen/print-game-board-to-console @core/game-board))
; )

(defn player-ai [turn]
 (board/place-marker (ai/get-best-move @board/game-board turn) turn))

(defn player-human [turn]
 (loop [legal? false]
  (println "\nPlease enter a square (1 - 9) to place a marker")
  (let [square (dec (Integer/parseInt (read-line)))]
   (if (true? (board/legal-move? @board/game-board square))
   (board/place-marker square turn)
   (recur false)))))

(defn -main []
 (println "Let's play a game of tic tac toe.\n")
 (screen/print-game-board-to-console @board/demo-board)
 (loop [turn 1]
  (if (or (< 10 turn) (or (board/tie? @board/game-board) (board/win? @board/game-board)))
   (println "Game Over!")
   (let []
    (if (not= 0 (mod turn 2))
     (player-ai turn)
     (player-human turn)
     )
    (screen/print-game-board-to-console @board/game-board)
    (recur (inc turn))))))