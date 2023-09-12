package renderer;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Model{

	private BufferedImage image;

	/*public Model(String imageName) {
		InputStream is = getClass().getResourceAsStream("./resources/block.png");
			try {
				image = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
				//return new BufferedImage(90, 90, BufferedImage.TYPE_3BYTE_BGR);
			}
	}*/

	public Model (String imageName) {
		try {
			image = ImageIO.read(new File("./resources/" + imageName + ".png"));
		} catch (IOException e) {
			System.err.println(e.toString());
			System.err.println("[Render][Model]: Exception loading model " + imageName);
			e.printStackTrace();
			//return new BufferedImage(90, 90, BufferedImage.TYPE_3BYTE_BGR);
		}
	}

	public BufferedImage getImage() {return image;}
}
