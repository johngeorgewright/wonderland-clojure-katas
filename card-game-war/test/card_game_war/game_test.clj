(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))

(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= (play-round [:spade 2] [:spade 3])
           [:spade 3]))
    (is (= (play-round [:diamond :king] [:diamond 8])
           [:diamond :king])))

  (testing "queens are higer rank than jacks"
    (is (= (play-round [:spade :jack] [:spade :queen])
           [:spade :queen])))

  (testing "kings are higer rank than queens"
    (is (= (play-round [:diamond :king] [:diamond :queen])
           [:diamond :king])))

  (testing "aces are higer rank than kings"
    (is (= (play-round [:club :king] [:club :ace])
           [:club :ace])))

  (testing "if the ranks are equal, clubs beat spades"
    (is (= (play-round [:club :king] [:spade :king])
           [:club :king])))

  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= (play-round [:club :king] [:diamond :king])
           [:diamond :king])))

  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= (play-round [:heart :king] [:diamond :king])
           [:heart :king]))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= (play-game [[:spade :jack]
                       [:diamond 9]]
                      [[:club 5]
                       [:club 2]])
           [[[:spade :jack]
             [:diamond 9]]
            []]))))
