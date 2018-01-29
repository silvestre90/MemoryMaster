import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.GameModel;

import java.net.URL;
import java.util.ResourceBundle;

public class startPageController implements Initializable {

    Stage window;
    Scene scene;

    @FXML
    private Button btnStart;



    @FXML
    private void startGame(ActionEvent event) throws Exception{

        Stage primaryStage;
        primaryStage = (Stage) this.btnStart.getScene().getWindow();
        primaryStage.close();

        Stage window = new Stage();
        GameModel model = new GameModel();
        scene = new Scene(model.createContent(window));
        window.setScene(scene);
        window.setTitle("Memory Master");
        window.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
