#include <iostream>
#include "set.h"
using namespace std;

void printInstruction()
{
    cout << "SET" << endl;
    cout << "0 - Exit" << endl;
    cout << "1 - Add" << endl;
    cout << "2 - Pop" << endl;
    cout << "3 - Check" << endl;
    cout << "4 - Print" << endl;
}

int main()
{
    Set *set = createSet();

    printInstruction();
    int choice = -1;
    cin >> choice;

    while (choice != 0)
    {
        int value = 0;
        switch (choice)
        {
            case 1:
                cout << "Enter number to add: ";
                cin >> value;
                add(set, value);
                break;

            case 2:
                cout << "Enter number to pop: ";
                cin >> value;
                pop(set, value);
                break;

            case 3:
                cout << "Enter number to check: ";
                cin >> value;
                cout << value << " is" << (isContained(set, value) ? "" : " not") << " in set" << endl;
                break;

            case 4:
                cout << " 1 - Increasing Order \n 2 - Decreasing order \n 3 - Tree form" << endl;
                cin >> value;
                if (value == 1)
                    print(set);
                else if (value == 2)
                    printReverse(set);
                else if (value == 3)
                    printTree(set);
                else
                    cout << "Unknown command";
                cout << endl;
                break;

            default:
                cout << "Unknown command" << endl;
        }
        cin >> choice;
    }

    deleteSet(set);
    return 0;
}