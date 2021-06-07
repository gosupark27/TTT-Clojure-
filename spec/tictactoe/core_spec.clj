(ns tictactoe.core-spec
 (:require [speclj.core :refer :all]
           [tictactoe.core :refer :all]))

(describe "tic tac toe game"

 (it "Check if game is tied"
  (should (tie? [\X \O \X \O \O \X \X \X \O]))
  (should-not (tie? [\X \O \u0020 \O \O \X \X \X \O]))
  )
 )
