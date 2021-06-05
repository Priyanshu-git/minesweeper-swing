import frame.Base;

import javax.swing.*;
import java.awt.*;

public class Screen extends Base {
    private final Container pane;

    public JFrame getFrame() {
        return frame;
    }

    Screen() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenRes = new Dimension();
        screenRes.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenRes.height = Toolkit.getDefaultToolkit().getScreenSize().height;

        Center obj = new Center(screenRes);
        obj.setFrameRatio(3, 4);
        obj.setFrameOnCenter(frame);

        frame.setLayout(null);
        frame.setVisible(true);
//        frame.setAlwaysOnTop(true);

        pane = frame.getContentPane();
        frame.setResizable(false);
    }

    void clear() {
        pane.removeAll();
        pane.repaint();
    }
}
