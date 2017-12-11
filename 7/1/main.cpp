#include <iostream>
#include "set.h"
using namespace std;

void printInstruction()
{
    cout << "SET" << endl;
    cout << "Supported commands: ";
    cout << "exit" << endl;
    cout << "add" << endl;
    cout << "pop" << endl;
    cout << "check" << endl;
    cout << "print" << endl;
}

int main()
{
    Set *set = createSet();

    printInstruction();

    enum choice {exit = 0, add = 1, pop = 2, check = 3, print = 4};

    choice ch;
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