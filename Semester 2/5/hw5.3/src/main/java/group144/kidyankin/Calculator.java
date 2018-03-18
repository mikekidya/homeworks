package group144.kidyankin;


/**
 * Calculator class to perform all calculator logic.
 */
public class Calculator {

    /** Enum enumerating arithmetical operators */
    public enum Operation {ADD, SUB, MULT, DIV}

    private enum State {SHOW_RESULT, WAIT_FOR_NEXT, FIRST_OPERAND_INPUT, SECOND_OPERAND_INPUT}

    private double firstOperand = 0.0;
    private double secondOperand = 0.0;
    private Operation operation = Operation.ADD;
    private State state = State.SHOW_RESULT;

    /**
     * Calculate firstOperand and secondOperand with operation and places result into firstOperand
     */
    private void operationPerform() {
        switch (operation) {
            case ADD:
                firstOperand = firstOperand + secondOperand;
                break;
            case SUB:
                firstOperand = firstOperand - secondOperand;
                break;
            case MULT:
                firstOperand = firstOperand * secondOperand;
                break;
            case DIV:
                firstOperand = firstOperand / secondOperand;
                break;
        }
    }

    /**
     * Gives the value need to be printed on calculator screen.
     *
     * @return String value need to be printed
     */
    public String display() {
        String result;
        if (state == State.SHOW_RESULT || state == State.FIRST_OPERAND_INPUT) {
            result = Double.toString(firstOperand);
        } else {
            result = Double.toString(secondOperand);
        }
        if (result.endsWith(".0")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    /**
     * Action when number key pressed
     *
     * @param number the number was pressed
     */
    public void numberPressed(int number) {
        switch (state) {
            case SHOW_RESULT:
                firstOperand = number;
                state = State.FIRST_OPERAND_INPUT;
                break;
            case WAIT_FOR_NEXT:
                secondOperand = number;
                state = State.SECOND_OPERAND_INPUT;
                break;
            case FIRST_OPERAND_INPUT:
                firstOperand = (Math.abs(firstOperand) * 10 + number) * (firstOperand < 0 ? -1 : 1);
                break;
            case SECOND_OPERAND_INPUT:
                secondOperand = (Math.abs(secondOperand) * 10 + number) * (secondOperand < 0 ? -1 : 1);
                break;
        }
    }

    /**
     * Action when operation key pressed
     *
     * @param operation the operation was pressed
     */
    public void operationPressed(Operation operation) {
        switch (state) {
            case SHOW_RESULT:
            case FIRST_OPERAND_INPUT:
                secondOperand = firstOperand;
                break;
            case SECOND_OPERAND_INPUT:
                operationPerform();
                secondOperand = firstOperand;
                break;
        }
        this.operation = operation;
        state = State.WAIT_FOR_NEXT;
    }

    /**
     * Action when equals key pressed
     */
    public void equalsPressed() {
        if (state == State.WAIT_FOR_NEXT || state == State.SECOND_OPERAND_INPUT) {
            operationPerform();
            state = State.SHOW_RESULT;
        }
    }

    /**
     * Action when clear key pressed
     */
    public void clearPressed() {
        switch (state) {
            case SHOW_RESULT:
            case FIRST_OPERAND_INPUT:
                firstOperand = 0;
                break;
            case SECOND_OPERAND_INPUT:
            case WAIT_FOR_NEXT:
                secondOperand = 0;
                break;
        }
    }

    /**
     * Action when all clear key pressed
     */
    public void allClearPressed() {
        firstOperand = 0;
        secondOperand = 0;
        state = State.SHOW_RESULT;
    }


    /**
     * Action when plus-minus key pressed
     */
    public void pmPressed() {
        switch (state) {
            case SHOW_RESULT:
                firstOperand = -firstOperand;
                state = State.FIRST_OPERAND_INPUT;
                break;
            case WAIT_FOR_NEXT:
                secondOperand = -firstOperand;
                state = State.SECOND_OPERAND_INPUT;
                break;
            case FIRST_OPERAND_INPUT:
                firstOperand = -firstOperand;
                break;
            case SECOND_OPERAND_INPUT:
                secondOperand = -secondOperand;
                break;
        }
    }

}
