(ns tictactoe.core)

(def board (atom {:1 "" :2 "" :3 "" :4 "" :5 "" :6 "" :7 "" :8 "" :9 ""}))

(def board-keys (map keyword (map str (range 1 10))))

(defn make-move [n mark]
 (swap! board assoc n mark))

(defn filled? [k]
 (clojure.string/blank? (get @board k)))

(defn cats-game?
 (= true (every? true? (map filled? board-keys)))
 )
