#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    ifstream fin;
    fin.open("input.txt");

    char currentSymbol = '\0';
    fin.get(currentSymbol);

    int notEmptyStrings = 0;
    bool isEmpty = true;
    while (!fin.eof())
    {
        if (currentSymbol == '\n')
        {
            if (!isEmpty)
                notEmptyStrings++;
            isEmpty = true;
        } else {
            if (currentSymbol != ' ' && currentSymbol != '\t')
                isEmpty = false;
        }
        fin.get(currentSymbol);
    }
    if (!isEmpty)
        notEmptyStrings++;
    fin.close();

    cout << "Number of not empty strings is " <<  notEmptyStrings;
    return 0;
}