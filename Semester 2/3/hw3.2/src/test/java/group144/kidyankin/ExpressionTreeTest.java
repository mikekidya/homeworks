package group144.kidyankin;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ExpressionTreeTest {

    @Test
    public void calculateTest() throws WrongTreeInputException {
        ExpressionTree tree = new ExpressionTree(new Scanner("(+ 2 (/ (* 2 3) 3))"));
        assertEquals(4, tree.calculate());
    }

    @Test
    public void onlyNumberTest() throws WrongTreeInputException {
        ExpressionTree tree = new ExpressionTree(new Scanner("123"));
        assertEquals(123, tree.calculate());
    }

    @Test
    public void negativesTest() throws WrongTreeInputException {
        ExpressionTree tree = new ExpressionTree(new Scanner("(+ -2 (- -4 6))"));
        assertEquals(-12, tree.calculate());
    }

    @Test
    public void printTest() throws WrongTreeInputException {
        ExpressionTree tree = new ExpressionTree(new Scanner("(- (+ 2 4) (* 1 15))"));
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        tree.print(new PrintStream(arrayOutputStream));
        String EXPECTED = "(- (+ 2 4) (* 1 15))";
        assertEquals(EXPECTED, arrayOutputStream.toString());
    }
}