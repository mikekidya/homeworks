#include <iostream>
#include <fstream>

#include "members.h"
#include "list.h"


using namespace std;


char *inputName(ifstream &file)
{
    int const maxLength = 255;
    int length = 0;

    char *buffer = new char[maxLength];
    char symbol = file.get();

    if (symbol == '\n')
        symbol = file.get();

    while (symbol != ' ')
    {
        buffer[length] = symbol;
        length++;
        symbol = file.get();
    }

    char *name = new char[length + 1];
    for (int i = 0; i < length; i++)
        name[i] = buffer[i];
    name[length] = '\0';

    delete[] buffer;

    return name;
}

int main()
{
    ifstream file;
    file.open("input.txt");

    if (!file.is_open())
    {
        cout << "File is not found" << endl;
        return 0;
    }

    int members = 0;
    List *list = createList();
    while (!file.eof())
    {
        char *name = inputName(file);
        file.get(); // extra -
        int danger = 0;
        file >> danger;
        addList(list, createMember(name, danger));
        members++;
    }
    file.close();


    Member **arrayMembers = new Member*[members];
    for (int i = 0; i < members; i++)
    {
        arrayMembers[i] = popList(list);
    }
    deleteList(list);

    int killed = 0;
    cout << "Enter the number of members should be killed: ";
    cin >> killed;

    int moved = 0;
    cout << "Enter the number of members should be moved: ";
    cin >> moved;

    sortByDanger(arrayMembers, 0, members - 1);

    cout << "Will be killed: " << endl;
    for (int i = 0; i < killed; i++)
    {
        printMember(arrayMembers[members - 1 - i]);
    }

    sortByName(arrayMembers, 0, members - 1 - killed);

    cout << "Will be moved: " << endl;
    for (int j = 0; j < moved; j++)
    {
        printMember(arrayMembers[j]);
    }

    for (int i = 0; i < members; i++)
    {
        deleteMember(arrayMembers[i]);
    }

    delete[] arrayMembers;

    return 0;
}