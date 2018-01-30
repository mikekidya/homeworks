#include "graph.h"


struct Graph
{
    int size;
    int *vertexColor;
    int **relative;
};

Graph *createGraph(int vertexNumber)
{
    Graph *graph = new Graph;
    graph->size = vertexNumber + 1;

    graph->vertexColor = new int[graph->size];
    for (int i = 0; i < graph->size; i++)
        graph->vertexColor[i] = 0;

    graph->relative = new int*[graph->size];
    for (int i = 0; i < graph->size; i++)
    {
        graph->relative[i] = new int[graph->size];
        for (int j = 0; j < graph->size; j++)
            graph->relative[i][j] = 0;
    }

    return graph;
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->size; i++)
        delete[] graph->relative[i];

    delete[] graph->relative;
    delete[] graph->vertexColor;
    delete graph;
}

void addEdge(Graph *graph, int firstVertex, int secondVertex, int dist)
{
    graph->relative[firstVertex][secondVertex] = dist;
}

void deleteEdge(Graph *graph, int firstVertex, int secondVertex)
{
    graph->relative[firstVertex][secondVertex] = 0;
}

int distance(Graph *graph, int firstVertex, int secondVertex)
{
    return graph->relative[firstVertex][secondVertex];
}

bool isRelated(Graph *graph, int firstVertex, int secondVertex)
{
    return distance(graph, firstVertex, secondVertex) != 0;
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

int numberOfVertex(Graph *graph)
{
    return graph->size;
}
