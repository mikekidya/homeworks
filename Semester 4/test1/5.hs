checkAll :: (a -> Bool) -> [a] -> Bool
checkAll condition list = foldr (&&) True (map condition list)