(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 :jack :queen :king :ace])
(def cards
  (for [rank ranks
        suit suits]
    [suit rank]))

(defn play-round [player1-card player2-card]
  (if (> (.indexOf cards player1-card)
         (.indexOf cards player2-card))
    player1-card
    player2-card))

(defn play-game [player1-cards player2-cards]
  (loop [player1-cards player1-cards
         player2-cards player2-cards]
    (if (or (empty? player1-cards)
            (empty? player2-cards))
      [player1-cards player2-cards]
      (let [player1-round (first player1-cards)
            player2-round (first player2-cards)]
        (if (= (play-round player1-round player2-round)
               player1-round)
          (recur player1-cards
                 (vec (rest player2-cards)))
          (recur (vec (rest player1-cards))
                 player2-cards))))))
