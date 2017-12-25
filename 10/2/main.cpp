#include <iostream>
#include "string.h"

using namespace std;

int const mod = 10007;
int const prime = 37;

int stringHash(String *string)
{
    int result = 0;
    for (int i = 0; i < stringLength(string); i++)
        result = ((result * prime) % mod + (int) getChar(string, i)) % mod;

    return result;
}

int stringFind(String *string, String *wanted, int start)
{
    if (start + stringLength(wanted) > stringLength(string))
        return -1;

    int subHash = stringHash(wanted);
    String *firstSlice = subString(string, start, start + stringLength(wanted) - 1);
    int currentHash = stringHash(firstSlice);
    deleteString(firstSlice);

    int power = 1;
    for (int i = 1; i < stringLength(wanted); i++)
        power = (power * prime) % mod;

    for (int i = start; i <= stringLength(string) - stringLength(wanted); i++)
    {
        if (currentHash == subHash)
        {
            String *slice = subString(string, i, i + stringLength(wanted) - 1);
            if (isEqual(wanted, slice))
            {
                deleteString(slice);
                return i;
            }
            deleteString(slice);
        }

        if (i + stringLength(wanted) < stringLength(string))
            currentHash = ((((currentHash - ((int) getChar(string, i)) * power + mod * mod) % mod) * prime) % mod
                           + getChar(string, i + stringLength(wanted))) % mod;

    }

    return -1;
}

int main()
{
    cout << "Enter a sting" << endl;
    String *string = inputString(cin);

    cout << "Enter a substring to search for" << endl;
    String *wanted = inputString(cin);

    int currentIndex = stringFind(string, wanted, 0);
    while ((currentIndex != -1) && (currentIndex <= stringLength(string) - stringLength(wanted)))
    {
        cout << "Occurrence index: " << currentIndex << endl;
        currentIndex = stringFind(string, wanted, currentIndex + 1);
    }
    cout << "There are no more occurrences" << endl;

    deleteString(string);
    deleteString(wanted);
}