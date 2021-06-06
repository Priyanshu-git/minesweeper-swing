package listeners;

import entity.Box;
import frame.Base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewGameListener extends Base implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("[NewGameListener.actionPerformed]");
        System.out.println("e = " + e);

        for (Box[] boxes : grid) {
            for (Box value : boxes) {
                try {
                    value.setBomb(false);
                    value.setClicked(false);
                    value.setFlagged(false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                value.setValue(0);
            }
        }
        flags = bombs;
        moves = 0;
        flagsIndicator.setText("FLAGS: " + flags);

    }
}
