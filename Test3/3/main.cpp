#include <iostream>

bool isLetter(char symbol)
{
    return symbol >= 'a' && symbol <= 'z';
}

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

int main()
{
    enum {start, afterFirstLetter, afterFirstDigit, afterFirstDot, afterFirstUnder, afterPercent, afterPlus,
        afterFirstMinus, afterDog, afterSecondLetter, afterSecondDigit, afterSecondMinus, afterSecondDot, fail} status;

    std::cout << "Enter an expression" << std::endl;
    char current = std::cin.get();

    status = start;

    while (current != '\n')
    {
        switch (status)
        {
            case start:
                if (isLetter(current))
                    status = afterFirstLetter;
                else
                    status = fail;
                break;

            case afterFirstLetter:
                if (isDigit(current))
                    status = afterFirstDigit;
                else
                    status = fail;
                break;

            case afterFirstDigit:
                if (current == '.')
                    status = afterFirstDot;
                else
                    status = fail;
                break;

            case afterFirstDot:
                if (current == '_')
                    status = afterFirstUnder;
                else
                    status = fail;
                break;

            case afterFirstUnder:
                if (current == '%')
                    status = afterPercent;
                else
                    status = fail;
                break;

            case afterPercent:
                if (current == '+')
                    status = afterPlus;
                else
                    status = fail;
                break;

            case afterPlus:
                if (current == '-')
                    status = afterFirstMinus;
                else
                    status = fail;
                break;

            case afterFirstMinus:
                if (current == '@')
                    status = afterDog;
                else if (isLetter(current))
                    status = afterFirstLetter;
                else
                    status = fail;
                break;

            case afterDog:
                if (isLetter(current))
                    status = afterSecondLetter;
                else
                    status = fail;
                break;

            case afterSecondLetter:
                if (isDigit(current))
                    status = afterSecondDigit;
                else
                    status = fail;
                break;

            case afterSecondDigit:
                if (current == '-')
                    status = afterSecondMinus;
                else
                    status = fail;
                break;

            case afterSecondMinus:
                if (current == '.')
                    status = afterSecondDot;
                else if (isLetter(current))
                    status = afterSecondLetter;
                else
                    status = fail;
                break;

            case afterSecondDot:
                if (isLetter(current))
                    status = afterSecondLetter;
                else
                    status = fail;
                break;
        }
        current = std::cin.get();
    }

    if (status == afterSecondLetter)
        std::cout << "Correct!" << std::endl;
    else
        std::cout << "Not correct!" << std::endl;

    return 0;
}