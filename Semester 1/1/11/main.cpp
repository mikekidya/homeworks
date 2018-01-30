#include <iostream>

void qsort(int *array, int left, int right)
{
    if (right - left <= 1)
        return;

    int p = array[(left + right) / 2];
    int i = left;
    int j = right;
    while (i < j)
    {
        while (array[i] < p)
            i++;

        while (array[j] > p)
            j--;

        if (i < j)
        {
            int t = array[i];
            array[i] = array[j];
            array[j] = t;
            i++;
            j--;
        }
    }
    qsort(array, left, j);
    qsort(array, i, right);
}

int main()
{
    int array[1000];
    int i = 0;
    for (i = 0; i < 1000; i++)
        array[i] = 0;
    int arrayLength = 0;
    std::cout << "Enter arrays' length: ";
    std::cin >> arrayLength;
    std::cout << "Enter array to sort it:" << std::endl;
    for (i = 0; i < arrayLength; i++)
        std::cin >> array[i];

    qsort(array, 0, arrayLength - 1);

    std::cout << "Sorted array:" << std::endl;
    for (i = 0; i < arrayLength; i++)
        std::cout << array[i] << " ";

    return 0;
}