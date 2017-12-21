#include <iostream>
#include "set.h"
using namespace std;


int main()
{
    Set *set = createSet();

    cout << "Enter a sequence: " << endl;
    int current = 0;
    cin >> current;
    while (current != 0)
    {
        addSet(set, current);
        cin >> current;
    }

    cout << "Numbers in sequence: " << endl;
    printSet(set);

    deleteSet(set);
    return 0;
}