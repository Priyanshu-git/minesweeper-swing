package listeners;

import entity.Coordinate;
import frame.Base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BoxListener extends Base implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton b = (JButton) e.getSource();
        String[] in = b.getName().split(" ");
        Coordinate clickedBox = new Coordinate(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        int i = clickedBox.getI();
        int j = clickedBox.getJ();


        if (SwingUtilities.isLeftMouseButton(e)) {
            try {
                grid[i][j].setClicked(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
        if (SwingUtilities.isRightMouseButton(e)) {
            try {
                grid[i][j].setFlagged(!grid[i][j].isFlagged());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        grid[i][j].button.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
