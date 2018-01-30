#include <iostream>

void inputString(char *string, int length)
{
    for (int i = 0; i < length; i++)
        std::cin >> string[i];
}

int main()
{
    int lengthS = 0;
    char s[100] = {' '};
    std::cout << "Enter s length: ";
    std::cin >> lengthS;
    std::cout << "Enter s:" << std::endl;
    inputString(s, lengthS);

    int lengthS1 = 0;
    char s1[100] = {' '};
    std::cout << "Enter s1 length: ";
    std::cin >> lengthS1;
    std::cout << "Enter s1:" << std::endl;
    inputString(s1, lengthS1);

    int counter = 0;
    int j = 0;
    for (int i = 0; i <= lengthS - lengthS1; i++)
    {
        j = 0;
        while ((j < lengthS1) && (s[i + j] == s1[j]))
            j++;
        if (j == lengthS1)
            counter++;
    }

    std::cout << "s1 is in s " << counter << " times";
    return 0;
}