package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class ResultSaveController implements Initializable {

    @FXML
    private Button btnOK;
    @FXML
    private TextField txtName;
    @FXML
    private Label lblResult;
    @FXML
    private Label lblAlert;

    private static final String ALERT_TEXT = "Field Name is empty!!!";



    Connection connection;
    private String dbTableName = StartPageController.chosenLevel;

    @FXML
    private void addNewResults() throws IOException {

//        System.out.println("Text: " + this.txtName.getText().equals(""));

        if(this.txtName.getText().equals("")){
            this.lblAlert.setText(ALERT_TEXT);
        }else {
            executeSQLInsert(this.txtName.getText());

            Parent root = (Parent) FXMLLoader.load(getClass().getResource("StartPage.fxml"));

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Memory Master");
            primaryStage.show();
        }

        Stage window = (Stage)this.btnOK.getScene().getWindow();
        window.close();


    }

    private void executeSQLInsert(String playerName){
        Statement stmt = null;
        int finalResult = 2*GameModel.numberOfClicks + GameModel.finalTime;
        String sql = "INSERT INTO " + dbTableName + " VALUES ('" + playerName + "','" + String.valueOf(finalResult) + "')";
        System.out.println(sql);
        try {
            this.connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.lblResult.setText(String.valueOf(2*GameModel.numberOfClicks + GameModel.finalTime));


    }
}
