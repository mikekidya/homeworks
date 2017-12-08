#include "members.h"

#include <iostream>

struct Member
{
    char *name;
    int danger;
};

Member *createMember(char *name, int danger)
{
    Member *newMember = new Member;
    newMember->name = name;
    newMember->danger = danger;
    return newMember;
}

void deleteMember(Member *member)
{
    delete[] member->name;
    delete member;
}

bool hasBiggerDanger(Member *member, Member *other)
{
    return member->danger > other->danger;
}

bool hasBiggerName(Member *member, Member *other)
{
    int index = 0;
    while (member->name[index] != '\0' && other->name[index] != '\0' && member->name[index] == other->name[index])
    {
        index++;
    }
    return other->name[index] == '\0' || other->name[index] < member->name[index];
}

void swap(Member *&a, Member *&b)
{
    Member *t = a;
    a = b;
    b = t;
}

void sortByDanger(Member **array, int left, int right)
{
    if (right <= left)
        return;

    Member *p = array[(left + right) / 2];
    int i = left;
    int j = right;
    while (i <= j)
    {
        while (hasBiggerDanger(p, array[i]))
            i++;

        while (hasBiggerDanger(array[j], p))
            j--;

        if (i <= j)
        {
            swap(array[i], array[j]);
            i++;
            j--;
        }
    }
    sortByDanger(array, left, j);
    sortByDanger(array, i, right);
}

void sortByName(Member **array, int left, int right)
{
    if (right <= left)
        return;

    Member *p = array[(left + right) / 2];
    int i = left;
    int j = right;
    while (i <= j)
    {
        while (p != array[i] && hasBiggerName(p, array[i]))
            i++;

        while (p != array[j] && hasBiggerName(array[j], p))
            j--;

        if (i <= j)
        {
            swap(array[i], array[j]);
            i++;
            j--;
        }
    }
    sortByName(array, left, j);
    sortByName(array, i, right);
}

void printMember(Member *member)
{
    std::cout << member->name << std::endl;
}