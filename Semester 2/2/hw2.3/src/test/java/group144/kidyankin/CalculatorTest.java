package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void calculate() {
        assertEquals(1, (new Calculator()).calculate("2 2 + 2 * 4 / 1 -"));
    }

    @Test
    public void calculateNegatives() {
        assertEquals(9, (new Calculator()).calculate("2 -1 + -2 - -3 * -1 /"));
    }

    @Test
    public void calculateBigNumbers() {
        assertEquals(-5, (new Calculator()).calculate("22 33 + -11 /"));
    }
}