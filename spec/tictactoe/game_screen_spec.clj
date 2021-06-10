(ns tictactoe.game-screen-spec
 (:require [speclj.core :refer :all]
           [tictactoe.game_screen :refer :all]))
(describe "tic tac toe console"

 (it "gets nth row of game board"
  (should= [\X \X \O] (get-nth-row [\X \X \O \O \O \X \O \X \X] 1))
  (should= [\O \O \X] (get-nth-row [\X \X \O \O \O \X \O \X \X] 2))
  (should= [\O \X \X] (get-nth-row [\X \X \O \O \O \X \O \X \X] 3))
  )

 (it "gets the game board row with divider"
  (should= " |  | " (get-board-row ["" "" ""]))
  (should= "X | X | O" (get-board-row `("X" "X" "O")))
  )

 (it "get a sequence of string board components"
  (should= ["  |   |  " "--+---+--" "  |   |  " "--+---+--" "  |   |  "] (get-board [\space \space \space \space \space \space \space \space \space]))
  (should= ["X | X | O" "--+---+--" "O | O | X" "--+---+--" "O | X | X"] (get-board [\X \X \O \O \O \X \O \X \X]))
  )
 )