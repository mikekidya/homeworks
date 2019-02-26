sumOfDigits :: Integer -> Integer
sumOfDigits 0 = 0
sumOfDigits n = sumOfDigits (div n 10) + (mod n 10)
