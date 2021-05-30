import frame.FrameManager;

import java.io.IOException;

public class Minesweeper {
    public static void main(String[] args) throws IOException {
        Screen screen=new Screen();
        FrameManager manager=new FrameManager();
        manager.setFrame(screen.getFrame());
    }
}
