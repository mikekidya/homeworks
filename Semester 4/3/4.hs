reduceSeq :: [Char] -> [Char]
reduceSeq [] = []
reduceSeq [a] = [a]
reduceSeq (x:y:xs) 
    | isCompliment x y  = reduceSeq xs
    | otherwise         = if reduced ==  y : xs 
                          then x : reduced 
                          else reduceSeq (x : reduced)
                              where reduced = reduceSeq (y : xs)


isCompliment :: Char -> Char -> Bool
isCompliment x y = (x == '(' && y == ')') ||
                   (x == '{' && y == '}') ||
                   (x == '[' && y == ']')


isCorrectSeq :: [Char] -> Bool
isCorrectSeq seq = [] == reduceSeq seq


test :: Bool
test = (isCorrectSeq "") &&
       not (isCorrectSeq "(") &&
       not (isCorrectSeq "([)]") &&
       (isCorrectSeq "()[]") &&
       (isCorrectSeq "{[]}") &&
       (isCorrectSeq "{[([])]}{()}[]")
