(ns tictactoe.main
 (:require [tictactoe.game-screen :as screen])
 (:require [tictactoe.game-board :as core]))

(defn -main []
 (println "Let's play a game of tic tac toe.\n")
 (screen/print-game-board-to-console @core/demo-board)
 (while (not (or (core/win? @core/game-board) (core/tie? @core/game-board)))
  (println "\n" @core/game-board "\n")
  (println "\nPlease enter a square (1 - 9) to place a marker")
  (let [square (dec (Integer/parseInt (read-line)))]
   (when-not (core/legal-move? @core/game-board square)
    (println "Not a legal move. Please enter a square (1 - 9) to place a marker")
    (def new-square (dec (Integer/parseInt (read-line))))
    (core/place-marker new-square @core/turn)
    )
   (core/place-marker square @core/turn)
   (swap! core/turn inc))
  (screen/print-game-board-to-console @core/game-board))
 )


[         ]