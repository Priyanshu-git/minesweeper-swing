package frame;

import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FrameManager extends Base{
    static JPanel gridPanel;

    public FrameManager() {
        gridPanel=new JPanel();
    }

    public void setFrame(JFrame frame) throws IOException {
        System.out.println("[FrameManager.setFrame]");
        JButton newGame = new JButton("NEW GAME");
        frame.add(newGame);
        newGame.setBounds((frame.getWidth() / 2) - 75, 30, 150, 50);
        newGame.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        newGame.addActionListener(new NewGameListener());

        frame.setBackground(Color.GRAY);
        Rectangle r = frame.getBounds();
        frame.add(gridPanel);

        gridPanel.setBounds((int) (0.06 * r.width), (int) (0.14 * r.height), (col + 1) * boxSize, (row + 1) * boxSize);
        gridPanel.setBackground(Color.RED);
        gridPanel.repaint();

        new Grid().setGrid(gridPanel);

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}
