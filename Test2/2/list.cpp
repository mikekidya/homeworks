#include "list.h"

struct ListElement
{
    int value;
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
        popHeadList(list);
    }
    delete list;
}

void addList(List *list, int value)
{
    ListElement *newElement = new ListElement;
    newElement->value = value;
    newElement->next = list->head;
    list->head = newElement;
}

int popHeadList(List *list)
{
    ListElement *currentElement = list->head;
    list->head = currentElement->next;
    int value = currentElement->value;
    delete currentElement;
    return value;
}

bool isSymmetricalElement(ListElement *element)
{
    if (element == nullptr || element->next == nullptr)
        return true;

    ListElement *lastElement = element;
    while (lastElement->next->next != nullptr)
        lastElement = lastElement->next;

    ListElement *tmp = lastElement->next;
    lastElement->next = nullptr;
    bool result = isSymmetricalElement(element->next) && (element->value == tmp->value);
    lastElement->next = tmp;
    return result;
}

bool isSymmetrical(List *list)
{
    return isSymmetricalElement(list->head);
}