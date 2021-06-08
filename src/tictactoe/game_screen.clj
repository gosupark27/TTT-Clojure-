(ns tictactoe.game_screen
 (:require [tictactoe.core :as core]))

(defn get-board-row [row]
 (clojure.string/join " | " row)
 )

(defn get-nth-row [board n]
 (let [row (cond
            (= 1 n) (subvec board 0 3)
            (= 2 n) (subvec board 3 6)
            (= 3 n) (subvec board 6 9)
            )]
  row))

(defn get-board [board]
 (let [row-1 (map #(str %) (get-nth-row board 1))
       row-2 (map #(str %) (get-nth-row board 2))
       row-3 (map #(str %) (get-nth-row board 3))
       rows (vector row-1 row-2 row-3)
       board (interpose "--+---+--" (map #(get-board-row %) rows))]
  board))

(defn print-board-to-console [board]
 (doseq [gameboard (get-board board)]
  (println gameboard)))

(defn get-user-input []
 (let [n (Integer/parseInt (read-line))]
  n ))