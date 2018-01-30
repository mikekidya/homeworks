#include <iostream>
#include "sort.h"


int *arrayInput(int length)
{
    int *array = new int[length];
    for (int i = 0; i < length; i++)
        std::cin >> array[i];
    return array;
}

void reverseArray(int *array, int length)
{
    int t = 0;
    for (int i = 0; i < length / 2; i++)
    {
        t = array[i];
        array[i] = array[length - 1 - i];
        array[length - 1 - i] = t;
    }

}

int main()
{
    int length = 0;
    std::cout << "Enter arrays' length: ";
    std::cin >> length;
    std::cout << "Enter the array: " << std::endl;
    int *array = arrayInput(length);

    qsort(array, 0, length - 1);
    reverseArray(array, length);

    int i = 0;
    while ((i + 1 < length) && (array[i] != array[i + 1]))
    {
        i++;
    }

    if (i + 1 == length)
        std::cout << "Every element is unique" << std::endl;
    else
        std::cout << "The biggest not unique element is " << array[i] << std::endl;

    delete[] array;

    return 0;
}