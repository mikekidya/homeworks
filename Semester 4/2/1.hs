reverseList :: [a] -> [a]
reverseList list = reverseList' list []
    where reverseList' [] reversed = reversed
          reverseList' (first:listTail) reversed = reverseList' listTail (first:reversed)
