package group144.kidyankin;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller for UI FXML file
 */
public class Controller {

    @FXML
    private TextField resultTextField;

    @FXML
    private ChoiceBox<String> operationBox;

    @FXML
    private Spinner<Integer> firstOperandSpinner;

    @FXML
    private Spinner<Integer> secondOperandSpinner;

    private void calculate() {
        double result = 0;
        int firstOperand = firstOperandSpinner.getValue();
        int secondOperand = secondOperandSpinner.getValue();
        switch (operationBox.getValue()) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                result = (double) firstOperand / secondOperand;
        }
        resultTextField.textProperty().setValue(Double.toString(result));
    }

    /**
     * StringConverter class with additional NumberFormatException handling and Alert message throwing.
     */
    private class StringConverterWithAlert extends javafx.util.StringConverter<Integer> {
        @Override
        public String toString(Integer object) {
            return Integer.toString(object);
        }

        @Override
        public Integer fromString(String string) {
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong operand input");
                alert.setContentText("Only integers from -100 to 100 are allowed.");
                alert.show();
                return 0;
            }
        }
    }

    /**
     * Initialization method
     */
    public void initialize() {
        operationBox.getItems().addAll("+", "-", "*", "/");
        operationBox.valueProperty().setValue("+");

        operationBox.valueProperty().addListener((observable, oldValue, newValue) -> calculate());

        firstOperandSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0, 1));
        secondOperandSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0, 1));

        firstOperandSpinner.getValueFactory().setConverter(new StringConverterWithAlert());
        secondOperandSpinner.getValueFactory().setConverter(new StringConverterWithAlert());

        firstOperandSpinner.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
        secondOperandSpinner.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
    }
}
