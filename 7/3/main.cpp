#include <iostream>
#include <fstream>
#include "expressionTree.h"

using namespace std;

int main()
{
    ExpressionTree *expression = createTree();

    ifstream inputFile;
    inputFile.open("input.txt");

    inputTree(expression, inputFile);
    cout << "Infix form: ";
    printInfixForm(expression);
    cout << "Result: " << calculate(expression) << endl;

    deleteTree(expression);
    inputFile.close();

    return 0;
}