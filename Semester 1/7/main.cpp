#include <iostream>
#include "set.h"
using namespace std;

void printInstruction()
{
    cout << "SET" << endl;
    cout << "Supported commands: " << endl;
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

    enum choices {exit, add, pop, check, print};
    enum print {increasingOrder = 1, decreasingOrder = 2, treeForm = 3};

    int choice;
    cin >> choice;

    while (choice != 0)
    {
        int value = 0;
        switch (choice)
        {
            case add:
                cout << "Enter number to add: ";
                cin >> value;
                addSet(set, value);
                break;

            case pop:
                cout << "Enter number to pop: ";
                cin >> value;
                popSet(set, value);
                break;

            case check:
                cout << "Enter number to check: ";
                cin >> value;
                cout << value << " is" << (isContained(set, value) ? "" : " not") << " in set" << endl;
                break;

            case print:
                cout << " 1 - Increasing Order \n 2 - Decreasing order \n 3 - Tree form" << endl;
                cin >> value;
                switch (value)
                {
                    case increasingOrder:
                        printSet(set);
                        break;
                    case decreasingOrder:
                        printReverse(set);
                        break;
                    case treeForm:
                        printTree(set);
                        break;
                    default:
                        cout << "Unknown command";
                        break;
                }
                break;

            default:
                cout << "Unknown command" << endl;
                break;
        }
        cin >> choice;
    }

    deleteSet(set);
    return 0;
}