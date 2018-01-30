#include <iostream>

using namespace std;

int **createArray(int size)
{
    int **newArray = new int*[size];
    for (int i = 0; i < size; i++)
    {
        newArray[i] = new int[size];
    }
    return newArray;

}

void deleteArray(int **array, int size)
{
    for (int i = 0; i < size; i++)
        delete[] array[i];
    delete[] array;
}

void inputArray(int **array, int size)
{
    for (int i = 0; i < size; i++)
        for (int j = 0; j < size; j++)
            cin >> array[i][j];
}

void printSpiral(int **array, int size)
{
    int leftSteps = 1;
    int downSteps = 2;
    int rightSteps = 2;
    int upSteps = 2;

    int iCurrent = size / 2;
    int jCurrent = size / 2;

    cout << array[iCurrent][jCurrent] << " ";

    while (!(iCurrent == 0 && jCurrent == size - 1))
    {
        iCurrent--;
        cout << array[iCurrent][jCurrent] << " ";

        for (int k = 0; k < leftSteps; k++)
        {
            jCurrent--;
            cout << array[iCurrent][jCurrent] << " ";
        }

        for (int k = 0; k < downSteps; k++)
        {
            iCurrent++;
            cout << array[iCurrent][jCurrent] << " ";
        }

        for (int k = 0; k < rightSteps; k++)
        {
            jCurrent++;
            cout << array[iCurrent][jCurrent] << " ";
        }

        for (int k = 0; k < upSteps; k++)
        {
            iCurrent--;
            cout << array[iCurrent][jCurrent] << " ";
        }

        leftSteps += 2;
        downSteps += 2;
        rightSteps += 2;
        upSteps += 2;
    }

}

int main()
{
    int arraySize = 0;
    cout << "Enter arrays' size (n): ";
    cin >> arraySize;

    int **array = createArray(arraySize);
    cout << "Enter the array" << endl;
    inputArray(array, arraySize);

    cout << "Spiral printed: ";
    printSpiral(array, arraySize);

    deleteArray(array, arraySize);

    return 0;
}