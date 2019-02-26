zipWithoutDropping :: (a -> a -> a) -> [a] -> [a] -> [a]
zipWithoutDropping f list1 list2 = 
    case (list1, list2) of
        ([], x) -> x
        (x, []) -> x
        (head1:tail1, head2:tail2) -> (f head1 head2) : (zipWithoutDropping f tail1 tail2)

sum3 :: (Num a) => [a] -> [a] -> [a] -> [a]
sum3 list1 list2 list3 = zipWithoutDropping (+) ((zipWithoutDropping) (+) list1 list2) list3
