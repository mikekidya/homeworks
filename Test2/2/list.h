#pragma once

struct List;

List *createList();
void deleteList(List *list);

void addList(List *list, int value);
int popHeadList(List *list);

bool isEmpty(List *list);
bool isSymmetrical(List *list);