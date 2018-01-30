#include <iostream>
#include <fstream>
#include "expressionTree.h"

using namespace std;


struct Node
{
    char operation;
    int value; // if leaf
    bool isLeaf;
    Node *leftNode;
    Node *rightNode;
};

struct ExpressionTree
{
    Node *head;
};

ExpressionTree *createTree()
{
    ExpressionTree *newTree = new ExpressionTree;
    newTree->head = nullptr;
    return newTree;
}

void deleteNode(Node *node)
{
    if (node == nullptr)
        return;

    deleteNode(node->leftNode);
    deleteNode(node->rightNode);
    delete node;
}

void deleteTree(ExpressionTree *tree)
{
    deleteNode(tree->head);
    delete tree;
}

int calculateNode(Node *node)
{
    if (node->isLeaf)
        return node->value;

    switch (node->operation)
    {
        case '+':
            return calculateNode(node->leftNode) + calculateNode(node->rightNode);

        case '-':
            return calculateNode(node->leftNode) + calculateNode(node->rightNode);

        case '*':
            return calculateNode(node->leftNode) * calculateNode(node->rightNode);

        case '/':
            return calculateNode(node->leftNode) / calculateNode(node->rightNode);

        default:
            cout << "Wrong expression" << endl;
            return 0;
    }
}

int calculate(ExpressionTree *tree)
{
    return calculateNode(tree->head);
}

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

int toInt(char symbol)
{
    return symbol - '0';
}

Node *inputNode(ifstream &file)
{
    char currentSymbol = file.get();

    Node *newNode = new Node;

    if (currentSymbol == '(')
    {
        currentSymbol = file.get();
        if (currentSymbol == ' ')
        {
            currentSymbol = '*';
        }
        else
        {
            file.get(); // extra space
        }
        newNode->isLeaf = false;
        newNode->operation = currentSymbol;
        newNode->leftNode = inputNode(file);
        file.get(); // extra space
        newNode->rightNode = inputNode(file);
        file.get(); // extra ")"
    }
    else
    {
        int currentValue = toInt(currentSymbol);
        currentSymbol = file.get();
        while (isDigit(currentSymbol))
        {
            currentValue = currentValue * 10 + toInt(currentSymbol);
            currentSymbol = file.get();
        }
        file.unget();
        newNode->isLeaf = true;
        newNode->leftNode = nullptr;
        newNode->rightNode = nullptr;
        newNode->value = currentValue;
    }

    return newNode;
}

void inputTree(ExpressionTree *tree, ifstream &file)
{
    tree->head = inputNode(file);
}

void printInfixNode(Node *node)
{
    if (node->isLeaf)
    {
        cout << node->value;
        return;
    }

    cout << "(";
    printInfixNode(node->leftNode);
    cout << " " << node->operation << " ";
    printInfixNode(node->rightNode);
    cout << ")";
}

void printInfixForm(ExpressionTree *tree)
{
    printInfixNode(tree->head);
    cout << endl;
}