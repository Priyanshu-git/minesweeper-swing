package frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageManager extends Base{
    static Map<String, ImageIcon> map = new HashMap<>();

    public ImageIcon getImage(String path) {
//        System.out.print("\n[ImageManager.getImage]\t");
//        System.out.println("path = " + path);

        path="/res/img/"+path;

        if (map.containsKey(path)) {
//            System.out.println("Target image found in map");
            return map.get(path);
        } else {
//            System.out.println("Target image not found in map");
            InputStream imgStream = Frame.class.getResourceAsStream(path);
            ImageIcon ii=null;
            try {
                BufferedImage myImg = ImageIO.read(imgStream);
                Image image = myImg.getScaledInstance(boxSize, boxSize, Image.SCALE_FAST);
                ii = new ImageIcon(image);
            } catch (Exception e){
//                System.out.println("Failed to load image: "+path);
                e.printStackTrace();
                System.exit(1);
            }
//            System.out.println("Target image loaded from disk");
            map.put(path, ii);
            return ii;
        }
    }
}
