#include <iostream>
#include "avl.h"

struct AVLNode
{
    int value;
    int height;
    AVLNode *left;
    AVLNode *right;
};

AVLTree *createTree()
{
    AVLTree *tree = new AVLTree;
    tree->root = nullptr;
    return tree;
}

void printLeftNode(AVLNode *node)
{
    if (node == nullptr)
        return;

    printLeftNode(node->left);
    std::cout << node->value << " ";
    printLeftNode(node->right);
}

void printLeft(AVLTree *tree)
{
    printLeftNode(tree->root);
}

void printTreeNode(AVLNode *node)
{
    if (node == nullptr)
    {
        std::cout << " null";
        return;
    }

    std::cout << "(" << node->value << " ";
    printTreeNode(node->left);
    printTreeNode(node->right);
    std::cout << ")";
}

void printTree(AVLTree *tree)
{
    printTreeNode(tree->root);
}

void printRightNode(AVLNode *node)
{
    if (node == nullptr)
        return;

    printRightNode(node->right);
    std::cout << node->value << " ";
    printRightNode(node->left);
}

void printRight(AVLTree *tree)
{
    printRightNode(tree->root);
}

int height(AVLNode *node)
{
    return node ? node->height : 0;
}

int balanceFactor(AVLNode *node)
{
    return height(node->right) - height(node->left);
}

void updateHeight(AVLNode *node)
{
    int heightLeft = height(node->left);
    int heightRight = height(node->right);
    node->height = ((heightLeft > heightRight) ? heightLeft : heightRight) + 1;
}

void rotateRight(AVLNode *&root)
{
    AVLNode* pivot = root->left;
    root->left = pivot->right;
    pivot->right = root;
    updateHeight(root);
    updateHeight(pivot);
    root = pivot;
}

void *rotateLeft(AVLNode *&root)
{
    AVLNode* pivot = root->right;
    root->right = pivot->left;
    pivot->left = root;
    updateHeight(root);
    updateHeight(pivot);
    root = pivot;
}

void balance(AVLNode *&node)
{
    updateHeight(node);

    if (balanceFactor(node) == 2)
    {
        if (balanceFactor(node->right) < 0)
            rotateRight(node->right);

        rotateLeft(node);
    }

    if (balanceFactor(node) == -2)
    {
        if (balanceFactor(node->left) > 0)
            rotateLeft(node->left);

        rotateRight(node);
    }

}


void clearNode(AVLNode *node)
{
    if (node == nullptr)
        return;

    clearNode(node->left);
    clearNode(node->right);
    delete node;
}

void clearTree(AVLTree *tree)
{
    clearNode(tree->root);
    tree->root = nullptr;
}

void deleteTree(AVLTree *tree)
{
    clearTree(tree);
    delete tree;
}

bool containsNode(AVLNode *node, int value)
{
    if (node == nullptr)
        return false;

    if (node->value == value)
        return true;

    if (value < node->value)
        return containsNode(node->left, value);
    else
        return containsNode(node->right, value);
}

bool contains(AVLTree *tree, int value)
{
    return containsNode(tree->root, value);
}

AVLNode *createNode(int value)
{
    AVLNode *node = new AVLNode;
    node->value = value;
    node->left = nullptr;
    node->right = nullptr;
    updateHeight(node);
    return node;
}

void addNode(AVLNode *&node, int value)
{
    if (node == nullptr)
        node = createNode(value);



    if (node->value == value)
        return;

    if (value < node->value)
        addNode(node->left, value);
    else
        addNode(node->right, value);

    balance(node);

}

void add(AVLTree *tree, int value)
{
    return addNode(tree->root, value);
}

AVLNode *findMax(AVLNode *node)
{
    while (node->right != nullptr)
        node = node->right;
    return node;
}

void removeNode(AVLNode *&node, int value)
{
    if (node == nullptr)
        return;

    if (node->value == value)
    {
        if (node->left == nullptr && node->right == nullptr)
        {
            delete node;
            node = nullptr;
        }
        else if (node->left == nullptr)
        {
            AVLNode *temp = node->right;
            delete node;
            node = temp;
        }
        else if (node->right == nullptr)
        {
            AVLNode *temp = node->left;
            delete node;
            node = temp;
        }
        else
        {
            AVLNode *maxNode = findMax(node->left);
            int maxValue = maxNode->value;
            removeNode(node, maxValue);
            node->value = maxValue;
            balance(node);
        }
        return;
    }

    if (value < node->value)
        removeNode(node->left, value);
    else
        removeNode(node->right, value);
    balance(node);
}

void remove(AVLTree *tree, int value)
{
    removeNode(tree->root, value);
}


