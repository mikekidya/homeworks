#include <iostream>
#include "sort.h"
using namespace std;

int lengthOfNumber(int n)
{
    int length = 0;
    while (n > 0)
    {
        length++;
        n /= 10;
    }
    return length;
}

int *intToDigits(int n, int length)
{
    int *digits = new int[length];
    for (int i = 0; i < length; i++)
        digits[i] = 0;
    int currentIndex = 0;
    while (n > 0)
    {
        digits[currentIndex] = n % 10;
        currentIndex++;
        n /= 10;
    }
    return digits;
}

int main()
{
    int number = 0;
    cout << "Enter integer n: ";
    cin >> number;

    int length = lengthOfNumber(number);
    int *digits = intToDigits(number, length);

    qsort(digits, 0, length - 1);

    int numberOfZeros = 0;
    while (digits[numberOfZeros] == 0)
        numberOfZeros++;

    cout << "The most minimal number is ";
    cout << digits[numberOfZeros];
    for (int i = 0; i < numberOfZeros; i++)
        cout << 0;
    for (int j = numberOfZeros + 1; j < length; j++)
        cout << digits[j];

    delete[] digits;
    return 0;
}