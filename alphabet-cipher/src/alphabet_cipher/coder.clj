(ns alphabet-cipher.coder)

(use '[clojure.string :only (join)])

(def alphabet ["a" "b" "c" "d" "e" "f" "g" "h" "i"
               "j" "k" "l" "m" "n" "o" "p" "q" "r"
               "s" "t" "u" "v" "w" "x" "y" "z"])

(def alphabetCount (count alphabet))

(defn encodeChar [keywordPos messagePos]
  (let [encodedCharPos (+ messagePos keywordPos)
        encodedCharPos (if (> encodedCharPos (dec alphabetCount))
                         (- encodedCharPos alphabetCount)
                         encodedCharPos)]
    encodedCharPos))

(defn decodeChar [keywordPos messagePos]
  (let [decodedCharPos (- messagePos keywordPos)
        decodedCharPos (if (< decodedCharPos 0)
                         (+ decodedCharPos alphabetCount)
                         decodedCharPos)]
    decodedCharPos))

(defn code [method keyword message]
  (loop [keywordChars (seq keyword)
         messageChars (seq message)
         coded '()]
    (if (empty? messageChars)
      (join "" coded)
      (let [keywordPos (.indexOf alphabet (str (first keywordChars)))
            messagePos (.indexOf alphabet (str (first messageChars)))
            moreKeywordChars (rest keywordChars)
            codedCharPos (apply method [keywordPos messagePos])]
        (recur (if (empty? moreKeywordChars) (seq keyword) moreKeywordChars)
               (rest messageChars)
               (concat coded (get alphabet codedCharPos)))))))

(defn encode [keyword message]
  (code encodeChar keyword message))

(defn decode [keyword message]
  (code decodeChar keyword message))
