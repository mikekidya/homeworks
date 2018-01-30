#include <iostream>
using namespace std;

int exponent(double number)
{
    unsigned char *b = reinterpret_cast<unsigned char*> (&number);
    int power = b[7] & 0x7F;
    power = power << 4;
    power = power | ((b[6] & 0xF0) >> 4);

    power = power - ((1 << 10) - 1);
    return power;
}

char sign(double number)
{
    unsigned char *b = reinterpret_cast<unsigned char*> (&number);
    return (b[7] & 0x80 ? '-': '+');
}

double mantissa(double number)
{
    unsigned char *b = reinterpret_cast<unsigned char*> (&number);
    b[7] = 0x3F;
    b[6] = b[6] | 0xF0;
    return number;
}

int main()
{
    double number = 0.0;
    cout << "Enter a number: ";
    cin >> number;
    cout.precision(20);
    cout << "Result: " << sign(number) << mantissa(number) << "*2^" << exponent(number);
    return 0;
}
