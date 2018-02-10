package game;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    Stage window;
    Scene scene;

    public static String chosenLevel;
    @FXML
    private Button btnStart;

    @FXML
    private Button btnExit;

    @FXML
    private ComboBox<Level> cmbDifficulty;


    @FXML
    private void exitGame(ActionEvent event) throws Exception{
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        Stage primaryStage;
        primaryStage = (Stage) this.btnStart.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void startGame(ActionEvent event) throws Exception{

        closeCurrentWindow();

        Stage window = new Stage();
        GameModel model = new GameModel();
        Object objectLevelValue = null;
        objectLevelValue = this.cmbDifficulty.getValue();

        if(!(objectLevelValue == null)){
            chosenLevel = ((Level) this.cmbDifficulty.getValue()).toString();
            scene = new Scene(model.createContent(window, chosenLevel));
            window.setScene(scene);
            window.setTitle("Memory Master");
            window.show();
        }

    }

//    !!!!!!!!!!!!!!!!!!!!!!!!!
//    @FXML
//    public void showBestResults(ActionEvent event) throws Exception{
//
//        PreparedStatement pr = null;
//        ResultSet rs = null;
//
//
//
//
//            levelValue = this.cmbDifficulty.getSelectionModel().getSelectedItem().toString();
//
//            Stage stage = (Stage) this.btnExit.getScene().getWindow();
//            stage.close();
//            showBestResultsStage(new Stage(), rs);
//
//
//
//
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cmbDifficulty.setItems(FXCollections.observableArrayList(Level.values()));
    }
}
