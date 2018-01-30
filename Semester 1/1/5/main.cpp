#include <iostream>

int main()
{
    int length = 0;
    std::cout << "Enter strings' length: ";
    std::cin >> length;
    char symbol = ' ';
    int counter = 0;
    int i = 0;
    std::cout << "Enter the string with brackets to check it:" << std::endl;
    while ((counter >= 0) && (i < length))
    {
        std::cin >> symbol;
        if (symbol == '(')
            counter++;
        else if (symbol == ')')
            counter --;
        i++;
    }
    if (counter == 0)
        std::cout << "Correct" << std::endl;
    else
        std::cout << "Not correct" << std::endl;
    return 0;
}