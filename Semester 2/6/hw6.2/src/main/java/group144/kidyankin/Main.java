package group144.kidyankin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main class for Application */
public class Main extends Application {

    /**
     * The main entry point for JavaFX application.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));

        primaryStage.setTitle("Tick-tac-toe");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 250, 350));
        primaryStage.show();
    }

    /** The entry point for application */
    public static void main(String[] args) {
        launch(args);
    }
}
