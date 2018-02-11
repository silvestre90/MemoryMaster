package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class BestResultsController implements Initializable {

@FXML
private Label lblActualLevel;

@FXML
private Button btnBackToTitle;

@FXML
private Label lblName1, lblName2, lblName3, lblName4, lblName5, lblName6, lblName7, lblName8, lblName9, lblName10;

@FXML
private Label lblResult1, lblResult2, lblResult3, lblResult4, lblResult5, lblResult6, lblResult7, lblResult8, lblResult9, lblResult10;

Connection connection;


private List<Label> lblNameList = new ArrayList<>();
private List<Label> lblResultList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> results = setResults();
        this.lblActualLevel.setText(StartPageController.chosenLevel);

        if(!(results == null)){
            fillLists();
            fillRecordsWithResults(results);
        }
    }

    @FXML
    private void goBackToTitle() throws IOException {

        Stage window = (Stage)this.btnBackToTitle.getScene().getWindow();
        window.close();

        Parent root = (Parent) FXMLLoader.load(getClass().getResource("StartPage.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memory Master");
        primaryStage.show();
    }


    public void fillRecordsWithResults(List<String> resultsList) {

        int i = 1;
        for (String r : resultsList) {
            lblNameList.get(i - 1).setText(r.split(",")[0]);
            lblResultList.get(i - 1).setText(r.split(",")[1]);
            i++;
        }
    }

        private List<String> setResults () {

            PreparedStatement pr = null;
            List<String> results = null;
            ResultSet rs = null;

            String tableName = StartPageController.chosenLevel;

            String sql = "SELECT * FROM " + tableName + " ORDER BY result ASC LIMIT 10";

            try {
                this.connection = DBConnection.getConnection();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (this.connection == null) {
                System.exit(1);
            }

            try {
                pr = this.connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                rs = pr.executeQuery();

                if (rs.isBeforeFirst()) {
                    results = new ArrayList<String>();
                    while (rs.next()) {

                        results.add(rs.getString(1) + "," + rs.getString(2));
                    }

                    return results;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    private void fillLists(){
        lblNameList.add(lblName1);
        lblNameList.add(lblName2);
        lblNameList.add(lblName3);
        lblNameList.add(lblName4);
        lblNameList.add(lblName5);
        lblNameList.add(lblName6);
        lblNameList.add(lblName7);
        lblNameList.add(lblName8);
        lblNameList.add(lblName9);
        lblNameList.add(lblName10);

        lblResultList.add(lblResult1);
        lblResultList.add(lblResult2);
        lblResultList.add(lblResult3);
        lblResultList.add(lblResult4);
        lblResultList.add(lblResult5);
        lblResultList.add(lblResult6);
        lblResultList.add(lblResult7);
        lblResultList.add(lblResult8);
        lblResultList.add(lblResult9);
        lblResultList.add(lblResult10);

    }
}
