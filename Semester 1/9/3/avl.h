#pragma once

#include "huffman.h"

struct AVLTree;

AVLTree *createTree();
void deleteTree(AVLTree *tree);

bool contains(AVLTree *tree, HuffmanTree *value);
void add(AVLTree *tree, HuffmanTree *value);
void remove(AVLTree *tree, HuffmanTree *value);
HuffmanTree *getMin(AVLTree *tree);

bool isEmpty(AVLTree *tree);
bool hasOneElement(AVLTree *tree);

void printLeft(AVLTree *tree);
void printTree(AVLTree *tree);
void printRight(AVLTree *tree);