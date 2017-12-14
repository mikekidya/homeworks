#include <iostream>

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

int main()
{
    enum {start, afterSigh, integralPart, afterDot, digitAfterDot, afterE, afterESigh, digitAfterE, fail} status;
    status = start;

    char current = std::cin.get();

    while (current != '\n')
    {
        switch (status)
        {
            case start:
                if (current == '+' || current == '-')
                    status = afterSigh;
                else if (isDigit(current))
                    status = integralPart;
                else
                    status = fail;
                break;
            case afterSigh:
                if (isDigit(current))
                    status = integralPart;
                else
                    status = fail;
                break;
            case integralPart:
                if (isDigit(current))
                    status = integralPart;
                else if (current == '.')
                    status = afterDot;
                else if (current == 'E')
                    status = afterE;
                else
                    status = fail;
                break;
            case afterDot:
                if (isDigit(current))
                    status = digitAfterDot;
                else
                    status = fail;
                break;
            case digitAfterDot:
                if (isDigit(current))
                    status = digitAfterDot;
                else if (current == 'E')
                    status = afterE;
                else
                    status = fail;
                break;
            case afterE:
                if (current == '+' || current == '-')
                    status = afterESigh;
                else if (isDigit(current))
                    status = digitAfterE;
                else
                    status = fail;
                break;
            case afterESigh:
                if (isDigit(current))
                    status = digitAfterE;
                else
                    status = fail;
                break;
            case digitAfterE:
                if (!isDigit(current))
                    status = fail;
                break;
        }
        current = std::cin.get();
    }

    if (status == integralPart || status == digitAfterDot || status == digitAfterE)
        std::cout << "This is the double real number";
    else
        std::cout << "This is not the double real number";
    return 0;
}