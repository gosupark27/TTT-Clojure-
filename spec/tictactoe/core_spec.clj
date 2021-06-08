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
  (should (legal-move? [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] 2))
  (should-not (legal-move? [\u0020 \u0020 \X \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] 2))
  (should-not (legal-move? [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] nil))
  (should-not (legal-move? [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] -1))
  (should-not (legal-move? [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] 9))
  )

 (it "Check game for a winner"
  (should-not (win? [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \u0020 \O \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \X \O \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \X \O \O \u0020 \u0020 \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \X \O \O \O \u0020 \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \X \O \O \O \X \u0020 \u0020 \u0020] \X))
  (should-not (win? [\X \X \O \O \O \X \X \O \u0020] \X))
  (should-not (win? [\X \X \O \O \O \X \X \O \X] \X))
  (should (win? [\X \X \X \u0020 \u0020 \u0020 \u0020 \u0020 \u0020] \X))
  (should (win? [\u0020 \u0020 \u0020 \X \X \X \u0020 \u0020 \u0020] \X))
  (should (win? [\u0020 \u0020 \u0020 \u0020 \u0020 \u0020 \X \X \X] \X))
  (should (win? [\O \u0020 \u0020 \O \u0020 \u0020 \O \u0020 \u0020] \O))
  (should (win? [\u0020 \O \u0020 \u0020 \O \u0020 \u0020 \O \u0020] \O))
  (should (win? [\u0020 \u0020 \O \u0020 \u0020 \O \u0020 \u0020 \O] \O))
  (should (win? [\O \u0020 \u0020 \u0020 \O \u0020 \u0020 \u0020 \O] \O))
  (should (win? [\u0020 \u0020 \X \u0020 \X \u0020 \X \u0020 \u0020] \X))
  )

 (it "Check if game is tied"
  (should (tie? [\X \O \X \O \O \X \X \X \O]))
  (should-not (tie? [\X \O \u0020 \O \O \X \X \X \O]))
  )
 )
