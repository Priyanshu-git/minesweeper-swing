package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FrameManager {
    public static final int GAME=1;
    private final int boxSize=45;
    private final int col =9;
    private final int row =11;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setFrame(int MODE, JFrame frame) throws IOException {
        JPanel gridPanel=new JPanel();
        frame.setBackground(Color.GRAY);
        Rectangle r= frame.getBounds();
        frame.add(gridPanel);

        gridPanel.setBounds((int)(0.06*r.width),(int)(0.14*r.height),(col +1)*boxSize,(row +1)*boxSize);
        gridPanel.setBackground(Color.RED);
        gridPanel.repaint();

        JButton[][] btn=new JButton[row][col];

        ImageIcon imageIcon=setImage();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                btn[i][j]=new JButton(imageIcon);
                btn[i][j].setBackground(Color.GRAY);
                btn[i][j].setSize(boxSize,boxSize);
                btn[i][j].setVisible(true);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gridPanel.add(btn[i][j]);
                btn[i][j].repaint();
            }
        }

        GridLayout gl=new GridLayout(row,col,0,0);
        gridPanel.setLayout(gl);
        gridPanel.updateUI();
        gridPanel.repaint();
    }

     ImageIcon setImage() throws IOException {
        String path= "/res/img/facingDown.png";
        InputStream imgStream = Frame.class.getResourceAsStream(path);
         assert imgStream != null;
         BufferedImage myImg = ImageIO.read(imgStream);
        Image image=myImg.getScaledInstance(50,50,Image.SCALE_FAST);
        ImageIcon ii = new ImageIcon(image);
        JLabel bgImage = new JLabel(ii);
        bgImage.setSize(10,10);
        return ii;
    }
}
