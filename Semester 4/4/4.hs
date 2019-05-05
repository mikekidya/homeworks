import Data.List

data Monomial = 
    Monomial 
        Integer -- coefficient
        Integer -- power

multMonoms :: Monomial -> Monomial -> Monomial
multMonoms (Monomial coef1 pow1) (Monomial coef2 pow2) = Monomial (coef1 * coef2) (pow1 + pow2)

instance Show Monomial where
    show (Monomial 0 _) = ""
    show (Monomial coef 0) = show coef 
    show (Monomial coef pow) = showCoef ++ showX where
        showCoef | coef == 1    = ""
                 | coef == -1   = "-"
                 | otherwise    = show coef
        showX = "x" ++ if pow == 1 then "" else ("^" ++ show pow)


data Polynomial = Polynomial [Monomial]

simplify :: Polynomial -> Polynomial
simplify (Polynomial monoms) = Polynomial $ filter notNull $ map sum $ groupBy equalPow $ sortBy cmp monoms
    where 
        cmp (Monomial _ pow1) (Monomial _ pow2) = compare pow1 pow2
        equalPow (Monomial _ pow1) (Monomial _ pow2) = pow1 == pow2
        sum (x:xs) = foldl sum' x xs
        sum' (Monomial coef1 pow) (Monomial coef2 _) = Monomial (coef1 + coef2) pow
        notNull (Monomial coef _) = coef /= 0

add :: Polynomial -> Polynomial -> Polynomial
add (Polynomial monoms1) (Polynomial monoms2) = simplify $ Polynomial (monoms1 ++ monoms2)

mult :: Polynomial -> Polynomial -> Polynomial
mult (Polynomial monoms1) (Polynomial monoms2) = simplify $ Polynomial [multMonoms x y | x <- monoms1, y <- monoms2]

list2polynom :: [Integer] -> Polynomial
list2polynom l = Polynomial $ map (\(x, y) -> Monomial x y) $ zip l [0..]

instance Show Polynomial where
    show p = show' $ simplify p where
        show' (Polynomial []) = ""
        show' (Polynomial (x:xs)) = foldl conc (show x) $ map show xs 
            where
                conc a "" = a
                conc a ('-':b) = a ++ ('-':b)
                conc a b = a ++ "+" ++ b



test =
    let a = Polynomial [Monomial 1 2, Monomial 2 4]
        b = Polynomial [Monomial 5 2, Monomial (-2) 4]
        c = Polynomial [Monomial 0 1]
        d = Polynomial []
        add1 = add a b
        add2 = add a d
        add3 = add b c
        mult1 = mult a b
        mult2 = mult a c 
        mult3 = mult b d
    in
        show a == "x^2+2x^4" &&
        show b == "5x^2-2x^4" &&
        show c == "" &&
        show d == "" &&
        show add1 == "6x^2" &&
        show add2 == "x^2+2x^4" &&
        show add3 == "5x^2-2x^4" &&
        show mult1 == "5x^4+8x^6-4x^8" &&
        show mult2 == "" &&
        show mult3 == ""
