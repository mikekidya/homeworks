plusMinusOnes :: [Integer]
plusMinusOnes = 1 : -1 : plusMinusOnes

alternatingSigns :: [Integer]
alternatingSigns = zipWith (*) plusMinusOnes [1..]