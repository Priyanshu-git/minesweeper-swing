import entity.Coordinate;
import frame.Base;

import javax.swing.*;
import java.awt.*;

public class Center extends Base {

    private final int screenWidth;
    private final int screenHeight;
    private int r1 = 2, r2 = 3;

    public Center(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        printScreenRes();
    }

    public Center(Dimension d) {
        this.screenWidth = d.width;
        this.screenHeight = d.height;
        printScreenRes();
    }

    private void printScreenRes() {
        System.out.print("screenWidth = " + screenWidth + "\t");
        System.out.println("screenHeight = " + screenHeight);
    }

    void setFrameRatio(double x, double y) {
        Coordinate pair = Ratio.getRatio(x, y);
        r1 = pair.getI();
        r2 = pair.getJ();
    }

    void setFrameOnCenter(JFrame frame) {
        Dimension c = getCenterCoordinates();
        Dimension size = frameSize();

        System.out.print("[Center.setFrameOnCenter]\t");
        System.out.printf("X Y W H\t%d %d %d %d ", c.width, c.height, size.width, size.height);

        frame.setBounds(c.width, c.height, size.width, size.height);
    }

    private Dimension frameSize() {
        return new Dimension(boxSize*(col+2),boxSize*(row+5));
    }

    private Dimension getCenterCoordinates() {
        Dimension f = frameSize();
        int x = screenWidth / 2 - f.width / 2;
        int y = screenHeight / 2 - f.height / 2;
        return new Dimension(x, y);
    }
}
