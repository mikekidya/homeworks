maxIndex = (fst . maxElement . zip [0..]) where
    maxElement [x] = x
    maxElement (first:second:tail) = maxElement ((if snd first >= snd second then first else second) : tail)

maxPairSumIndex lst = maxIndex (zipWith (+) lst (0:lst))
