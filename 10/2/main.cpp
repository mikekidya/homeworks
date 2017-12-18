#include <iostream>
#include "string.h"

using namespace std;

int main()
{
    cout << "Enter a sting" << endl;
    String *string = inputString(cin);

    cout << "Enter a substring to search for" << endl;
    String *wanted = inputString(cin);

    int currentIndex = find(string, wanted, 0);
    while ((currentIndex != -1) && (currentIndex <= stringLength(string) - stringLength(wanted)))
    {
        cout << "Occurrence index: " << currentIndex << endl;
        currentIndex = find(string, wanted, currentIndex + 1);
    }

    deleteString(string);
    deleteString(wanted);
}