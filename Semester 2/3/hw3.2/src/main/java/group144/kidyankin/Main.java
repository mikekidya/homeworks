package group144.kidyankin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in;
        try {
            in = new Scanner(new File("input.txt"));
            ExpressionTree tree = new ExpressionTree(in);
            System.out.println("Expression tree:");
            tree.print(System.out);
            System.out.print("\nResult: ");
            System.out.println(tree.calculate());
        } catch (FileNotFoundException fileException) {
            System.out.println("File is not found");
        } catch (WrongTreeInputException wrongTreeException) {
            System.out.println("Wrong tree input");
        }
    }
}
