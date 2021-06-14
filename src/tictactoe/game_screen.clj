(ns tictactoe.game-screen
 (:require [tictactoe.game-board :as core]))

(defn get-nth-row [board n]
 (let [row (cond
            (= 1 n) (subvec board 0 3)
            (= 2 n) (subvec board 3 6)
            (= 3 n) (subvec board 6 9)
            )]
  row))

(defn get-board-row [row]
 (clojure.string/join " | " row))

(defn get-board [board]
 (let [row-1 (map #(str %) (get-nth-row board 1))
       row-2 (map #(str %) (get-nth-row board 2))
       row-3 (map #(str %) (get-nth-row board 3))
       rows (vector row-1 row-2 row-3)]
  (interpose "--+---+--" (map #(get-board-row %) rows))))

(defn print-game-board-to-console [board]
 (doseq [gameboard (get-board board)]
  (println gameboard)))
