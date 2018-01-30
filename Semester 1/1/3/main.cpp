#include <iostream>

void reverseArray(int *arr, int start, int end)
{
    int i = 0;
    int t = 0;
    for (i = 0; i <= (end - start) / 2; i++)
    {
        t = arr[start + i];
        arr[start + i] = arr[end - i];
        arr[end - i] = t;
    }
}

int main()
{
    int m = 0;
    int n = 0;
    int i = 0;
    int x[1000];
    for (i = 0; i < 1000; i++)
        x[i] = 0;
    std::cout << "Enter integer m and n (lengths of arrays' parts): ";
    std::cin >> m >> n;
    std::cout << "Enter the array: ";
    for (i = 0; i < m + n; i++)
        std::cin >> x[i];
    reverseArray(x, 0, m - 1);
    reverseArray(x, m, m + n - 1);
    reverseArray(x, 0, m + n - 1);
    std::cout << "Result: ";
    for (i = 0; i < m + n; i++)
        std::cout << x[i] << " ";
    return 0;
}