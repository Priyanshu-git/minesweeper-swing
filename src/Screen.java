import javax.swing.*;
import java.awt.*;

public class Screen {
    private JFrame frame;
    private Container pane;
    private int minHeight = 768, minWidth = 1024;
    private Dimension screenRes = new Dimension();

    public JFrame getFrame() {
        return frame;
    }

    Screen() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screenRes.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenRes.height = Toolkit.getDefaultToolkit().getScreenSize().height;

        Center obj = new Center(screenRes);
        obj.setFrameRatio(3, 4);
        obj.setFrameOnCenter(frame);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        pane = frame.getContentPane();
        frame.setResizable(false);
    }

    void clear() {
        pane.removeAll();
        pane.repaint();
    }
}
