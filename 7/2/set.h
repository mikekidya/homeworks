#pragma once

struct Set;

Set *createSet();
void deleteSet(Set *set);

void addSet(Set *set, int value);
void popSet(Set *set, int value);
bool isContained(Set *set, int value);

void printSet(Set *set);
void printReverse(Set *set);
void printTree(Set *set);

