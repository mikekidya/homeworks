#include "set.h"
#include "avl.h"

struct Set
{
    AVLTree *tree;
};

Set *createSet()
{
    Set *set = new Set;
    set->tree = createTree();
    return set;
}

void deleteSet(Set *set)
{
    deleteTree(set->tree);
    delete set;
}

void addSet(Set *set, int value)
{
    add(set->tree, value);
}

void popSet(Set *set, int value)
{
    remove(set->tree, value);
}

bool isContained(Set *set, int value)
{
    return contains(set->tree, value);
}

void printSet(Set *set)
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