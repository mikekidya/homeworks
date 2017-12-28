#include <iostream>
#include <fstream>
#include "graph.h"

using namespace std;

Graph *inputGraph(istream &stream)
{
    int vertexNumber = 0;
    stream >> vertexNumber;

    Graph *graph = createGraph(vertexNumber);

    for (int i = 0; i < vertexNumber; i++)
        for (int j = 0; j < vertexNumber; j++)
        {
            int edge = 0;
            stream >> edge;
            if (edge == 1)
                addEdge(graph, i, j);
        }

    return graph;
}

int main()
{
    ifstream inputFile;
    inputFile.open("input.txt");
    if (!inputFile.is_open())
    {
        cout << "File is not found";
        return 0;
    }

    Graph *graph = inputGraph(inputFile);
    inputFile.close();

    cout << "Vertexes in BFS order: " << endl;

    printBFS(graph, 0, cout);

    deleteGraph(graph);
    return 0;
}