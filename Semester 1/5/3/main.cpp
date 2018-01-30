#include <iostream>
using namespace std;

int abs(int x)
{
    return x > 0 ? x : -x;
}

char sigh(int x)
{
    return x > 0 ? '+' : '-';
}

int length(int number)
{
    number = abs(number);

    int result = 0;
    while (number > 0)
    {
        result++;
        number /= 10;
    }
    return result;
}

int *inputCoefs(int degree)
{
    int *factors = new int[degree + 1];
    for (int i = degree; i >= 0; i--)
        cin >> factors[i];
    return factors;
}

void printDegrees(int *factors, int maxDegree)
{
    if (factors[maxDegree] < 0)
        cout << " ";

    if (abs(factors[maxDegree]) != 1)
        for (int i = 0; i < length(factors[maxDegree]); i++)
            cout << " ";
    cout << " " << maxDegree;

    for (int currentDegree = maxDegree - 1; currentDegree > 1; currentDegree--)
        if (factors[currentDegree] != 0)
        {
            cout << "   ";
            if (abs(factors[currentDegree]) != 1)
                for (int i = 0; i < length(factors[currentDegree]); i++)
                    cout << " ";
            cout << currentDegree;
        }
    cout << endl;
}

void printCoefs(int *factors, int maxDegree)
{
    if (maxDegree > 0)
    {
        if (maxDegree > 1)
            printDegrees(factors, maxDegree);

        if (sigh(factors[maxDegree]) == '-')
            cout << '-';

        if (abs(factors[maxDegree]) != 1)
            cout << abs(factors[maxDegree]);
        cout << 'x';
        for (int i = 0; i < length(maxDegree) - 1; i++)
            cout << " ";
    }

    for (int currentDegree = maxDegree - 1; currentDegree > 0; currentDegree--)
        if (factors[currentDegree] != 0)
        {
            cout << " " << sigh(factors[currentDegree]) << " ";
            if (abs(factors[currentDegree]) != 1)
                cout << abs(factors[currentDegree]);
            cout << 'x';
            for (int i = 0; i < length(currentDegree) - 1; i++)
                cout << " ";
        }

    if (factors[0] != 0)
        if (maxDegree > 0)
            cout << " " << sigh(factors[0]) << " " << abs(factors[0]);
        else
            cout << factors[0];
}

int main()
{
    int degree = 0;
    cout << "Enter degree of polynom: ";
    cin >> degree;
    cout << "Enter coefficients: ";
    int *factors = inputCoefs(degree);

    printCoefs(factors, degree);

    delete[] factors;
    return 0;
}