package frame;

import entity.Box;
import listeners.BoxListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Grid extends Base{


    public void setGrid(JPanel gridPanel) throws IOException {
        grid = new entity.Box[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new Box(i+" "+j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j].button.setSize(boxSize, boxSize);
                gridPanel.add(grid[i][j].button);
                grid[i][j].button.repaint();
                grid[i][j].button.addMouseListener(new BoxListener());
            }
        }

        GridLayout layout = new GridLayout(row, col, 0, 0);
        gridPanel.setLayout(layout);
        gridPanel.updateUI();
        gridPanel.repaint();
    }
    public static Box[][] getGrid(){
        return grid;
    }
}
