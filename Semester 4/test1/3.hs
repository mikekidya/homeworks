rhombusLine level size = (rhombusLine' (size - level) level) ++ "\n" where
    rhombusLine' 0 1 = "*"
    rhombusLine' 0 n = "*" ++ (rhombusLine' 0 (n - 1)) ++ "*"
    rhombusLine' t n = " " ++ (rhombusLine' (t - 1) n) ++ " "

rhombus size = rhombus' size 1 where
    rhombus' size level = if size == level 
                            then rhombusLine size size
                            else (rhombusLine level size) 
                                ++ (rhombus' size (level + 1)) 
                                ++ (rhombusLine level size)

printRhombus = putStr . rhombus
