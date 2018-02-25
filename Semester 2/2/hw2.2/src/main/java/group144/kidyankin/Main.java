package group144.kidyankin;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int[][] array = arrayInput(in);
        System.out.print("Print in spiral order in file (F) or screen (S)? ");
        switch (in.next()) {
            case "F": {
                PrintStream file = new PrintStream("output.txt");
                SpiralWriter writer = new FileOutputter();
                writer.printSpiral(array);
                file.close();
                break;
            }
            case "S": {
                SpiralWriter writer = new ConsoleOutputter();
                writer.printSpiral(array);
                break;
            }
            default:
                System.out.println("Incorrect choice");
                break;
        }
    }

    private static int[][] arrayInput(Scanner in) {
        System.out.print("Enter array size (odd number): ");
        int size = in.nextInt();
        System.out.println("Enter the array:");
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = in.nextInt();
            }
        }
        return array;
    }
}
