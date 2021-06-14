(ns tictactoe.main
 (:require [tictactoe.game-screen :as screen])
 (:require [tictactoe.game-board :as board])
 (:require [tictactoe.unbeatable-ai :as ai]))

(defn player-ai [turn]
 (board/place-marker (ai/get-best-move @board/game-board) turn))

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