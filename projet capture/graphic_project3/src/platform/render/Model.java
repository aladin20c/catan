package platform.render;


import platform.utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Model extends Rectangle {
	private BufferedImage image;


	public Model(int w, int h) {
		super(w, h);
		this.image = null;
	}
	public Model(String fileName) {
		this.image= ImageUtil.loadImage(fileName);
		this.width = image.getWidth();
		this.height=image.getHeight();
	}
	public Model(BufferedImage image) {
		super(image.getWidth(), image.getHeight());
		this.image = image;
	}
	public Model(int width, int height, String fileName) {
		super(width, height);
		this.image= ImageUtil.loadImage(width,height,fileName);
	}
	public Model(int w, int h, BufferedImage image) {
		super(w, h);
		this.image = image;
	}


	public BufferedImage getImage() {return image;}
	public void setImage(BufferedImage image) {this.image = image;}

}
