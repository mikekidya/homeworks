#include <iostream>
using namespace std;

int *toBits(int number)
{
    int *bits = new int[32];
    int mask = 1;
    for (int i = 0; i < 32; i++)
    {
        bits[i] = number & mask ? 1 : 0;
        mask = mask << 1;
    }
    return bits;
}

int *getSum(int *firstNumber, int *secondNumber)
{
    int *sum = new int[32];
    int memory = 0;
    for (int i = 0; i < 32; i++)
    {
        sum[i] = (firstNumber[i] + secondNumber[i] + memory) % 2;
        memory = (firstNumber[i] + secondNumber[i] + memory) / 2;
    }
    return sum;
}

void printBits(int *number)
{
    for (int i = 31; i >= 0; i--)
    {
        cout << number[i];
        if (i % 8 == 0)
            cout << " ";
    }
    cout << endl;
}

int toNumber(int *bits)
{
    int degree = 1;
    int result = 0;
    for (int i = 0; i < 32; i++)
    {
        result += bits[i] * degree;
        degree = degree << 1;
    }
    return result;
}

int main()
{
    int firstNumber = 0;
    cout << "Enter first number: ";
    cin >> firstNumber;
    int *firstNumberBits = toBits(firstNumber);
    int secondNumber = 0;
    cout << "Enter second number: ";
    cin >> secondNumber;
    int *secondNumberBits = toBits(secondNumber);

    int *sum = getSum(firstNumberBits, secondNumberBits);

    cout << "First number  : ";
    printBits(firstNumberBits);
    cout << "Second number : ";
    printBits(secondNumberBits);
    cout << "Sum           : ";
    printBits(sum);
    cout << "Sum: " << toNumber(sum) << endl;

    delete[] firstNumberBits;
    delete[] secondNumberBits;
    delete[] sum;
    return 0;
}