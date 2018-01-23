package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

Scene scene;
GameModel model;
Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        model = new GameModel();
        scene = new Scene(model.createContent(primaryStage));
        window.setScene(scene);
        window.setTitle("Memory Master");
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
