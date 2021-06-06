package listeners;

import entity.Coordinate;
import frame.Base;
import frame.FrameManager;
import frame.Initiate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class BoxListener extends Base implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("[BoxListener.mouseClicked]");
        System.out.println("e = " + e + "\n");

        JButton b = (JButton) e.getSource();
        String[] in = b.getName().split(" ");
        Coordinate clickedBox = new Coordinate(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        int i = clickedBox.getI();
        int j = clickedBox.getJ();

        if (moves == 0 && flags == 0) {
            FrameManager.getNewGameButton().doClick();
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            try {
                grid[i][j].setClicked(true);
                if (moves == 0) {
                    Initiate init = new Initiate();
                    init.exclude(i, j);
                    init.initiate();
                    moves++;
                }
                if (grid[i][j].isBomb()) {
                    explode();
                }
                if (grid[i][j].getValue() == 0) {
                    cascade(i, j);
                    moves++;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
        if (SwingUtilities.isRightMouseButton(e)) {
            try {
                if (grid[i][j].isFlagged()) {
                    flags++;
                } else {
                    if (flags == 0) return;
                    flags--;
                }
                grid[i][j].setFlagged(!grid[i][j].isFlagged());

                System.out.println("Setting flags to " + flags);
                flagsIndicator.setText("FLAGS: " + flags);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        grid[i][j].button.repaint();

        if (flags == 0) check();
    }

    private void cascade(int i, int j) throws IOException {
        neighbours(i - 1, j);
        neighbours(i, j - 1);
        neighbours(i, j + 1);
        neighbours(i + 1, j);
    }

    private void neighbours(int i, int j) throws IOException {
        if (i < 0 || i >= row || j < 0 || j >= col) return;
        if (grid[i][j].isBomb() || grid[i][j].isClicked())
            return;
        grid[i][j].setClicked(true);

        if (grid[i][j].getValue() != 0) {
            click(i + 1, j);
            click(i - 1, j);
            click(i, j - 1);
            click(i, j + 1);
        }

        neighbours(i - 1, j);
        neighbours(i, j - 1);
        neighbours(i, j + 1);
        neighbours(i + 1, j);
    }

    private void click(int i, int j) throws IOException {
        if (i < 0 || i >= row || j < 0 || j >= col) return;
        if (grid[i][j].isBomb()) return;
        grid[i][j].setClicked(true);
    }

    private void explode() {
        for (int ii = 0; ii < row; ii++) {
            for (int jj = 0; jj < col; jj++) {
                try {
                    grid[ii][jj].setClicked(true);
                    grid[ii][jj].button.repaint();

                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
        end("You stepped on a mine. You took "+moves+" clicks");
    }

    private void end(String msg) {
        JDialog dialog = new JDialog(frame, "Game Over");
        JLabel label = new JLabel(msg);
        dialog.setVisible(true);
        dialog.setLayout(new GridBagLayout());

        Rectangle r = frame.getBounds();
        dialog.setBounds(r.x, r.y, r.width / 2 + 50, 150);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        dialog.add(label, c);

        JButton button = new JButton("Close");
        button.addActionListener(e -> System.exit(0));
        button.setSize(50, 30);
        c.insets.top = 20;
        c.gridy = 1;
        dialog.add(button, c);

        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        FrameManager.gridPanel.removeAll();
    }

    void check() {
        Set<Coordinate> set = new Initiate().getBombsList();
        AtomicBoolean flag = new AtomicBoolean(false);
        set.forEach(coordinate -> {
            int i = coordinate.getI();
            int j = coordinate.getJ();
            if (!grid[i][j].isFlagged()) {
                flag.set(true);
            }
        });

        if (flag.get())
            return;

        end("Game Completed. You took "+moves+" clicks");

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
