#pragma once

#include "string.h"


struct HuffmanTree;

struct HuffmanCode
{
    HuffmanTree *tree;
    String **codes;
};

int frequency(HuffmanTree *huffmanTree);
void deleteHuffmanTree(HuffmanTree *huffmanTree);
void deleteHuffmanCode(HuffmanCode *huffmanCode);
void printHuffmanTree(HuffmanCode *huffmanCode, std::ostream &stream);

HuffmanCode *encode(String *string);
void decode(std::istream &input, std::ostream &output);
String *getCode(HuffmanCode *huffmanCode, char symbol);
