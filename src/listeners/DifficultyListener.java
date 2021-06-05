package listeners;

import frame.Base;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DifficultyListener extends Base implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox cb= (JComboBox) e.getSource();
        String t= (String) cb.getSelectedItem();
        if ("EASY".equals(t))
            bombs=14;
        if ("MEDIUM".equals(t))
            bombs=20;
        if ("HARD".equals(t))
            bombs=30;
    }
}
