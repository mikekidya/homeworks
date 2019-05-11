import Data.List
import Data.Char

data Addend = 
    Addend 
        Float -- coefficient
        Int -- power

deriveAddend :: Addend -> Addend
deriveAddend (Addend coef pow) = Addend (coef * (fromIntegral pow)) (pow - 1)

multAddend :: Addend -> Addend -> Addend
multAddend (Addend coef1 pow1) (Addend coef2 pow2) = Addend (coef1 * coef2) (pow1 + pow2)

instance Show Addend where
    show (Addend 0  _) = "0"
    show (Addend coef  0) = show coef
    show (Addend 1 p) | p > 0       = intercalate "*" (take p vars) 
                      | otherwise   = intercalate "/" ("1" : (take (-p) vars)) where
                                vars = "x" : vars
    show (Addend (-1) p) = '-' : show (Addend 1  p)
    show (Addend coef p) | p < 0 = show coef ++ (tail $ show (Addend 1 p))
                         | p > 0 = show coef ++ "*" ++ (show (Addend 1 p))


data Expression = Expression [Addend]

simplify :: Expression -> Expression
simplify (Expression addends) = Expression $ filter notNull $ map sum $ groupBy equalPow $ sortBy cmp addends
    where 
        cmp (Addend _ pow1) (Addend _ pow2) = compare pow1 pow2
        equalPow (Addend _ pow1) (Addend _ pow2) = pow1 == pow2
        sum (x:xs) = foldl sum' x xs
        sum' (Addend coef1 pow) (Addend coef2 _) = Addend (coef1 + coef2) pow
        notNull (Addend coef _) = coef /= 0

derive :: Expression -> Expression
derive (Expression addends) = simplify $ Expression (map deriveAddend addends)

parse :: String -> Expression
parse s =  Expression (map (foldl multAddend (Addend 1 0) . map parseAddend . splitByMD) $ splitByPM s)
    where
        replace from curr | curr == from = ' '
                          | otherwise    = curr
        splitByPM s = concatMap (words . map (replace '+')) 
                            $  map (\x -> '-':x) $ words $ map (replace '-') 
                            $ ("0+" ++) $ concat $ words s
        splitByMD s = concatMap (words . map (replace '*')) 
                            $  map (\x -> '/':x) $ words $ map (replace '/') 
                            $ ("1*" ++) $ concat $ words s
        str2int str = read str :: Float
        parseAddend "" = Addend 1 0
        parseAddend "/x" = Addend 1 (-1)
        parseAddend "x" = Addend 1 1
        parseAddend "-x" = Addend (-1) 1
        parseAddend ('/':xs) = Addend (1 / (str2int xs)) 0
        parseAddend xs = Addend (str2int xs) 0

instance Show Expression where
    show p = if res == "" then "0" else res
        where
            res = show' $ simplify p 
            show' (Expression []) = ""
            show' (Expression (x:xs)) = foldl conc (show x) $ map show xs 
                where
                    conc a "" = a
                    conc a ('-':b) = a ++ ('-':b)
                    conc a b = a ++ "+" ++ b

test =
    (show $ derive $ parse "12.3*x - 198") == "12.3" &&
    (show $ derive $ parse "12.3/x - 1/x*2") == "-10.3/x/x" &&
    (show $ derive $ parse "0") == "0" &&
    (show $ derive $ parse "-12.5*165/124*x/x") == "0" &&
    (show $ derive $ parse "2/2/2*x*x") == "x" &&
    (show $ derive $ parse "x - x + x - x + x + x + x") == "3.0"
