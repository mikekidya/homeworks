#include <iostream>
#include <fstream>
using namespace std;

bool isLetter(char c)
{
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

bool isBigLetter(char c)
{
    return (c >= 'A' && c <= 'Z');
}

void fillFalse(bool *array, int length)
{
    for (int i = 0; i < length; i++)
        array[i] = false;
}

int main()
{
    ifstream text;
    text.open("input.txt");

    int const numberOfLetters = 26;
    bool hasLetter[numberOfLetters];
    fillFalse(hasLetter, numberOfLetters);

    bool isNewWord = true;
    char currentSymbol = '\0';
    while (!text.eof())
    {
        text.get(currentSymbol);
        if (isLetter(currentSymbol))
        {
            isNewWord = true;
            if (isBigLetter(currentSymbol))
            {
                if (!hasLetter[currentSymbol - 'A'])
                {
                    hasLetter[currentSymbol - 'A'] = true;
                    cout << currentSymbol;
                }
            } else {
                if (!hasLetter[currentSymbol - 'a'])
                {
                    hasLetter[currentSymbol - 'a'] = true;
                    cout << currentSymbol;
                }
            }
        } else {
            fillFalse(hasLetter, numberOfLetters);
            if (isNewWord)
            {
                cout << endl;
                isNewWord = false;
            }
        }
    }

    text.close();
    return 0;
}