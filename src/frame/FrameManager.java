package frame;

import entity.RoundedBorder;
import listeners.DifficultyListener;
import listeners.NewGameListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FrameManager extends Base {
    static JPanel gridPanel;

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


//        HELP BUTTON
        JButton help=new JButton();
        help.setBorder(new RoundedBorder(32));

        InputStream imgStream = Frame.class.getResourceAsStream("/res/img/help.png");
        BufferedImage myImg = ImageIO.read(imgStream);
        Image image = myImg.getScaledInstance(32, 32, Image.SCALE_FAST);

        help.setIcon(new ImageIcon(image));

        frame.add(help);
        help.setBounds((int) (frame.getBounds().getWidth()-40),1,32,32);
        help.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/Priyanshu-git/minesweeper-swing#readme").toURI());
            } catch (Exception ew) {
                ew.printStackTrace();
            }
        });


    }

}
