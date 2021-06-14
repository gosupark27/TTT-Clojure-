(ns tictactoe.unbeatable-ai-spec
 (:require [speclj.core :refer :all]
           [tictactoe.unbeatable-ai :refer :all]))

(def x \X)
(def o \O)
(def - \space)

(describe "tic tac toe - unbeatable ai"

 (it "check if board has a win or tie"
  (should (eval-board? [x o x
                        o o x
                        x x o]))
  (should (eval-board? [o x o
                        o o x
                        x x x]))
  (should (eval-board? [o x x
                        o o -
                        x x o]))
  (should-not (eval-board? [o x -
                            o o -
                            x x -]))
  )

 (it "recursively finds all the blank spaces in board"
  (should= [] (get-empty-board-square-indices [x o x
                                               o o x
                                               x x o]))
  (should= [0] (get-empty-board-square-indices [- o x
                                                o o x
                                                x x o]))
  (should= [0 5] (get-empty-board-square-indices [- o x
                                                  o o -
                                                  x x o]))
  (should= [0 1 2 3 4 5 6 7 8] (get-empty-board-square-indices [- - -
                                                                - - -
                                                                - - -]))
  )

 (it "minimax algo"
  (should= 0 (mini-max [x x o
                        o o x
                        x o -]))
  (should= 0 (mini-max [x x -
                        o - x
                        x o o]))
  (should= 0 (mini-max [x x o
                        o o -
                        x - -]))
  (should= 1 (mini-max [x x -
                        o o x
                        o o x]))
  (should= 3 (mini-max [o - x
                        - o -
                        o x x]))
  (should= 3 (mini-max [- x -
                        o - x
                        - o -]))

  (should= -2 (mini-max [x x o
                         o - x
                         - o o]))
  (should= -4 (mini-max [o - x
                         - o -
                         o - x]))
  )

 (it "get best move using minimax algo"
  (should= 8 (get-best-move [x x o
                             o o x
                             x o -]))
  ; T-8 - if AI played for "O"
  ;(should= 7 (get-best-move[x x o
  ;                          o o x
  ;                          x - -]))
  (should= 5 (get-best-move [x x o
                             o o -
                             x - -]))
  ; T-6 - if AI played for "O"
  ;(should= 3 (get-best-move[x x o
  ;                          - o -
  ;                          x - -]))
  (should= 6 (get-best-move [x x o
                             - o -
                             - - -]))
  ;T-4 - if AI played for "O"
  ;(should= 2 (get-best-move[x x -
  ;                          - o -
  ;                          - - -]))
  (should= 1 (get-best-move [x - -
                             - o -
                             - - -]))
  ;T-2 - if AI played for "O"
  ;(should= 4 (get-best-move[x - -
  ;                          - - -
  ;                          - - -]))
  (should= 0 (get-best-move [- - -
                             - - -
                             - - -]))
  (should= 2 (get-best-move [x x -
                             o o -
                             - - -]))
  )
 )

