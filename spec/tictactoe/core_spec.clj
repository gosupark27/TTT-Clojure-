(ns tictactoe.core-spec
 (:require [speclj.core :refer :all]
           [tictactoe.core :refer :all]))

(describe "tic tac toe game logic"

 (it "place a marker on the board"
  (should=[\space \space \O \space \space \space \space \space \space] (place-marker 2 4))
  (should=[\space \space \X \space \space \space \space \space \space] (place-marker 2 1))
  )

 (it "get marker of current player"
  (should= \X (get-marker 1))
  (should= \O (get-marker 2))
  (should= \X (get-marker 3))
  (should= \O (get-marker 4))
  )

 (it "Check if move is legal"
  (should (legal-move? [\space \space \space \space \space \space \space \space \space] 2))
  (should-not (legal-move? [\space \space \X \space \space \space \space \space \space] 2))
  (should-not (legal-move? [\space \space \space \space \space \space \space \space \space] nil))
  (should-not (legal-move? [\space \space \space \space \space \space \space \space \space] -1))
  (should-not (legal-move? [\space \space \space \space \space \space \space \space \space] 9))
  )

 (it "Check game for a winner"
  (should-not (win? [\space \space \space \space \space \space \space \space \space] \X))
  (should-not (win? [\X \space \space \space \space \space \space \space \space] \X))
  (should-not (win? [\X \space \O \space \space \space \space \space \space] \X))
  (should-not (win? [\X \X \O \space \space \space \space \space \space] \X))
  (should-not (win? [\X \X \O \O \space \space \space \space \space] \X))
  (should-not (win? [\X \X \O \O \O \space \space \space \space] \X))
  (should-not (win? [\X \X \O \O \O \X \space \space \space] \X))
  (should-not (win? [\X \X \O \O \O \X \X \O \space] \X))
  (should-not (win? [\X \X \O \O \O \X \X \O \X] \X))
  (should (win? [\X \X \X \space \space \space \space \space \space] \X))
  (should (win? [\space \space \space \X \X \X \space \space \space] \X))
  (should (win? [\space \space \space \space \space \space \X \X \X] \X))
  (should (win? [\O \space \space \O \space \space \O \space \space] \O))
  (should (win? [\space \O \space \space \O \space \space \O \space] \O))
  (should (win? [\space \space \O \space \space \O \space \space \O] \O))
  (should (win? [\O \space \space \space \O \space \space \space \O] \O))
  (should (win? [\space \space \X \space \X \space \X \space \space] \X))
  )

 (it "Check if game is tied"
  (should (tie? [\X \O \X \O \O \X \X \X \O]))
  (should-not (tie? [\X \O \space \O \O \X \X \X \O]))
  )
 )
