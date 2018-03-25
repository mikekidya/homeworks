package group144.kidyankin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller class for UI FXML file
 */
public class Controller {

    /** Calculator that performs all calculating actions */
    private Calculator calculator = new Calculator();

    /** Array of number buttons */
    private Button[] buttons;

    /**
     * Changes the value on text field
     */
    private void updateField() {
        textField.textProperty().setValue(calculator.display());
    }

    /**
     * Action when number button is pressed
     *
     * @param actionEvent event with information about source
     */
    public void numberPress(ActionEvent actionEvent) {
        for (int i = 0; i < 10; i++) {
            if (actionEvent.getSource().equals(buttons[i])) {
                calculator.numberPressed(i);
            }
        }
        updateField();
    }

    /**
     * Action when operation button is pressed
     *
     * @param actionEvent event with information about source
     */
    public void operationPress(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(buttonAdd)) {
            calculator.operationPressed(Calculator.Operation.ADD);
        } else if (actionEvent.getSource().equals(buttonSub)) {
            calculator.operationPressed(Calculator.Operation.SUB);
        } else if (actionEvent.getSource().equals(buttonMult)) {
            calculator.operationPressed(Calculator.Operation.MULT);
        } else {
            calculator.operationPressed(Calculator.Operation.DIV);
        }
        updateField();
    }

    /**
     * Action when equals button is pressed
     */
    public void equalsPress() {
        calculator.equalsPressed();
        updateField();
    }

    /**
     * Initialization function
     */
    public void initialize() {
        buttons = new Button[]{button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};
    }

    /**
     * Action when plus-minus button is pressed
     */
    public void pmPress() {
        calculator.pmPressed();
        updateField();
    }

    /**
     * Action when C button is pressed
     */
    public void clearPress() {
        calculator.clearPressed();
        updateField();
    }

    /**
     * Action when AC button is pressed
     */
    public void allClearPress() {
        calculator.allClearPressed();
        updateField();
    }

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonSub;

    @FXML
    private Button buttonMult;

    @FXML
    private TextField textField;
}
