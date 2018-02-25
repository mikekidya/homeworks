package group144.kidyankin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter an expression in postfix form to calculate it:");
        Scanner in = new Scanner(System.in);
        String exp = in.nextLine();
        Calculator calc = new Calculator();
        System.out.print("Result: ");
        System.out.print(calc.calculate(exp));
    }
}
