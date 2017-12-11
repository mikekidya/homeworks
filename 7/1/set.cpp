#include "set.h"
#include "bst.h"

struct Set
{
    BinarySearchTree *tree;
};

Set *createSet()
{
    Set *set = new Set;
    set->tree = createTree();
    return set;
}

void deleteSet(Set *set)
{
    clearTree(set->tree);
    delete set->tree;
    delete set;
}

void add(Set *set, int value)
{
    add(set->tree, value);
}

void pop(Set *set, int value)
{
    remove(set->tree, value);
}

bool isContained(Set *set, int value)
{
    return contains(set->tree, value);
}

void print(Set *set)
{
    printLeft(set->tree);
}

void printReverse(Set *set)
{
    printRight(set->tree);
}

void printTree(Set *set)
{
    printTree(set->tree);
}