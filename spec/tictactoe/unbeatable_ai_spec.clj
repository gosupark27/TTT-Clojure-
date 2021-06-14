(ns tictactoe.unbeatable-ai-spec
  (:require [speclj.core :refer :all]
            [tictactoe.unbeatable-ai :refer :all]))

(describe "tic tac toe - unbeatable ai"

  ;(it "if game is not over keep playing"
  ;  (should (game-over? )))

  (it "evaluates an updated board with square"
    (should= 0 (eval-board-square \X [\X \O \X \O \O \X \X \X \O]))
    (should= 10 (eval-board-square \X  [\O \X \O \O \O \X \X \X \X]))
    (should= -10 (eval-board-square \O [\O \X \X \O \O \space \X \X \O]))
    (should= nil (eval-board-square \X [\O \X \space \O \O \space \X \X \space]))
    )

  (it "recursively finds all the blank spaces in board"
    (should= [] (get-empty-board-square-indices [\X \O \X \O \O \X \X \X \O]))
    (should= [0] (get-empty-board-square-indices [\space \O \X \O \O \X \X \X \O]))
    (should= [0 5] (get-empty-board-square-indices [\space \O \X \O \O \space \X \X \O]))
    (should= [0 1 2 3 4 5 6 7 8] (get-empty-board-square-indices [\space \space \space \space \space \space \space \space \space]))
    )

  (xit (it "minimax algo"
    (should= 0 (mini-max [\X \X \O \O \O \X \X \O \space] 9))
    (should= 10 (mini-max [\X \X \space \O \O \X \X \O \X] 9))
    (should= -10 (mini-max [\X \X \O \O \space \X \space \O \O] 8))
    (should= 0 (mini-max [\X \X \space \O \space \X \X \O \O] 8))
    (should= 0 (mini-max [\X \X \O \O \O \space \X \space \space] 7))
    (should= 10 (mini-max [\O \space \X \space \O \space \O \X \X] 7))
    (should= -10 (mini-max [\O \space \X \space \O \space \O \space \X] 6))
    ))

  (it "get best move using minimax algo"
    ;(should= 8 (get-best-move [\X \X \O \O \O \X \X \O \space] 9))
    ;(should= 7 (get-best-move [\X \X \O \O \O \X \X \space \O] 9))
    ;(should= 4 (get-best-move [\X \X \O \O \space \X \X \O \O] 9))
    ;(should= 4 (get-best-move [\X \X \O \O \space \X \space \O \O] 8))
    ;(should= 2 (get-best-move [\X \X \space \O \space \X \X \O \O] 7))
    ;(should= 5 (get-best-move [\O \space \X \space \O \space \space \space \X] 5))
    (should= 5 (get-best-move [\X \space \space \space \O \space \space \space \space] 3))
    )
  )


