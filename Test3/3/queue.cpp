#include "queue.h"

struct QueueElement
{
    int value;
    QueueElement *next;
};

struct Queue
{
    QueueElement *head;
    QueueElement *tail;
};

Queue *createQueue()
{
    Queue *newQueue = new Queue;
    newQueue->head = nullptr;
    newQueue->tail = nullptr;
    return newQueue;
}

void deleteQueue(Queue *queue)
{
    while (!isEmpty(queue))
        pop(queue);
    delete queue;
}

void append(Queue *queue, int value)
{
    QueueElement *newElement = new QueueElement;
    newElement->value = value;
    newElement->next = nullptr;
    if (!isEmpty(queue))
        queue->tail->next = newElement;
    if (isEmpty(queue))
        queue->head = newElement;
    queue->tail = newElement;

}

int pop(Queue *queue)
{
    int value = queue->head->value;

    QueueElement *tmp = queue->head->next;
    delete queue->head;
    queue->head = tmp;

    return value;
}

int lastElement(Queue *queue)
{
    return queue->tail->value;
}

bool isEmpty(Queue *queue)
{
    return queue->head == nullptr;
}

int length(Queue *queue)
{
    int result = 0;

    QueueElement *currentElement = queue->head;
    while (currentElement != nullptr)
    {
        currentElement = currentElement->next;
        result++;
    }

    return result;
}