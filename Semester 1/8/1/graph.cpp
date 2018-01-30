#include "graph.h"


struct Graph
{
    int size;
    int *vertexColor;
    bool **isRelative;
};

Graph *createGraph(int vertexNumber)
{
    Graph *graph = new Graph;
    graph->size = vertexNumber + 1;

    graph->vertexColor = new int[graph->size];
    for (int i = 0; i < graph->size; i++)
        graph->vertexColor[i] = 0;

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
    delete[] graph->vertexColor;
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

void setVertexColor(Graph *graph, int vertex, int color)
{
    graph->vertexColor[vertex] = color;
}

int getColor(Graph *graph, int vertex)
{
    return graph->vertexColor[vertex];
}

void DFS(Graph *graph, int start, int color)
{
    graph->vertexColor[start] = color;
    for (int vertex = 0; vertex < graph->size; vertex++)
        if (isRelated(graph, start, vertex) && getColor(graph, vertex) != color)
            DFS(graph, vertex, color);
}