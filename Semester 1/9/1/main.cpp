#include <iostream>
#include <fstream>
#include "graph.h"

using namespace std;

Graph *inputGraph(istream &stream)
{
    int numberOfVertex;
    int numberOfEdges;
    stream >> numberOfVertex >> numberOfEdges;
    Graph *graph = createGraph(numberOfVertex);
    for (int i = 0; i < numberOfEdges; i++)
    {
        int firstVertex;
        int secondVertex;
        int distance;
        stream >> firstVertex >> secondVertex >> distance;
        addEdge(graph, firstVertex - 1, secondVertex - 1, distance);
        addEdge(graph, secondVertex - 1, firstVertex - 1, distance);
    }
    return graph;
}

void printPath(int *previous, int vertex)
{
    while (previous[vertex] != -1)
    {
        cout << " <- " << previous[vertex] + 1;
        vertex = previous[vertex];
    }
}

void dijkstra(Graph *graph, int start)
{
    int const inf = 1000000000;

    int *result = new int[numberOfVertex(graph)];
    int *previous = new int[numberOfVertex(graph)];
    bool *visited = new bool[numberOfVertex(graph)];

    for (int i = 0; i < numberOfVertex(graph); i++)
    {
        result[i] = inf;
        previous[i] = -1;
        visited[i] = false;
    }

    cout << "Capture order: ";
    result[start] = 0;
    int current = 0;
    while (!visited[current])
    {
        visited[current] = true;
        cout << current + 1 << " ";
        for (int i = 0; i < numberOfVertex(graph); i++)
            if (!visited[i] && isRelated(graph, current, i) && result[current] + distance(graph, current, i) < result[i])
            {
                previous[i] = current;
                result[i] = result[current] + distance(graph, current, i);
            }

        int minDist = inf;
        for (int i = 0; i < numberOfVertex(graph); i++)
            if (!visited[i] && result[i] < minDist)
            {
                minDist = result[i];
                current = i;
            }
    }

    cout << endl << "Paths: " << endl;
    for (int i = 0; i < numberOfVertex(graph); i++)
        if (visited[i])
        {
            cout << i + 1;
            printPath(previous, i);
            cout << " (distance: " << result[i] << ")" << endl;
        }

    delete[] result;
    delete[] previous;
    delete[] visited;
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

    dijkstra(graph, 0);
    deleteGraph(graph);
    return 0;
}