powersOf2 :: Integer -> [Integer]
powersOf2 n = reverse (powersOf2' n)
    where powersOf2' 0 = [1]
          powersOf2' n = (2 * head prev) : prev
              where prev = powersOf2' (n - 1)
