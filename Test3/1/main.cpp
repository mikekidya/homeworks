#include <iostream>
#include "set.h"
using namespace std;


int main()
{
    Set *set = createSet();

    cout << "Enter a sequence: " << endl;
    int current = -1;
    while (current != 0)
    {
        cin >> current;
        addSet(set, current);
    }

    cout << "Numbers in sequence: " << endl;
    printSet(set);

    deleteSet(set);
    return 0;
}