#include <iostream>
#include "graph.h"
using namespace std;

int main()
{
    int studentsNumber = 0;
    cout << "Enter students number: ";
    cin >> studentsNumber;

    Graph *graph = createGraph(studentsNumber);

    int numberOfPairs = 0;
    cout << "Enter the number of pairs: ";
    cin >> numberOfPairs;

    cout << "Enter the pairs: " << endl;
    for (int i = 0; i < numberOfPairs; i++)
    {
        int firstStudent = 0;
        int secondStudent = 0;
        cin >> firstStudent >> secondStudent;
        addEdge(graph, secondStudent, firstStudent);
    }

    DFS(graph, 1, 1);
    DFS(graph, 2, 2);
    DFS(graph, 3, 3);

    for (int student = 1; student <= studentsNumber; student++)
    {
        if (getColor(graph, student) == 0)
            cout << student << " should be excluded" << endl;
        else
            cout << student << " " << getColor(graph, student) << endl;
    }

    deleteGraph(graph);
    return 0;
}