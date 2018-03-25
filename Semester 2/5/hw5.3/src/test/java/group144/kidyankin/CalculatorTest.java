package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void numberPressedTest() {
        Calculator calculator = new Calculator();
        calculator.numberPressed(0);
        calculator.numberPressed(1);
        calculator.numberPressed(4);
        calculator.numberPressed(0);
        assertEquals("140", calculator.display());
    }

    @Test
    public void operationPressedTest() {
        Calculator calculator = new Calculator();
        calculator.numberPressed(1);
        calculator.numberPressed(2);
        calculator.operationPressed(Calculator.Operation.MULT);
        calculator.numberPressed(6);
        calculator.operationPressed(Calculator.Operation.ADD);
        assertEquals("72", calculator.display());
        calculator.numberPressed(8);
        calculator.operationPressed(Calculator.Operation.DIV);
        assertEquals("80", calculator.display());
        calculator.numberPressed(1);
        calculator.numberPressed(0);
        calculator.operationPressed(Calculator.Operation.SUB);
        assertEquals("8", calculator.display());
    }

    @Test
    public void equalsPressedTest() {
        Calculator calculator = new Calculator();
        calculator.equalsPressed();
        assertEquals("0", calculator.display());
        calculator.numberPressed(1);
        calculator.numberPressed(2);
        calculator.equalsPressed();
        assertEquals("12", calculator.display());
        calculator.operationPressed(Calculator.Operation.SUB);
        calculator.numberPressed(8);
        calculator.equalsPressed();
        assertEquals("4", calculator.display());
        calculator.numberPressed(8);
        assertEquals("8", calculator.display());
    }

    @Test
    public void clearPressedTest() {
        Calculator calculator = new Calculator();
        calculator.clearPressed();
        assertEquals("0", calculator.display());
        calculator.numberPressed(1);
        calculator.numberPressed(2);
        calculator.operationPressed(Calculator.Operation.ADD);
        calculator.numberPressed(4);
        calculator.clearPressed();
        calculator.numberPressed(3);
        calculator.equalsPressed();
        assertEquals("15", calculator.display());
    }

    @Test
    public void allClearPressedTest() {
        Calculator calculator = new Calculator();
        calculator.numberPressed(1);
        calculator.numberPressed(2);
        calculator.operationPressed(Calculator.Operation.MULT);
        calculator.numberPressed(3);
        calculator.allClearPressed();
        assertEquals("0", calculator.display());
        calculator.numberPressed(4);
        calculator.operationPressed(Calculator.Operation.ADD);
        assertEquals("4", calculator.display());
    }

    @Test
    public void pmPressedTest() {
        Calculator calculator = new Calculator();
        calculator.numberPressed(1);
        calculator.pmPressed();
        assertEquals("-1", calculator.display());
        calculator.numberPressed(2);
        assertEquals("-12", calculator.display());
        calculator.pmPressed();
        assertEquals("12", calculator.display());
    }
}