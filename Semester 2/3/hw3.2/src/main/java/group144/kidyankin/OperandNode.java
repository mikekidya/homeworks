package group144.kidyankin;

import java.io.PrintStream;
import java.util.Scanner;

public class OperandNode implements ExpressionTreeNode {
    private int value;

    OperandNode(Scanner scanner) throws WrongTreeInputException {
        try {
            String input = scanner.next();
            if (input.charAt(input.length() - 1) == ')') {
                value = Integer.parseInt(input.substring(0, input.indexOf(')')));
            } else {
                value = Integer.parseInt(input);
            }

        } catch (Exception exception) {
            throw new WrongTreeInputException();
        }
    }

    @Override
    public int calculate() {
        return value;
    }

    @Override
    public void print(PrintStream stream) {
        stream.print(value);
    }
}
