package group144.kidyankin;

import java.io.PrintStream;
import java.util.Scanner;

public class ExpressionTree {
    private ExpressionTreeNode head;

    ExpressionTree(Scanner scanner) throws WrongTreeInputException {
        if (scanner.hasNextInt()) {
            head = new OperandNode(scanner);
        } else {
            head = new OperatorNode(scanner);
        }
    }

    public void print(PrintStream stream) {
        head.print(stream);
    }

    public int calculate() {
        return head.calculate();
    }
}
