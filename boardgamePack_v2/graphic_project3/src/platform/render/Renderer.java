package platform.render;



import platform.entities.StaticEntity;

import java.awt.*;

public class Renderer {

	/**Renders an entity on screen
	 * @param entity - The entity to render
	 * @param graphics - Graphics object
	 */
	public static void renderEntity(StaticEntity entity, Graphics graphics) {
		if(entity.hasModel()) renderModel(entity.getModel(), entity.getPosX(), entity.getPosY(), graphics);
	}
	
	/**Renders a model on screen
	 * @param model - The model to render
	 * @param posX - Model top left corner position
	 * @param posY - Model top left corner position
	 * @param graphics - Graphics object
	 */
	public static void renderModel(Model model, int posX, int posY, Graphics graphics) {
		graphics.drawImage(model.getImage(), posX, posY, model.width, model.height, null);
	}

	public static void drawTextInCenter(Model model,String text){
		Graphics g = model.getImage().getGraphics();
		FontMetrics metrics = g.getFontMetrics();
		int positionX = (model.getImage().getWidth() - metrics.stringWidth(text)) / 2;
		int positionY = (model.getImage().getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
		Font font = new Font("Arial", Font.BOLD, 25);
		g.setFont(font);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString(text, positionX, positionY);
	}
	public static void drawTextInBottomRight(String text,Graphics graphics,Rectangle rec){

		FontMetrics metrics = graphics.getFontMetrics();
		int positionX = (int)(rec.x+(rec.getWidth() - metrics.stringWidth(text)-4));
		int positionY = (int)(rec.y+(rec.getHeight() - metrics.getHeight()) + metrics.getAscent()-2);

		Font font = new Font("Arial", Font.BOLD, 20);
		graphics.setFont(font);
		graphics.setColor(Color.WHITE);
		graphics.drawString(text, positionX, positionY);
	}



	public static void drawTextInCenter(Rectangle rec,int number,int size,Color color,Graphics graphics){
		String text=number+"";
		FontMetrics metrics = graphics.getFontMetrics();

		int positionX = rec.x+ (rec.width - metrics.stringWidth(text)) / 2;
		int positionY = rec.y+(rec.height - metrics.getHeight()) / 2 + metrics.getAscent();
		Font font = new Font("Arial", Font.BOLD, size);
		graphics.setFont(font);
		graphics.setColor(color);
		graphics.drawString(text, positionX, positionY);
	}
	public static void drawRect(Rectangle rec,Color color,Graphics graphics){
		graphics.setColor(color);
		graphics.drawRect(rec.x,rec.y,rec.width,rec.height);
	}


	public static void increaseBrightness(int x,int y,int w,int h,double percentage,Graphics graphics) {
		int brightness = (int)(256 + 256 * (percentage/100));
		graphics.setColor(new Color(0,0,0,brightness));
		graphics.fillRect(x, y, w, h);
	}
	public static void increaseBrightness(Rectangle rec,double percentage,Graphics graphics){
		increaseBrightness(rec.x, rec.y, rec.width, rec.height, percentage,graphics);
	}

}
