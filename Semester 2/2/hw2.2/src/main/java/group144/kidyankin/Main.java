package group144.kidyankin;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] array = arrayInput(in);
        System.out.print("Print in spiral order in file (F) or screen (S)? ");
        String choice = in.next();
        while (!choice.equals("F") && !choice.equals("S")) {
            System.out.println("Incorrect choice. Try again: ");
            choice = in.next();
        }
        SpiralWriter writer;
        if (choice.equals("F")) {
            writer = new FileOutputter();
        } else {
            writer = new ConsoleOutputter();
        }
        try {
            writer.printSpiral(array);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
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
