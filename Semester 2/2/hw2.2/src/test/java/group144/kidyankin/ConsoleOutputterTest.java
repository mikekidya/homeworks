package group144.kidyankin;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ConsoleOutputterTest {

    @Test
    public void printSpiral5() throws FileNotFoundException{
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        SpiralWriter writer = new ConsoleOutputter();
        int[][] array = {{1, 2, 3, 4, 5},
                         {6, 7, 8, 9, 0},
                         {1, 2, 3, 4, 5},
                         {6, 7, 8, 9, 0},
                         {1, 2, 3, 4, 5}};
        writer.printSpiral(array);
        String EXPECTED = "Elements in spiral order: 3 8 7 2 7 8 9 4 9 4 3 2 1 6 1 6 1 2 3 4 5 0 5 0 5 ";
        assertEquals(EXPECTED, arrayOutputStream.toString());
    }

    @Test
    public void printSpiral3() throws FileNotFoundException{
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        SpiralWriter writer = new ConsoleOutputter();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        writer.printSpiral(array);
        String EXPECTED = "Elements in spiral order: 5 2 1 4 7 8 9 6 3 ";
        assertEquals(EXPECTED, arrayOutputStream.toString());
    }

    @Test
    public void printSpiral1() throws FileNotFoundException{
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        SpiralWriter writer = new ConsoleOutputter();
        int[][] array = {{1}};
        writer.printSpiral(array);
        String EXPECTED = "Elements in spiral order: 1 ";
        assertEquals(EXPECTED, arrayOutputStream.toString());
    }
}