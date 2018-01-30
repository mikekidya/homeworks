#include <iostream>
#include <fstream>

#include "hashtable.h"
#include "string.h"

using namespace std;

bool isSeparator(char symbol)
{
    return symbol == '\0' || symbol == '\n' || symbol == ' ' ||
           symbol == ','  || symbol == '!'  || symbol == '?' ||
           symbol == ':'  || symbol == ';'  || symbol == '.';
}

void addWords(HastTable *hastTable, String *string)
{
    int wordBegin = 0;
    int wordEnd = 0;
    int currentIndex = 0;
    while (currentIndex < stringLength(string))
    {
        currentIndex = wordEnd;
        while (currentIndex < stringLength(string) && isSeparator(getChar(string, currentIndex)))
            currentIndex++;
        wordBegin = currentIndex;

        while (currentIndex < stringLength(string) && !isSeparator(getChar(string, currentIndex)))
            currentIndex++;
        wordEnd = currentIndex;

        if (wordBegin < wordEnd)
            add(hastTable, subString(string, wordBegin, wordEnd - 1));
    }
    deleteString(string);
}

int main()
{
    ifstream inputFile;
    inputFile.open("input.txt");

    if (!inputFile.is_open())
    {
        cout << "File is not found" << endl;
        return 0;
    }

    HastTable *hastTable = createTable();

    while (!inputFile.eof())
    {
        String *newLine = inputString(inputFile);
        addWords(hastTable, newLine);
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