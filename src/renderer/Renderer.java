package renderer;




import java.awt.*;

public class Renderer {



	public static void renderModel(Model model, int x, int y, int width,int height, Graphics graphics) {
		if(model!=null) {
			graphics.drawImage(model.getImage(), x, y, width, height, null);
		}
	}

	public static void renderModel(Model model, int x, int y, Graphics graphics) {
		if(model!=null) {
			graphics.drawImage(model.getImage(), x, y, model.getImage().getWidth(), model.getImage().getHeight(), null);
		}
	}



	public static void drawCenteredText(Graphics g, String text, Rectangle rect, int size) {
		Font font = new Font("Arial", Font.PLAIN, size);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}


	public static void drawBottomRightedText(Graphics g, String text, Rectangle rect,int size) {
		Font font = new Font("Arial", Font.PLAIN, size);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = rect.x + rect.width - metrics.stringWidth(text);
		int y = rect.y + rect.height - metrics.getHeight() + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}



	public static void increaseBrightness(Rectangle rec,double percentage,Graphics graphics){
		increaseBrightness(rec.x, rec.y, rec.width, rec.height, percentage,graphics);
	}

	public static void increaseBrightness(int x,int y,int width,int hight,double percentage,Graphics graphics) {
		int brightness = (int)(256 + 256 * (percentage/100));
		graphics.setColor(new Color(0,0,0,brightness));
		graphics.fillRect(x, y, width, hight);
	}

}
