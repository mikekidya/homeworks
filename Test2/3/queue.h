#pragma once

struct Queue;

Queue *createQueue();
void deleteQueue(Queue *queue);

void append(Queue *queue, int value);
int pop(Queue *queue);
int lastElement(Queue *queue);

int length(Queue *queue);
bool isEmpty(Queue *queue);
