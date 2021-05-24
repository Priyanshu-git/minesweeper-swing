import javafx.util.Pair;

public class Ratio {
    static Pair<Integer,Integer> getRatio(double x,double y){
        System.out.print("[Ratio.getRatio] param: ");
        System.out.println("x = " + x + ", y = " + y);

        while (x - (int) x > 0.0 || y - (int) y > 0.0) {
            x *= 10;
            y *= 10;
        }

        double temp1 = x;
        double temp2 = y;

        while (x != y) {
            if (x > y)
                x = x - y;
            else
                y = y - x;
        }

        int X = (int) (temp1 / x);
        int Y = (int) (temp2 / x);

        System.out.print("Simplified ratio: ");
        System.out.print("X = " + X+"\t");
        System.out.println("Y = " + Y);

        return new Pair<>(X,Y);
    }
}
