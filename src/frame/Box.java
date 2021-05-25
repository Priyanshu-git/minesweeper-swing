package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Box {
    private boolean bomb, flagged, clicked;
    private int value;
    String imagePath;

    public Box() {
        setBomb(false);
        setClicked(false);
        setFlagged(false);
        setValue(0);
    }

    ImageIcon setImage() throws IOException {
        String path = "/res/img/0.png";
        InputStream imgStream = Frame.class.getResourceAsStream(path);
        BufferedImage myImg = ImageIO.read(imgStream);
        Image image = myImg.getScaledInstance(50, 50, Image.SCALE_FAST);
        ImageIcon ii = new ImageIcon(image);
        JLabel bgImage = new JLabel(ii);
        bgImage.setSize(10, 10);
        return ii;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
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
        String p = "";
        if (clicked){
            p=String.valueOf(value);
        }else{
            if (isFlagged()){
                p="flagged";
            }else {
                p="facingDown";
            }
        }
        this.imagePath = p + ".png";
    }

}
