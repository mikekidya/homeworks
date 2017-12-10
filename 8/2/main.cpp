#include <iostream>
#include "string.h"

using namespace std;

void printEquality(String *string1, String *string2)
{
    cout << "Words \"";
    printString(string1, cout);
    cout << "\" and \"";
    printString(string2, cout);
    if (isEqual(string1, string2))
        cout << "\" are equal" << endl;
    else
        cout << "\" aren't equal" << endl;
}

int main()
{

    cout << "Enter two words: ";

    String *string1 = inputString(cin, ' ');
    String *string2 = inputString(cin, ' ');

    printEquality(string1, string2);

    printString(string1, cout);
    cout << " + ";
    printString(string2, cout);
    concatenation(string1, string2);
    cout << " = ";
    printString(string1, cout);
    cout << endl;

    String *cloned = clone(string1);
    printEquality(cloned, string1);
    printEquality(cloned, string2);

    String *trunc = subString(cloned, 1, stringLength(cloned) - 1);
    cout << "Deleted first symbol: ";
    printString(trunc, cout);

    deleteString(string1);
    deleteString(string2);
    deleteString(cloned);
    deleteString(trunc);

    return 0;
}
