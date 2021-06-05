package listeners;

import entity.Box;
import entity.Coordinate;
import frame.Base;
import frame.Grid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NewGameListener extends Base implements ActionListener {
    Box[][] grid;
    Set<Coordinate> bombsList;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("[NewGameListener.actionPerformed]");
        System.out.println("e = " + e);

        grid = Grid.getGrid();

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
        flagsIndicator.setText("FLAGS: " + flags);


        initiate();

    }

    private void initiate() {
        bombsList = getBombList();
        bombsList.forEach(co -> {

            int i = co.getI(), j = co.getJ();
            System.out.println(i + " " + j);

            try {
                grid[i][j].setBomb(true);

                update(i + 1, j);
                update(i + 1, j + 1);
                update(i + 1, j - 1);
                update(i - 1, j + 1);
                update(i - 1, j);
                update(i - 1, j - 1);
                update(i, j - 1);
                update(i, j + 1);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    void update(int i, int j) {
        if (i < row && i >= 0 && j < col && j >= 0)
            grid[i][j].setValue(grid[i][j].getValue() + 1);
    }

    public Set<Coordinate> getBombList() {
        Set<Coordinate> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < bombs) {
            int i = random.nextInt(row);
            int j = random.nextInt(col);
            Coordinate co = new Coordinate(i, j);
            set.add(co);
        }
        return set;
    }
}
