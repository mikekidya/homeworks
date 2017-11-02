#include <iostream>

int main()
{
    int a = 0;
    int b = 0;
    std::cout << "Enter integer numbers a b to find a div b: ";
    std::cin >> a >> b;

    int k = 0;
    if (a * b >= 0)
    {
        while (a * b > 0)
        {
            a -= b;
            k++;
        }
        if (a < 0)
            k--;
    }
    else
    {
        while (a * b < 0)
        {
            a += b;
            k--;
        }
        if (a < 0)
            k++;
    }

    std::cout << "a div b = " << k << std::endl;

    return 0;
}
