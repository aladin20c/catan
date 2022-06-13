package platform.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {


    private ImageUtil() {}

    public static BufferedImage loadImage(int width,int height,String fileName) {
            try {
                return ImageIO.read(new File("./images/" + fileName + ".png"));
            } catch (IOException e) {
                System.err.println(e.toString());
                System.err.println("[Render][Model]: Exception loading model " + fileName);
                return new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            }
    }
    public static BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(new File("./images/" + fileName + ".png"));
        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("[Render][Model]: Exception loading model " + fileName);
            return new BufferedImage(90, 90, BufferedImage.TYPE_3BYTE_BGR);
        }
    }

}




