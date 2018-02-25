package group144.kidyankin;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void mainConsole() throws FileNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream("3\n1 2 3\n4 5 6\n7 8 9\nS\n".getBytes());
        System.setOut(new PrintStream(arrayOutputStream));
        System.setIn(arrayInputStream);
        Main.main(null);
        String EXPECTED = "Enter array size (odd number): Enter the array:\nPrint in spiral order in file (F) or screen (S)? " +
                "Elements in spiral order: 5 2 1 4 7 8 9 6 3 ";
        assertEquals(EXPECTED, arrayOutputStream.toString());
    }

    @Test
    public void mainFile() throws FileNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream("1\n1\nF\n".getBytes());
        System.setOut(new PrintStream(arrayOutputStream));
        System.setIn(arrayInputStream);
        Main.main(null);
        String EXPECTED = "Elements in spiral order: 1 ";
        Scanner file = new Scanner(new File("output.txt"));
        file.useDelimiter("\n");
        assertEquals(EXPECTED, file.next());
    }
}