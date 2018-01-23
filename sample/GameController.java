package sample;

import javafx.stage.Stage;

public class GameController {


    public void restartGame(Stage window) throws Exception {
        window.close();
        Main mainGame = new Main();
        Stage primaryStage = new Stage();
        GameModel.numberOfClicks = 0;
        mainGame.start(primaryStage);
    }
}
