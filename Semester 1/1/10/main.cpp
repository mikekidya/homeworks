#include <iostream>

int main()
{
    char string[1000] = {' '};
    int stringLength = 0;
    std::cout << "Enter strings' length: ";
    std::cin >> stringLength;
    std::cout << "Enter the string:" << std::endl;
    for (int i = 0; i < stringLength; i++)
        std::cin >> string[i];

    int i = 0;
    int j = stringLength - 1;
    while ((i < j) && (string[i] == string[j]))
    {
        i++;
        j--;
    }

    if (i < j)
        std::cout << "The string is not a palindrome." << std::endl;
    else
        std::cout << "The string is a palindrome." << std::endl;
    return 0;
}