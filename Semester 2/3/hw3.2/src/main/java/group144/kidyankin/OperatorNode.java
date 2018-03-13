package group144.kidyankin;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OperatorNode implements ExpressionTreeNode {
    private char operation;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;

    OperatorNode(Scanner scanner) throws WrongTreeInputException {
        String input = scanner.next();
        operation = input.charAt(input.length() - 1); // "(+"
        if (operation != '+' && operation != '-' && operation != '*' && operation != '/') {
            throw new WrongTreeInputException("Wrong structure: operation was expected");
        }

        if (scanner.hasNextInt()) {
            left = new OperandNode(scanner);
        } else {
            left = new OperatorNode(scanner);
        }
        if (scanner.hasNext(Pattern.compile("[-]?[0-9]+([)]+)?"))) {
            right = new OperandNode(scanner);
        } else {
            right = new OperatorNode(scanner);
        }

    }

    @Override
    public int calculate() {
        switch (operation) {
            case '+':
                return left.calculate() + right.calculate();
            case '-':
                return left.calculate() - right.calculate();
            case '*':
                return left.calculate() * right.calculate();
            case '/':
                return left.calculate() / right.calculate();
            default:
                return 0;
        }
    }

    @Override
    public void print(PrintStream stream) {
        stream.print('(');
        stream.print(operation);
        stream.print(' ');
        left.print(stream);
        stream.print(' ');
        right.print(stream);
        stream.print(')');
    }
}
