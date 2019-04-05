cornerMatrix :: Int -> [[Int]]
cornerMatrix 1 = [[1]]
cornerMatrix n = (map (++ [n]) (cornerMatrix (n - 1))) ++ [take n [n, n ..]]
