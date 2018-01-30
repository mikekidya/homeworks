#include <iostream>

int factorialRecursion(int n)
{
    if (n <= 1)
        return 1;
    else
        return n * factorialRecursion(n - 1);
}

int factorialIteration(int n)
{
    int result = 1;
    for (int i = 2; i <= n; i++)
        result *= i;
    return result;
}

int main()
{
    int n = 0;
    std::cout << "Enter integer n to calculate n! : ";
    std::cin >> n;

    std::cout << "Recursion algorithm: " << factorialRecursion(n) << std::endl;
    std::cout << "Iteration algorithm: " << factorialIteration(n) << std::endl;
    return 0;
}