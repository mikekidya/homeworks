#include "graph.h"

#include "queue.h"

struct Graph
{
    int size;
    bool **isRelative;
};

Graph *createGraph(int vertexNumber)
{
    Graph *graph = new Graph;
    graph->size = vertexNumber + 1;

    graph->isRelative = new bool*[graph->size];
    for (int i = 0; i < graph->size; i++)
    {
        graph->isRelative[i] = new bool[graph->size];
        for (int j = 0; j < graph->size; j++)
            graph->isRelative[i][j] = false;
    }

    return graph;
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->size; i++)
        delete[] graph->isRelative[i];

    delete[] graph->isRelative;
    delete graph;
}

void addEdge(Graph *graph, int firstVertex, int secondVertex)
{
    graph->isRelative[firstVertex][secondVertex] = true;
}

void deleteEdge(Graph *graph, int firstVertex, int secondVertex)
{
    graph->isRelative[firstVertex][secondVertex] = false;
}

bool isRelated(Graph *graph, int firstVertex, int secondVertex)
{
    return graph->isRelative[firstVertex][secondVertex];
}

void printBFS(Graph *graph, int start, std::ostream &stream)
{
    Queue *queue = createQueue();

    bool *isVisited = new bool[graph->size];
    for (int i = 0; i < graph->size; i++)
        isVisited[i] = false;

    append(queue, start);
    isVisited[start] = true;
    while (!isEmpty(queue))
    {
        int currentVertex = pop(queue);
        stream << currentVertex + 1 << " ";

        for (int nextVertex = 0; nextVertex < graph->size; nextVertex++)
            if (!isVisited[nextVertex] && isRelated(graph, currentVertex, nextVertex))
            {
                append(queue, nextVertex);
                isVisited[nextVertex] = true;
            }
    }

    deleteQueue(queue);
    delete[] isVisited;
}