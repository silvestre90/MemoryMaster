package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class GameWonController implements Initializable {

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnRestart;

    @FXML
    private Button btnTitle;

    @FXML
    private Button btnSaveResult;

    @FXML
    private Button btnExit;

    Scene scene;


    @FXML
    private void backToTitle(ActionEvent event) throws Exception {
        closeCurrentWindow();


        Parent root;
        root = (Parent) FXMLLoader.load(getClass().getResource("startPage.fxml"));
        Stage startWindow = new Stage();
        Scene sceneStart = new Scene(root);
        startWindow.setScene(sceneStart);
        startWindow.setTitle("Memory Master");
        startWindow.show();
    }

    private void closeCurrentWindow(){

        Stage window;
        window = (Stage)this.btnRestart.getScene().getWindow();
        window.close();
    }

    @FXML
    private void restartGame(ActionEvent event) throws Exception {

        closeCurrentWindow();
        GameModel.numberOfClicks = 0;

        Stage window = new Stage();
        GameModel model = new GameModel();
        scene = new Scene(model.createContent(window));
        window.setScene(scene);
        window.setTitle("Memory Master");
        window.show();
    }

    @FXML
    private void closeGame(ActionEvent event) {

        closeCurrentWindow();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblInfo.setText("Congratulations, you won with final result:" + GameModel.numberOfClicks);
    }
}
