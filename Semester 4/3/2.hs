listOfNumsOf179 = 
    1:7:9: 
    concat (map (addLastDigits (take 3 listOfNumsOf179)) listOfNumsOf179) where
        addLastDigits list number = map (+(10 * number)) list
