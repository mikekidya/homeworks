#pragma once

struct Graph;

Graph *createGraph(int vertexNumber);
void deleteGraph(Graph *graph);

void addEdge(Graph *graph, int firstVertex, int secondVertex);
void deleteEdge(Graph *graph, int firstVertex, int secondVertex);
bool isRelated(Graph *graph, int firstVertex, int secondVertex);

// default preset color is 0
void setVertexColor(Graph *graph, int vertex, int color);
int getColor(Graph *graph, int vertex);

void DFS(Graph *graph, int start, int color);