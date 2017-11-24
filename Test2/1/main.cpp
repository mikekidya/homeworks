#include <iostream>

int fibonacci(int n)
{
    int previous = 1;
    int current = 1;
    for (int i = 3; i <= n; i++)
    {
        current = current + previous;
        previous = current - previous;
    }
    return current;
}

int main()
{
    int n = 0;
    std::cout << "Enter integer n to calculate Fibonaccis' n number: ";
    std::cin >> n;
    std::cout << "Result: " << fibonacci(n) << std::endl;
    return 0;
}