package game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
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
    private void addNewResults(){

//        System.out.println("Text: " + this.txtName.getText().equals(""));

        if(this.txtName.getText().equals("")){
            this.lblAlert.setText(ALERT_TEXT);
        }else {
            executeSQLInsert(this.txtName.getText());
        }

        Stage window = (Stage)this.btnOK.getScene().getWindow();
        window.close();
    }

    private void executeSQLInsert(String playerName){
        Statement stmt = null;

        String sql = "INSERT INTO " + dbTableName + " VALUES ('" + playerName + "','" + String.valueOf(GameModel.numberOfClicks) + "')";
        System.out.println(sql);
        try {
            this.connection = DBConnection.getConnection();
            System.out.println("Great success!!!");
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lblResult.setText(String.valueOf(GameModel.numberOfClicks));
    }
}
