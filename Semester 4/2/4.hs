find :: (Eq a) => [a] -> a -> Integer
find list value = find' (zip list [0..]) value
    where find' [] _ = -1
          find' (head:tail) value = if fst head == value then snd head else find' tail value
