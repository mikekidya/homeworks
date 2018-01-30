#include <iostream>

void printAddends(int *a, int length)
{
    std::cout << "= " << a[0];
    for (int i = 1; i < length; i++)
        std::cout << " + " << a[i];
    std::cout << std::endl;
}

void toAddends(int n, int *addends, int position, int minimum)
{
    if (n < 0)
        return;

    if (n == 0)
        printAddends(addends, position);

    for (int i = minimum; i <= n; i++)
    {
        addends[position] = i;
        toAddends(n - i, addends, position + 1, i);
    }
}

int main()
{
    int n = 0;
    std::cout << "Enter integer n to present it as a sum: " << std::endl;
    std::cin >> n;

    int addends[1000] = {0};
    toAddends(n, addends, 0, 1);
    return 0;
}