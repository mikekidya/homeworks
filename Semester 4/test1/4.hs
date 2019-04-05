supermap list action = foldr (++) [] (supermap' list action) where
    supermap' [] _ = []
    supermap' (head:tail) action = (map action [head]) ++ (supermap'  tail action)
