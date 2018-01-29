package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class GameModel {

    ImageButton button1, button2;
    GridPane root;
    Stage window;

    public static int numberOfClicks = 0;
    GameController gameController = new GameController();
    private static final int X_GRID_SIZE = 3;
    private static final int Y_GRID_SIZE = 4;
    private static final int NUMBER_OF_PICTURES = (X_GRID_SIZE*Y_GRID_SIZE)/2;
    private static int remainingFields = X_GRID_SIZE*Y_GRID_SIZE;


    Map<String,Integer> imageMap = new HashMap<>();
    private int globalCounter = 0;

    public Parent createContent(Stage primaryStage) throws Exception {

        window = primaryStage;
        fillHashMap();
        root = new GridPane();

        root.setPrefSize(620,620);
        primaryStage.setTitle("Memory Master");
        primaryStage.setResizable(false);
        root.setVgap(8);
        root.setHgap(4);

        for (int i = 0; i < X_GRID_SIZE ; i++) {
            for(int j = 0; j<Y_GRID_SIZE; j++) {
                ImageButton button = new ImageButton();

                button.setPrefHeight(100);
                button.setPrefWidth(100);


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
                    System.out.println("jestem tu");
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
