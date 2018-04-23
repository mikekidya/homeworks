package group144.kidyankin;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/** Controller class for UI FXML file */
public class Controller {

    /** Array contains all buttons contains X or O */
    private Button[][] buttonArray;

    /** Objects realizing all game  */
    private Tictactoe game;

    /** Initialization method */
    public void initialize() {
        buttonArray = new Button[][]{{button00, button01, button02},
                                     {button10, button11, button12},
                                     {button20, button21, button22}};
        newGame();
    }

    /**
     * Method for action when user wants to put X or O by a button click
     *
     * @param event action event
     */
    @FXML
    private void nextTurn(ActionEvent event) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (event.getSource().equals(buttonArray[row][column])) {
                    buttonArray[row][column].setText(game.nextTurn(row, column));
                }
            }
        }
        updateLabel();
        if (game.isFinished()) {
            endGame();
        }
    }

    /**
     * Method for action when user clicks New Game Button
     *
     * @param event action event
     */
    @FXML
    private void newGameButtonEvent(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Start new game");
        confirmation.setContentText("Click OK to start new game");
        confirmation.showAndWait();
        if (confirmation.getResult() == ButtonType.OK) {
            newGame();
        }
    }

    /** Starts a new game */
    private void newGame() {
        game = new Tictactoe();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonArray[i][j].setText("");
            }
        }
        updateLabel();
    }

    /** Updates the text on label showing current player */
    private void updateLabel() {
        nextTurnLabel.setText("Now " + (game.getPlayer() == Tictactoe.Player.X ? "X" : "O") + " turn");
    }

    /** Ends current game and starts new one */
    private void endGame() {
        Alert finishMessage = new Alert(Alert.AlertType.INFORMATION);
        finishMessage.setTitle("Game over");
        if (game.hasWinner()) {
            finishMessage.setContentText("Player " + (game.getPlayer() == Tictactoe.Player.X ? "O" : "X") + " wins!");
        } else {
            finishMessage.setContentText("Draw!");
        }

        finishMessage.showAndWait();
        newGame();
    }

    @FXML
    private Button button00;

    @FXML
    private Button button01;

    @FXML
    private Button button02;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

    @FXML
    private Button button20;

    @FXML
    private Button button21;

    @FXML
    private Button button22;

    @FXML
    private Label nextTurnLabel;

}
