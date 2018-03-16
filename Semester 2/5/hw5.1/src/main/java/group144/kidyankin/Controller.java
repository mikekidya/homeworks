package group144.kidyankin;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

/** Controller class for UI.fxml elements */
public class Controller {

    @FXML
    private Slider slider;

    @FXML
    private ProgressBar progressBar;

    /**
     * Initialization method
     *
     * Sets the action when slider's value changing
     */
    public void initialize() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> progressBar.progressProperty().setValue(newValue));
    }

}
