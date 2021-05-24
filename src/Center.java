import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

public class Center {

    private int screenWidth,screenHeight;
    private int r1 =2, r2 =3;

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
        System.out.print("screenWidth = " + screenWidth+"\t");
        System.out.println("screenHeight = " + screenHeight);
    }

    void setFrameRatio(double x,double y){
        Pair<Integer, Integer> pair=Ratio.getRatio(x,y);
        r1=pair.getKey();
        r2=pair.getValue();
    }

    void setFrameOnCenter(JFrame frame){
        Dimension c= getCenterCoordinates();
        Dimension size=frameSize();

        System.out.print("[Center.setFrameOnCenter]\t");
        System.out.printf("X Y W H\t%d %d %d %d ",c.width,c.height,size.width,size.height);

        frame.setBounds(c.width,c.height,size.width,size.height);
    }

    private Dimension frameSize() {
        int factor=(int)(screenHeight*0.65);
        factor=factor/ r2;
        return new Dimension(r1 *factor, r2 *factor);
    }

    private Dimension getCenterCoordinates(){
        Dimension f=frameSize();
        int x=screenWidth/2 - f.width/2;
        int y=screenHeight/2 - f.height/2;
        return new Dimension(x,y);
    }


}
