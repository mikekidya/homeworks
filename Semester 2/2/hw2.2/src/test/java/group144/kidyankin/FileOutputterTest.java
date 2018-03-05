package group144.kidyankin;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FileOutputterTest {

    @Test
    public void printSpiral1() throws FileNotFoundException{
        SpiralWriter writer = new FileOutputter();
        int[][] array = {{1}};
        writer.printSpiral(array);
        Scanner file = new Scanner(new File("output.txt"));
        file.useDelimiter("\n");
        String EXPECTED = "Elements in spiral order: 1 ";
        assertEquals(EXPECTED, file.next());
    }

    @Test
    public void printSpiral3() throws FileNotFoundException{
        SpiralWriter writer = new FileOutputter();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        writer.printSpiral(array);
        Scanner file = new Scanner(new File("output.txt"));
        file.useDelimiter("\n");
        String EXPECTED = "Elements in spiral order: 5 2 1 4 7 8 9 6 3 ";
        assertEquals(EXPECTED, file.next());
    }

    @Test
    public void printSpiral5() throws FileNotFoundException{
        SpiralWriter writer = new FileOutputter();
        int[][] array = {{1, 2, 3, 4, 5},
                         {6, 7, 8, 9, 0},
                         {1, 2, 3, 4, 5},
                         {6, 7, 8, 9, 0},
                         {1, 2, 3, 4, 5}};
        writer.printSpiral(array);
        Scanner file = new Scanner(new File("output.txt"));
        file.useDelimiter("\n");
        String EXPECTED = "Elements in spiral order: 3 8 7 2 7 8 9 4 9 4 3 2 1 6 1 6 1 2 3 4 5 0 5 0 5 ";
        assertEquals(EXPECTED, file.next());
    }

}