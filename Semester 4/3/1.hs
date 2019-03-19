{-- given function
func x l = map (\y -> y*x) l

-- reducing lambda
func x l = map (*x) l

-- reducing last arg l
func x = map (*x)

-- reducing arg x and adding the compostion -}
func :: Num a => a -> [a] -> [a]
func = map.(*)
