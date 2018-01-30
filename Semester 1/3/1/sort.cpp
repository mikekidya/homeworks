#include "sort.h"

void swap(int &a, int &b)
{
    int t = a;
    a = b;
    b = t;
}

void qsort(int *array, int left, int right)
{
    if (right <= left)
        return;

    int p = array[(left + right) / 2];
    int i = left;
    int j = right;
    while (i <= j)
    {
        while (array[i] < p)
            i++;

        while (array[j] > p)
            j--;

        if (i <= j)
        {
            swap(array[i], array[j]);
            i++;
            j--;
        }
    }
    qsort(array, left, j);
    qsort(array, i, right);
}


void stringSort(char *string, int length)
{
    int minSymbol = (int) string[0];
    int maxSymbol = (int) string[0];

    for (int i = 1; i < length; i++)
    {
        if (minSymbol > (int) string[i])
            minSymbol = (int) string[i];

        if (maxSymbol < (int) string[i])
            maxSymbol = (int) string[i];
    }

    int *letters = new int[maxSymbol - minSymbol + 1];
    for (int i = 0; i < maxSymbol - minSymbol + 1; i++)
        letters[i] = 0;

    for (int i = 0; i < length; i++)
        letters[((int) string[i]) - minSymbol]++;

    int currentPosition = 0;
    for (int i = 0; i < maxSymbol - minSymbol + 1; i++)
        while (letters[i] > 0)
        {
            string[currentPosition] = (char) (minSymbol + i);
            currentPosition++;
            letters[i]--;
        }

    delete[] letters;

}