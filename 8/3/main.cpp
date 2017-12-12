#include <iostream>
#include <fstream>

#include "hashtable.h"
#include "string.h"

using namespace std;

int main()
{
    ifstream inputFile;
    inputFile.open("input.txt");

    if (!inputFile.is_open())
    {
        cout << "File is not found" << endl;
        return 0;
    }

    char separators[9] = {' ', '\n', '\'', '"', ',', '.', '!', '?', ':'};

    HastTable *hastTable = createTable();

    while (!inputFile.eof())
    {
        String *newWord = inputString(inputFile, separators, 9);
        add(hastTable, newWord);
    }
    inputFile.close();

    cout << "Words and their frequencies:" << endl;
    printElements(hastTable, cout);
    cout << "Load factor (average bucket length): " << loadFactor(hastTable) << endl;
    cout << "Maximal bucket: " << endl;
    printMaxBucket(hastTable, cout);
    cout << "Number of added words: " << numberOfWords(hastTable) << endl;
    cout << "Number of empty buckets: " << numberOfEmptyBuckets(hastTable) << endl;

    deleteTable(hastTable);

    return 0;
}