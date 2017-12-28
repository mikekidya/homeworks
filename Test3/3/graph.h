#pragma once

#include <ostream>

struct Graph;

Graph *createGraph(int vertexNumber);
void deleteGraph(Graph *graph);

void addEdge(Graph *graph, int firstVertex, int secondVertex);
void deleteEdge(Graph *graph, int firstVertex, int secondVertex);
bool isRelated(Graph *graph, int firstVertex, int secondVertex);

void printBFS(Graph *graph, int start, std::ostream &stream);