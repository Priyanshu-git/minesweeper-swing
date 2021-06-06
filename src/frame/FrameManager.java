package frame;

import entity.RoundedBorder;
import listeners.DifficultyListener;
import listeners.NewGameListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FrameManager extends Base {
    public static JPanel gridPanel;
    public static JButton newGameButton;

    public FrameManager() {
        gridPanel = new JPanel();
    }

    public void setFrame(JFrame frame) throws IOException {
        System.out.println("[FrameManager.setFrame]");

        frame.setBackground(Color.GRAY);
        frame.add(gridPanel);

        int HALF_BOX = (int) (0.5 * boxSize);

        JPanel dashboard = new JPanel();
        dashboard.setBounds(HALF_BOX, boxSize, (col + 1) * boxSize, boxSize);
        dashboard.setLayout(null);

        newGameButton = new JButton("New Game");

        dashboard.add(flagsIndicator);
        dashboard.add(newGameButton);
        dashboard.setBackground(Color.pink);
        frame.add(dashboard);
        dashboard.updateUI();

        gridPanel.setBounds(HALF_BOX, 5*HALF_BOX, (col + 1) * boxSize, (row + 1) * boxSize);
        gridPanel.setBackground(Color.RED);
        gridPanel.repaint();

        int BUTTON_SIZE = 36;
        newGameButton.setBounds((dashboard.getBounds().width / 2) - BUTTON_SIZE, 5, BUTTON_SIZE*2, BUTTON_SIZE);
        newGameButton.setMargin(new Insets(0,0,0,0));
        newGameButton.addActionListener(new NewGameListener());


        flagsIndicator.setText("FLAGS: ");
        flagsIndicator.setBounds(HALF_BOX - 20, 5, 80, 35);
        flagsIndicator.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);

        String[] difficulty = {"EASY", "MEDIUM", "HARD"};
        JComboBox<String> cb = new JComboBox(difficulty);
        cb.setBounds(HALF_BOX + ((col - 2) * boxSize), 10, 2 * boxSize, 25);
        dashboard.add(cb);
        cb.repaint();
        cb.addItemListener(new DifficultyListener());
        new Grid().setGrid(gridPanel);


//        HELP BUTTON
        JButton help = new JButton();
        help.setBorder(new RoundedBorder(32));


//        InputStream imgStream = Frame.class.getResourceAsStream("/res/img/help.png");
//        BufferedImage myImg = ImageIO.read(imgStream);
        BufferedImage myImg = ImageIO.read(new File("res/img/help.png"));
        Image image = myImg.getScaledInstance(32, 32, Image.SCALE_REPLICATE);

        help.setIcon(new ImageIcon(image));

        frame.add(help);
        help.setBounds( (col+1)*boxSize-HALF_BOX, 5, 32, 32);
        help.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/Priyanshu-git/minesweeper-swing#instructions-to-play-the-game").toURI());
            } catch (Exception ew) {
                ew.printStackTrace();
            }
        });
        help.setToolTipText("Help");
        help.repaint();
        help.updateUI();


        JLabel label = new JLabel("RMB: play         LMB: flag");
        label.setBounds(dashboard.getX(), dashboard.getY() + dashboard.getHeight() - 5, boxSize*(col-3), 30);
        frame.add(label);
        label.repaint();

    }

    public static JButton getNewGameButton() {
        return newGameButton;
    }
}
