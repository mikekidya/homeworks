module Phonebook where

data Record = 
    Record {
        name :: String,
        phone :: String
    }

instance Show Record where
    show record = name record ++ " " ++ phone record ++ "\n" 

data Phonebook = Phonebook [Record]

instance Show Phonebook where
    show (Phonebook records) = concatMap show records

findRecords :: Phonebook -> (Record -> Bool) -> Phonebook
findRecords (Phonebook records) condition = Phonebook (filter condition records)

addRecord :: Phonebook -> Record -> Phonebook
addRecord (Phonebook records) new_rec = Phonebook (new_rec : records)

save :: Phonebook -> String -> IO()
save phonebook path = do
    writeFile path $ show phonebook

load :: String -> IO(Phonebook)
load path = do
    file <- readFile path
    return $ Phonebook (map ((\x -> Record (x !! 0) (x !! 1)) . words) $ lines file)
