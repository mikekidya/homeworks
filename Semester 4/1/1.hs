factorial :: Int -> Int
factorial n = if n > 1
              then n * factorial (n - 1)
              else 1

main = print (factorial 5)
