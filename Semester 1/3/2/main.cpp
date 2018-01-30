#include <iostream>
#include "sort.h"

using namespace std;

void inputString(char* &string, int &length)
{
    cout << "Enter strings' length: ";
    cin >> length;
    cout << "Enter the string: " << endl;
    string = new char[length];
    for (int i = 0; i < length; i++)
    {
        cin >> string[i];
    }

}

int main()
{
    char *firstString = nullptr;
    int firstStringLength = 0;
    inputString(firstString, firstStringLength);

    char *secondString = nullptr;
    int secondStringLength = 0;
    inputString(secondString, secondStringLength);

    if (firstStringLength == secondStringLength)
    {
        stringSort(firstString, firstStringLength);
        stringSort(secondString, secondStringLength);
        int i = 0;
        while ((i < firstStringLength) && (firstString[i] == secondString[i]))
            i++;

        if (i == firstStringLength)
            cout << "You can get the first string from the second one using transpositions" << endl;
        else
            cout << "You cannot get the first string from the second one using only transpositions" << endl;
    }
    else
    {
        cout << "You cannot get the first string from the second one using only transpositions" << endl;
    }

    delete[] firstString;
    delete[] secondString;

    return 0;
}