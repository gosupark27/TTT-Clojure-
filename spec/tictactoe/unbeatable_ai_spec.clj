(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "tic tac toe - unbeatable ai"

  ;(it "if game is not over keep playing"
  ;  (should (game-over? )))

  (it "evaluates an updated board with square"
    (should= 0 (eval-board-square \X 2 [\X \O \space \O \O \X \X \X \O]))
    (should= 10 (eval-board-square \X 8 [\O \X \O \O \O \X \X \X \space]))
    (should= -10 (eval-board-square \O 8 [\O \X \X \O \O \space \X \X \space]))
    (should= 0 (eval-board-square \X 3 [\O \X \space \O \O \space \X \X \space]))
    (should= 0 (eval-board-square \X -1 [\O \X \space \O \O \space \X \X \space]))
    )

  (it "recursively finds all the blank spaces in board"
    (should= [] (get-empty-board-square-indices [\X \O \X \O \O \X \X \X \O]))
    (should= [0] (get-empty-board-square-indices [\space \O \X \O \O \X \X \X \O]))
    (should= [0 5] (get-empty-board-square-indices [\space \O \X \O \O \space \X \X \O]))
    (should= [0 1 2 3 4 5 6 7 8] (get-empty-board-square-indices [\space \space \space \space \space \space \space \space \space]))
    )

  (it "minimax algo"
    (should= 8 (mini-max [\X \X \O \O \O \X \X \O \space] 9))
    (should= 7 (mini-max [\X \X \O \O \O \X \X \space \O] 9))
    (should= 4 (mini-max [\X \X \O \O \space \X \X \O \O] 9))
    (should= 2 (mini-max [\X \X \space \O \space \X \X \O \O] 7))
    )

  (xit (it "get best move using minimax algo"
    (should= 8 (get-best-move [\X \X \O \O \O \X \X \O \space] 9))
    (should= 7 (get-best-move [\X \X \O \O \O \X \X \space \O] 9))
    (should= 4 (get-best-move [\X \X \O \O \space \X \X \O \O] 9))
    (should= 2 (get-best-move [\X \X \space \O \space \X \X \O \O] 7))))

  )


