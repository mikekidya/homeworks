#pragma once

#include "members.h"

struct List;

List *createList();
void deleteList(List *list);

void addList(List *list, Member *member);
Member *popList(List *list);

bool isEmpty(List *list);