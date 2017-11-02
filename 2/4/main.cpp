#include <iostream>

struct Fraction
{
    int numerator;
    int denominator;
};

void swap(int &a, int &b)
{
    int t = a;
    a = b;
    b = t;
}

void qsortFractions(Fraction *fraction, int left, int right)
{
    if (right - left <= 1)
        return;
    Fraction p = {fraction[(left + right) / 2].numerator, fraction[(left + right) / 2].denominator};
    int i = left;
    int j = right;
    while (i < j)
    {
        while (fraction[i].numerator * p.denominator < p.numerator * fraction[i].denominator)
            i++;

        while (fraction[j].numerator * p.denominator > p.numerator * fraction[j].denominator)
            j--;

        if (i < j)
        {
            swap(fraction[i].numerator, fraction[j].numerator);
            swap(fraction[i].denominator, fraction[j].denominator);
            i++;
            j--;
        }
    }
    qsortFractions(fraction, left, j);
    qsortFractions(fraction, i, right);
}

int gcd(int a, int b)
{
    if (a < b)
        swap(a, b);

    while (b > 0)
    {
        a = a % b;
        swap(a, b);
    }

    return a;
}

int generateFractions(Fraction *fraction, int maxDenominator) //returns number of fractions
{
    int currentPosition = 0;
    for (int q = 2; q <= maxDenominator; q++)
        for (int p = 1; p < q; p++)
            if (gcd(p, q) == 1)
            {
                fraction[currentPosition].numerator = p;
                fraction[currentPosition].denominator = q;
                currentPosition++;
            }
    return currentPosition;
}

void printFractions(Fraction *fraction, int length)
{
    for (int i = 0; i < length; i++)
        std::cout << fraction[i].numerator << "/" << fraction[i].denominator << std::endl;
}

int main()
{
    int n = 0;
    std::cout << "Enter n to get all positive fractions p/q < 1; q <= n: ";
    std::cin >> n;

    Fraction *fraction = new Fraction[n * n];

    int length = generateFractions(fraction, n);
    qsortFractions(fraction, 0, length - 1);
    printFractions(fraction, length);

    delete[] fraction;

    return 0;
}