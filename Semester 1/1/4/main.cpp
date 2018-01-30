#include <iostream>

int main() {
    int const sums = 28;
    int i = 0;
    int numberOfSums[sums];
    for (i = 0; i < sums; i++)
        numberOfSums[i] = 0;
    int firstDigit = 0;
    int secondDigit = 0;
    int thirdDigit = 0;
    for (firstDigit = 0; firstDigit < 10; firstDigit++)
        for (secondDigit = 0; secondDigit < 10; secondDigit++)
            for (thirdDigit = 0; thirdDigit < 10; thirdDigit++)
                numberOfSums[firstDigit + secondDigit + thirdDigit]++;
    int result = 0;
    for (i = 0; i < sums; i++)
        result += numberOfSums[i] * numberOfSums[i];
    std::cout << "The number of \"happy tickets\" is " << result << std::endl;
    return 0;
}