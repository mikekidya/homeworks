#include <iostream>

int binPower(int a, int n)
{
    if (n == 1)
        return a;

    if (n % 2 == 1)
        return a * binPower(a, n - 1);

    int res = binPower(a, n / 2);
    return res * res;
}

int main()
{
    int a = 0;
    int n = 0;
    std::cout << "Enter integers a and n to calculate a^n: ";
    std::cin >> a >> n;

    std::cout << "a^n = " << binPower(a, n) << std::endl;
    return 0;
}