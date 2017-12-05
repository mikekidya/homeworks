#include <iostream>
#include <fstream>
#include "queue.h"

using namespace std;

void fileToGroups(int a, int b, Queue *lessThanA, Queue *betweenAB, Queue *biggerThanB, ifstream &file)
{
    while (!file.eof())
    {
        int current = 0;
        file >> current;
        if (current < a)
            append(lessThanA, current);
        if (current >= a && current <= b)
            append(betweenAB, current);
        if (current > b)
            append(biggerThanB, current);
    }
}

void printGroup(Queue *group, ofstream &file)
{
    while (!isEmpty(group))
        file << pop(group) << " ";
    file << endl;
}

int main()
{
    Queue *lessThanA = createQueue();
    Queue *betweenAB = createQueue();
    Queue *biggerThanB = createQueue();

    int a = 0;
    int b = 0;
    cout << "Enter a and b: ";
    cin >> a >> b;
    ifstream inputFile("f");
    fileToGroups(a, b, lessThanA, betweenAB, biggerThanB, inputFile);
    inputFile.close();

    ofstream outputFile("g");
    printGroup(lessThanA, outputFile);
    printGroup(betweenAB, outputFile);
    printGroup(biggerThanB, outputFile);
    outputFile.close();

    deleteQueue(lessThanA);
    deleteQueue(betweenAB);
    deleteQueue(biggerThanB);
    return 0;
}