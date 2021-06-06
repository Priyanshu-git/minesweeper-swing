package frame;

import entity.Coordinate;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Initiate extends Base {

    static Set<Coordinate> bombsList;
    private int exI, exJ;

    public void exclude(int exI, int exJ) {
        this.exI = exI;
        this.exJ = exJ;
    }

    public void initiate() {
        bombsList = generateBombList();

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

    public Set<Coordinate> generateBombList() {
        Set<Coordinate> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < bombs) {
            int i = random.nextInt(row);
            int j = random.nextInt(col);

            if ((i <= exI + 1 && i >= exI - 1) && (j <= exJ + 1 && j >= exJ - 1)) continue;

            Coordinate co = new Coordinate(i, j);
            set.add(co);
        }
        return set;
    }

    public Set<Coordinate> getBombsList() {
        return bombsList;
    }
}
