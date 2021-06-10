(ns tictactoe.main
 (:require [tictactoe.game_screen :as screen])
 (:require [tictactoe.core :as core]))

(defn -main []
 (println "Let's play a game of tic tac toe.")
 (screen/print-board-to-console @core/demo-board)
 (while (not (or (core/win? @core/board) (core/tie? @core/board)))
  (println "Please enter a square (1 - 9) to place a marker")
  (let [square (Integer/parseInt (read-line))]
   (while (not (core/legal-move? @core/board square))
    (println "Please enter a square (1 - 9) to place a marker")
    (Integer/parseInt (read-line))
    )
   (core/place-marker square @core/turn)
   (swap! core/turn inc))
  (screen/print-board-to-console @core/board))
 )


