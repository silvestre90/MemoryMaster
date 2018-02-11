package game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GameModel {

    ImageButton button1, button2;
    GridPane root;
    Stage window;

    public static int numberOfClicks = 0;
    public static int finalTime;
    GameController gameController = new GameController();
    private static int X_GRID_SIZE;
    private static int Y_GRID_SIZE;
    private static int NUMBER_OF_PICTURES;
    private static int remainingFields;
    private String chosenLevel;
    public Date startTime, endTime;

    Map<String,Integer> imageMap = new HashMap<>();
    private int globalCounter = 0;

    private void setGridSize(){
        if(chosenLevel.equals("EASY")){
            X_GRID_SIZE = 4;
            Y_GRID_SIZE =4;

        }else if(chosenLevel.equals("MEDIUM")){
            X_GRID_SIZE = 5;
            Y_GRID_SIZE =6;
        }else{
            X_GRID_SIZE = 6;
            Y_GRID_SIZE =6;
        }

        NUMBER_OF_PICTURES = (X_GRID_SIZE*Y_GRID_SIZE)/2;
        remainingFields = X_GRID_SIZE*Y_GRID_SIZE;
    }

    public Parent createContent(Stage primaryStage, String chosenLevel) throws Exception {
        this.chosenLevel = chosenLevel;
        setGridSize();
        window = primaryStage;
        fillHashMap();
        root = new GridPane();

        root.setPrefSize(97*X_GRID_SIZE,99*Y_GRID_SIZE);
        primaryStage.setTitle("Memory Master");
        primaryStage.setResizable(false);
        root.setVgap(8);
        root.setHgap(4);

        for (int i = 0; i < X_GRID_SIZE ; i++) {
            for(int j = 0; j<Y_GRID_SIZE; j++) {
                ImageButton button = new ImageButton();

                button.setPrefHeight(95);
                button.setPrefWidth(95);


                button.setId(String.valueOf(i) + String.valueOf(j));
                button.setImageName(assignImage());
                button.setOnAction( e ->{

                        if(!button.isRecognizedByPlayer() && !button.isClicked()){
                            globalCounter++;
                            Image image;
                            numberOfClicks++;
                            System.out.println(numberOfClicks);
                            if(globalCounter == 1) {
                                image = new Image(getClass().getResourceAsStream("resources/" + button.getImageName()));
                                button.setGraphic(new ImageView(image));
                                button.setClicked(true);
                                button1 = button;
                            }else if(globalCounter == 2){
                                    image = new Image(getClass().getResourceAsStream("resources/" + button.getImageName()));
                                    button.setGraphic(new ImageView(image));
                                    button.setClicked(true);
                                    button2 = button;
                                    checkIfCorrect(button1, button2);


                            }
                        }
                });

                root.add(button, i, j, 1, 1);

            }
        }

        startTime = new Date();
        return root;
    }

    private void checkIfCorrect(ImageButton button1, ImageButton button2){



        if(button1.getImageName().equals(button2.getImageName())){
            button1.setRecognizedByPlayer(true);
            button2.setRecognizedByPlayer(true);
            globalCounter = 0;
            remainingFields = remainingFields-2;
            System.out.println("Remaining fields:" + remainingFields);

            if(remainingFields == 0){
                remainingFields = X_GRID_SIZE*Y_GRID_SIZE;

                window.close();
                try {
                    endTime = new Date();
                    long diff = (endTime.getTime() - startTime.getTime())/1000;
                    finalTime = (int)(diff);
                    Parent root;
                    root = (Parent) FXMLLoader.load(getClass().getResource("GameWonWindow.fxml"));
                    Stage gameWonWindow = new Stage();
                    Scene scenegameWon = new Scene(root);
                    gameWonWindow.setScene(scenegameWon);
                    gameWonWindow.setTitle("You won the game");
                    gameWonWindow.show();

//                    gameController.restartGame(window);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            button1.setClicked(false);
            button1.setGraphic(null);
            System.out.println("Id pierwszego z pary: " +  button1.getId());
            this.button1 = button2;
            globalCounter = 1;
        }
    }

    private void fillHashMap(){

        for(int i = 1;i<=NUMBER_OF_PICTURES;i++){
            imageMap.put(String.valueOf(i) + ".png",2);
            System.out.println(imageMap.get(String.valueOf(i)+".png"));
        }

    }

    private String assignImage(){
        boolean flag = false;
        String key;
        do {
            int imageId = (int) (Math.floor(Math.random()*NUMBER_OF_PICTURES)+1);
            System.out.println("image chosen " + imageId);
             key = String.valueOf(imageId) + ".png";
            if(imageMap.get(key) > 0){
                imageMap.put(key, imageMap.get(key) - 1);
                flag = true;
            }

        }while(!flag);


        return key;
    }


}
