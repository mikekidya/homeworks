#include <iostream>

int fibonacciRecursion(int n)
{
    if (n <= 2)
        return 1;
    return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
}

int fibonacciIteration(int n)
{
    int a = 1;
    int b = 1;
    for (int i = 3; i <= n; i++)
    {
        b = a + b;
        a = b - a;
    }
    return b;
}

int main()
{
    int n = 0;
    std::cout << "Enter integer n to calculate Fibonaccis' n number: ";
    std::cin >> n;

    std::cout << "Recursion algorithm: " << fibonacciRecursion(n) << std::endl;
    std::cout << "Iteration algorithm: " << fibonacciIteration(n) << std::endl;
    return 0;
}