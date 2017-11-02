#include <iostream>

int main()
{
    double x = 0;
    std::cout << "Enter number x to calculate x^4 + x^3 + x^2 + x + 1: ";
    std::cin >> x;
    double x2 = x * x;
    double result = (x2 + x) * (x2 + 1) + 1;
    std::cout << "x^4 + x^3 + x^2 + x + 1 = " << result << std::endl;
    return 0;
}
