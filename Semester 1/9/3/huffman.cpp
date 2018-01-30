#include "huffman.h"

#include "string.h"
#include "avl.h"

int const maxSymbolCode = 256;

struct HuffmanTree
{
    HuffmanTree *left;
    HuffmanTree *right;
    bool isLeaf;
    char value; // if leaf
    int frequency;
};

HuffmanTree *createLeaf(char symbol, int frequency)
{
    HuffmanTree *newLeaf = new HuffmanTree;
    newLeaf->value = symbol;
    newLeaf->frequency = frequency;
    newLeaf->left = nullptr;
    newLeaf->right = nullptr;
    newLeaf->isLeaf = true;
    return newLeaf;
}

HuffmanTree *mergeHuffmanTree(HuffmanTree *firstTree, HuffmanTree *secondTree)
{
    HuffmanTree *newTree = new HuffmanTree;
    newTree->left = firstTree;
    newTree->right = secondTree;
    newTree->isLeaf = false;
    newTree->frequency = firstTree->frequency + secondTree->frequency;
    return newTree;
}

void deleteHuffmanTree(HuffmanTree *huffmanTree)
{
    if (huffmanTree == nullptr)
        return;

    deleteHuffmanTree(huffmanTree->left);
    deleteHuffmanTree(huffmanTree->right);
    delete huffmanTree;
}

void getCodes(HuffmanTree *huffmanTree, String **codes, String *currentCode)
{
    if (huffmanTree->isLeaf)
    {
        if (stringLength(currentCode) == 0)
        {
            char leftSuffixLine[2] = {'0', '\0'};
            String *leftSuffix = createString(leftSuffixLine);
            concatenation(currentCode, leftSuffix);
            deleteString(leftSuffix);
        }
        codes[(int) huffmanTree->value] = currentCode;
        return;
    }

    String *leftCode = currentCode;
    String *rightCode = clone(currentCode);

    char leftSuffixLine[2] = {'0', '\0'};
    char rightSuffixLine[2] = {'1', '\0'};
    String *leftSuffix = createString(leftSuffixLine);
    String *rightSuffix = createString(rightSuffixLine);
    concatenation(leftCode, leftSuffix);
    concatenation(rightCode, rightSuffix);
    deleteString(leftSuffix);
    deleteString(rightSuffix);

    getCodes(huffmanTree->left, codes, leftCode);
    getCodes(huffmanTree->right, codes, rightCode);
}

String **codes(HuffmanTree *huffmanTree)
{
    String **codes = new String*[maxSymbolCode];
    for (int i = 0; i < maxSymbolCode; i++)
        codes[i] = nullptr;

    char emptyLine[1] = {'\0'};
    getCodes(huffmanTree, codes, createString(emptyLine));
    return codes;
}

HuffmanCode *encode(String *string)
{
    int frequencies[maxSymbolCode];
    for (int i = 0; i < maxSymbolCode; i++)
        frequencies[i] = 0;

    for (int i = 0; i < stringLength(string); i++)
        frequencies[(int) getChar(string, i)]++;

    AVLTree *priorityQueue = createTree();
    for (int i = 0; i < maxSymbolCode; i++)
    {
        if (frequencies[i] > 0)
        {
            HuffmanTree *newLeaf = createLeaf((char) i, frequencies[i]);
            add(priorityQueue, newLeaf);
        }
    }

    while (!hasOneElement(priorityQueue))
    {
        HuffmanTree *firstTree = getMin(priorityQueue);
        HuffmanTree *secondTree = getMin(priorityQueue);
        HuffmanTree *merged = mergeHuffmanTree(firstTree, secondTree);
        add(priorityQueue, merged);
    }

    HuffmanTree *huffmanTree = getMin(priorityQueue);
    deleteTree(priorityQueue);

    String **code = codes(huffmanTree);

    HuffmanCode *huffmanCode = new HuffmanCode;
    huffmanCode->codes = code;
    huffmanCode->tree = huffmanTree;

    return huffmanCode;
}

String *getCode(HuffmanCode *huffmanCode, char symbol)
{
    return huffmanCode->codes[symbol];
}

int frequency(HuffmanTree *huffmanTree)
{
    return huffmanTree->frequency;
}

void deleteHuffmanCode(HuffmanCode *huffmanCode)
{
    deleteHuffmanTree(huffmanCode->tree);
    for (int i = 0; i < maxSymbolCode; i++)
    {
        if (huffmanCode->codes[i] != nullptr)
            deleteString(huffmanCode->codes[i]);
    }
    delete[] huffmanCode->codes;
    delete huffmanCode;
}

void printTree(HuffmanCode *huffmanCode, HuffmanTree *huffmanTree, std::ostream &stream)
{
    if (huffmanTree->isLeaf)
    {
        stream << "{" << huffmanTree->value << "}";
        return;
    }
    stream << "(";
    printTree(huffmanCode, huffmanTree->left, stream);
    printTree(huffmanCode, huffmanTree->right, stream);
    stream << ")";
}

void printHuffmanTree(HuffmanCode *huffmanCode, std::ostream &stream)
{
    printTree(huffmanCode, huffmanCode->tree, stream);
}

HuffmanTree *createHuffmanTree(std::istream &stream)
{
    char currentSymbol;
    currentSymbol = stream.get();
    if (currentSymbol == '{')
    {
        currentSymbol = stream.get();
        HuffmanTree *newLeaf = createLeaf(currentSymbol, -1);
        return newLeaf;
    }

    HuffmanTree *leftTree = createHuffmanTree(stream);
    stream.get(); // extra ) or }
    HuffmanTree *rightTree = createHuffmanTree(stream);
    stream.get(); // extra ) or }
    return mergeHuffmanTree(leftTree, rightTree);
}

char decodeSymbol(std::istream &stream, HuffmanTree *huffmanTree)
{
    if (huffmanTree->isLeaf)
        stream.get();



    while (!huffmanTree->isLeaf && !stream.eof())
    {
        char symbol;
        stream >> symbol;
        if (symbol == '0')
            huffmanTree = huffmanTree->left;
        else
            huffmanTree = huffmanTree->right;
    }

    if (huffmanTree->isLeaf)
        return huffmanTree->value;

    return '\n';
}

void decode(std::istream &input, std::ostream &output)
{
    HuffmanTree *tree = createHuffmanTree(input);
    input.get(); // extra '\n'
    while (!input.eof())
        output << decodeSymbol(input, tree);

    deleteHuffmanTree(tree);
}