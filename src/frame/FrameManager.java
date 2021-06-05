package frame;

import listeners.DifficultyListener;
import listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
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
        flagsIndicator.setHorizontalAlignment(SwingConstants.CENTER);
        resetButton.setHorizontalAlignment(SwingConstants.CENTER);

        String[] difficulty ={"EASY","MEDIUM","HARD"};
        JComboBox<String> cb=new JComboBox(difficulty);
        cb.setBounds(LEFT_START+(col * boxSize)-110, 20,110,30);
        dashboard.add(cb);
        cb.repaint();
        cb.addItemListener(new DifficultyListener());
        new Grid().setGrid(gridPanel);

        JButton show=new JButton("Show");
        show.setBounds(1,1,50,30);
        frame.add(show);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        try {
                            grid[i][j].setClicked(true);
                        } catch (IOException ee) {
                            ee.printStackTrace();
                        }
                    }
                }
            }
        });

    }

}
