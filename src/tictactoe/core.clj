(ns tictactoe.core)

(defn tie? [board]
 (= 0 (count (filter #(clojure.string/blank? (str %)) board))))
