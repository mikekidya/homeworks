#include "list.h"

struct ListElement
{
    Member *member;
    ListElement *next;
};

struct List
{
    ListElement *head;
};

List *createList()
{
    List *newList = new List;
    newList->head = nullptr;
    return newList;
}

bool isEmpty(List *list)
{
    return list->head == nullptr;
}

void deleteList(List *list)
{
    while (!isEmpty(list))
    {
        popList(list);
    }
}

void addList(List *list, Member *member)
{
    ListElement *newElement = new ListElement;
    newElement->member = member;
    newElement->next = list->head;
    list->head = newElement;
}

Member *popList(List *list)
{
    ListElement *currentElement = list->head;
    Member *currentMember = currentElement->member;
    list->head = currentElement->next;
    delete currentElement;
    return currentMember;
}

