#include <iostream>
using namespace std;

int **inputMatrix(int lines, int columns)
{
    int **matrix = new int*[lines];
    for (int i = 0; i < lines; i++)
    {
        matrix[i] = new int[columns];
        for (int j = 0; j < columns; j++)
            cin >> matrix[i][j];
    }
    return matrix;
}

void deleteMatrix(int **matrix, int lines)
{
    for (int i = 0; i < lines; i++)
        delete[] matrix[i];
    delete[] matrix;
}

bool isMin(int **matrix, int line, int columns, int value)
{
    for (int i = 0; i < columns; i++)
        if (matrix[line][i] < value)
            return false;
    return true;
}

bool isMax(int **matrix, int lines, int column, int value)
{
    for (int i = 0; i < lines; i++)
        if (matrix[i][column] > value)
            return false;
    return true;
}

int main()
{
    int numberOfColumns = 0;
    int numberOfLines = 0;
    cout << "Enter number of lines: ";
    cin >> numberOfLines;
    cout << "Enter number of columns: ";
    cin >> numberOfColumns;

    cout << "Enter a matrix: " << endl;
    int **matrix = inputMatrix(numberOfLines, numberOfColumns);

    cout << "Saddle points: " << endl;
    for (int i = 0; i < numberOfLines; i++)
        for (int j = 0; j < numberOfColumns; j++)
            if (isMin(matrix, i, numberOfColumns, matrix[i][j]) && isMax(matrix, numberOfLines, j, matrix[i][j]))
                cout << matrix[i][j] << " (" << i << ", " << j << ")" << endl;

    deleteMatrix(matrix, numberOfLines);
    return 0;
}