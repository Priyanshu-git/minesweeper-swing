package frame;

import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrameManager extends Base {
    static JPanel gridPanel;
    static JFrame frame;

    public FrameManager() {
        gridPanel = new JPanel();
    }

    public void setFrame(JFrame frame) throws IOException {
        System.out.println("[FrameManager.setFrame]");
        Font font = new Font("Times New Roman", Font.PLAIN, 16);

        frame.setBackground(Color.GRAY);
        Rectangle r = frame.getBounds();
        frame.add(gridPanel);

        int LEFT_START = (int) (0.06 * r.width);

        JPanel dashboard = new JPanel();
        dashboard.setBounds(LEFT_START, (int) (0.02 * r.height), (col + 1) * boxSize, 60);
        dashboard.setLayout(null);

        JButton resetButton = new JButton("RESET");

        dashboard.add(flagsIndicator);
        dashboard.add(resetButton);
        dashboard.setBackground(Color.pink);
        frame.add(dashboard);
        dashboard.updateUI();

        gridPanel.setBounds(LEFT_START, (int) (0.14 * r.height), (col + 1) * boxSize, (row + 1) * boxSize);
        gridPanel.setBackground(Color.RED);
        gridPanel.repaint();

        resetButton.setBounds((dashboard.getBounds().width / 2) - 40, 10, 80, 40);
        resetButton.setFont(font);
        resetButton.addActionListener(new NewGameListener());


        flagsIndicator.setText("FLAGS: ");
        flagsIndicator.setBounds(LEFT_START-20, 15, 80, 35);
        flagsIndicator.setFont(font);
//        flagsIndicator.setFont(new Font("",Font.PLAIN,15));
        flagsIndicator.setHorizontalAlignment(SwingConstants.CENTER);
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);

        new Grid().setGrid(gridPanel);

    }

}
