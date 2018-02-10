package game;

import javafx.scene.control.Button;

public class ImageButton extends Button {

    private boolean isRecognizedByPlayer;
    private boolean isClicked;
    private String imageName;

    public boolean isRecognizedByPlayer() {
        return isRecognizedByPlayer;
    }

    public void setRecognizedByPlayer(boolean recognizedByPlayer) {
        isRecognizedByPlayer = recognizedByPlayer;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ImageButton(){
        this.setClicked(false);
        this.setRecognizedByPlayer(false);
    }
}
