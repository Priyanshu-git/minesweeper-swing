package frame;

import entity.Box;

import javax.swing.*;

public abstract class Base {
    final public int row=11;
    final public int col=9;
    final public int boxSize=45;
    public static int bombs=14;
    public static int moves=0;
    public static Box[][] grid;
    public static JFrame frame;

    static public JLabel flagsIndicator=new JLabel();
    static public int flags;

}
