fib :: Integer -> Integer
fib n | n < 0 && (mod n 2) == 0  = - fib' (abs n) 1 0 
      | otherwise                = fib' (abs n) 1 0 
            where fib' 0 x y = y
                  fib' n x y = fib' (n - 1) (x + y) x 


test :: Bool
test = [fib x | x <- [-5..5]] == [5, -3, 2, -1, 1, 0, 1, 1, 2, 3, 5]