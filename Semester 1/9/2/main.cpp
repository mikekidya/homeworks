#include <fstream>
#include <iostream>
#include "string.h"
#include "huffman.h"

using namespace std;

int main()
{
    ifstream input;
    input.open("input.txt");
    if (!input.is_open())
    {
        cout << "File is not found";
        return 0;
    }

    String *line = inputString(input);
    input.close();

    HuffmanCode *huffmanCode = encode(line);

    ofstream output;
    output.open("output.txt");

    printHuffmanTree(huffmanCode, output);
    output << endl;

    for (int i = 0; i < stringLength(line); i++)
        printString(getCode(huffmanCode, getChar(line, i)), output);
    output << endl;

    deleteHuffmanCode(huffmanCode);
    deleteString(line);

    output.close();
    return 0;
}