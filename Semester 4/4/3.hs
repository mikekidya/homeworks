import System.IO
import System.IO.Error
import Phonebook

main = do
    hSetBuffering stdin LineBuffering
    putStrLn "Phonebook app help:"
    putStrLn "1 {name} {phone without spaces} -- add new record"
    putStrLn "2 {name} -- find phone by name"
    putStrLn "3 {phone withous spaces} -- find name by phone"
    putStrLn "4 {fileName} -- save data into file"
    putStrLn "5 {fileName} -- load data from file"
    putStrLn "0 -- exit without save"
    doLoop (Phonebook [])

doLoop phonebook = do
    putStr "Phonebook >> "
    command <- getLine
    case (words command) of
        "1":new_name:new_phone:[] -> do
            doLoop (addRecord phonebook (Record new_name new_phone))
        "2":find_name:[] -> do
            putStrLn $ show $ findRecords phonebook (\x -> name x == find_name)
            doLoop phonebook
        "3":find_phone:[] -> do
            putStrLn $ show $ findRecords phonebook (\x -> phone x == find_phone)
            doLoop phonebook
        "4":saveFile:[] -> do
            save phonebook saveFile
            doLoop phonebook
        "5":loadFile:[] -> do
            newPhonebook <- tryIOError (load loadFile)
            case newPhonebook of
                Left e -> do
                    putStrLn "File reading error. Does this file exist?"
                    doLoop phonebook
                Right correctResult -> doLoop correctResult
        "0":[] ->
            putStrLn "Good bye"
        _ -> do
            putStrLn "Incorrect command"
            doLoop phonebook
