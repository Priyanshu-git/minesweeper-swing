package frame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrameManager {
    public static final int GAME = 1;
    private static final int boxSize = 45;
    private final int col = 9;
    private final int row = 11;

    public void setFrame(int MODE, JFrame frame) throws IOException {
        JPanel gridPanel = new JPanel();
        frame.setBackground(Color.GRAY);
        Rectangle r = frame.getBounds();
        frame.add(gridPanel);

        gridPanel.setBounds((int) (0.06 * r.width), (int) (0.14 * r.height), (col + 1) * boxSize, (row + 1) * boxSize);
        gridPanel.setBackground(Color.RED);
        gridPanel.repaint();

        JButton[][] btn = new JButton[row][col];
        Box[][] box=new Box[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                box[i][j]=new Box();
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

//                btn[i][j].setBackground(Color.GRAY);
                box[i][j].button.setSize(boxSize, boxSize);
                gridPanel.add(box[i][j].button);
                box[i][j].button.repaint();
            }
        }

        GridLayout gl = new GridLayout(row, col, 0, 0);
        gridPanel.setLayout(gl);
        gridPanel.updateUI();
        gridPanel.repaint();
    }

    static public int getBoxSize() {
        return boxSize;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
