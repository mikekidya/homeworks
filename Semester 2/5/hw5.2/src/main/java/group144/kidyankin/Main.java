package group144.kidyankin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));

        primaryStage.setTitle("Simple Calculator");
        primaryStage.minHeightProperty().setValue(100);
        primaryStage.minWidthProperty().setValue(500);
        primaryStage.setScene(new Scene(root, 500, 100));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
