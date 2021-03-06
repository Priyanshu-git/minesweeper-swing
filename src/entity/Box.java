package entity;

import frame.ImageManager;

import javax.swing.*;
import java.io.IOException;

public class Box {
    private boolean bomb, flagged, clicked;
    private int value;
    private String imagePath;
    public JButton button;

    public Box(String s) throws IOException {
        button = new JButton();
        button.setName(s);
        setBomb(false);
        setClicked(false);
        setFlagged(false);
        setValue(0);
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) throws IOException {
        this.bomb = bomb;
        setImagePath();
        setImage();
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) throws IOException {
        this.flagged = flagged;
        setImagePath();
        setImage();
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) throws IOException {
        this.clicked = clicked;
        setImagePath();
        setImage();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setImagePath();
    }

    public String getImagePath() {
        return imagePath;
    }

    private void setImagePath() {
        String p;
        if (clicked) {
            if (isBomb())
                p = "bomb";
            else p = String.valueOf(value);
        } else {
            if (isFlagged()) {
                p = "flagged";
            } else {
                p = "facingDown";
            }
        }
        this.imagePath = p + ".png";
    }

    public void setImage() throws IOException {
        ImageManager manager = new ImageManager();
        button.setIcon(manager.getImage(getImagePath()));
    }

}
