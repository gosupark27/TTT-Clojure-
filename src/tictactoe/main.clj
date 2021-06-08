(ns tictactoe.main
 (:require [tictactoe.game_screen :as screen])
 (:require [tictactoe.core :as core]))

(defn -main []
 (println "Let's play a game of tic tac toe.")
 (screen/print-board-to-console core/board)
 )
