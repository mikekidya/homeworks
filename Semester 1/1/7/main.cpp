#include <iostream>

int main()
{
    int n = 0;
    std::cout << "Enter integer n to print all primes from 1 to n: ";
    std::cin >> n;
    int isPrime[1000];
    int i = 0;
    for (i = 0; i < 1000; i++)
        isPrime[i] = true;

    int j = 0;
    isPrime[0] = isPrime[1] = false;
    for (i = 2; i <= n; i++)
        if (isPrime[i])
        {
            j = i + i;
            while (j <= n)
            {
                isPrime[j] = false;
                j += i;
            }
        }

    for (i = 2; i <= n; i++)
        if (isPrime[i])
            std::cout << i << " ";
    return 0;
}