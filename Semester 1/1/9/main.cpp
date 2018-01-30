#include <iostream>

int main()
{
    double a = 1.0;
    int n = 1;
    std::cout << "Enter a and integer n to calculate a^n: ";
    std::cin >> a >> n;

    int i = 0;
    double result = 1.0;
    for (i = 1; i <= n; i++)
        result *= a;

    std::cout << "a^n = " << result << std::endl;
    return 0;
}