#pragma once

struct Graph;

Graph *createGraph(int vertexNumber);
void deleteGraph(Graph *graph);

void addEdge(Graph *graph, int firstVertex, int secondVertex, int dist);
void deleteEdge(Graph *graph, int firstVertex, int secondVertex);
bool isRelated(Graph *graph, int firstVertex, int secondVertex);
int distance(Graph *graph, int firstVertex, int secondVertex);
int numberOfVertex(Graph *graph);

// default preset color is 0
void setVertexColor(Graph *graph, int vertex, int color);
int getColor(Graph *graph, int vertex);

void DFS(Graph *graph, int start, int color);
