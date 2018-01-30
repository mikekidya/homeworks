#include <iostream>

void swap(int &a, int &b)
{
    int t = a;
    a = b;
    b = t;
}

int heapPop(int *heap, int length)
{
    int result = heap[0];
    heap[0] = heap[length - 1];
    length--;
    int current = 0;
    int leftSon = 2 * current + 1;
    int rightSon = 2 * current + 2;
    while ((rightSon < length) && ((heap[leftSon] > heap[current]) || (heap[rightSon] > heap[current])))
    {
        if (heap[leftSon] > heap[rightSon])
        {
            swap(heap[current], heap[leftSon]);
            current = leftSon;
        }
        else
        {
            swap(heap[current], heap[rightSon]);
            current = rightSon;
        }
        leftSon = 2 * current + 1;
        rightSon = 2 * current + 2;
    }
    if ((leftSon < length) && (heap[leftSon] > heap[current]))
        swap(heap[current], heap[leftSon]);

    return result;
}

void heapCreate(int *heap, int length)
{
    int current = 0;
    int parent = 0;
    for (int currentLength = 1; currentLength <= length; currentLength++)
    {
        current = currentLength - 1;
        parent = (current - 1) / 2;
        while ((current > 0) && (heap[current] > heap[parent]))
        {
            swap(heap[current], heap[parent]);
            current = parent;
            parent = (current - 1) / 2;
        }
    }
}

void heapSort(int *a, int length)
{
    heapCreate(a, length);

    for (int i = length - 1; i >= 0; i--)
        a[i] = heapPop(a, i + 1);

}

int main()
{
    int n = 0;
    std::cout << "Enter number of elements: ";
    std::cin >> n;

    int a[10000] = {0};
    std::cout << "Enter an array to sort it:" << std::endl;
    for (int i = 0; i < n; i++)
        std::cin >> a[i];

    heapSort(a, n);

    std::cout << "Sorted array: " << std::endl;
    for (int i = 0; i < n; i++)
        std::cout << a[i] << " ";

    return 0;
}