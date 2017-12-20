#include <iostream>
#include <fstream>
#include "list.h"

using namespace std;


int main()
{
    ifstream inputFile;
    inputFile.open("input.txt");

    if (!inputFile.is_open())
    {
        cout << "File is not found";
        return 0;
    }

    List *list = createList();
    while (!inputFile.eof())
    {
        int current;
        inputFile >> current;
        inputFile.ignore(256, ' ');
        addList(list, current);
    }
    inputFile.close();

    if (isSymmetrical(list))
        cout << "List is symmetrical" << endl;
    else
        cout << "List is not symmetrical" << endl;

    deleteList(list);
    return 0;
}