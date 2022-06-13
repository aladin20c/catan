package platform.utils;

import platform.render.Model;
import platform.render.ModelManager;
import platform.render.Renderer;

import java.awt.*;

public class CustomButton extends Rectangle {

    private String name;
    private boolean active;
    private boolean mouseOver;
    private boolean mousePressed;
    private Model model;

    // For normal Buttons
    public CustomButton(Model model,String name) {
        this.active=true;
        this.name=name;
        this.model = model;
    }
    public CustomButton(Model model,String name,int x, int y, int width, int height) {
        super(x, y, width, height);
        this.active=true;
        this.name=name;
        this.model = model;
    }




    public void render(Graphics graphics) {
        // Body
        drawBody(graphics);
        // Border
        drawBorder(graphics);
        // Text
        if(model==null) drawText(graphics);
        else drawModel(graphics);
        if (mouseOver || !active) Renderer.increaseBrightness(this,-50,graphics);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        if (mousePressed && active) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }
    private void drawBody(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, width, height);
    }
    private void drawModel(Graphics graphics) {
        Renderer.renderModel(model,x,y,graphics);
    }
    private void drawText(Graphics graphics) {
        int w = graphics.getFontMetrics().stringWidth(name);
        int h = graphics.getFontMetrics().getHeight();
        graphics.drawString(name, x - w / 2 + width / 2, y + h / 2 + height / 2);
    }



    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }
    public void setBounds( int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public boolean isMouseOver() {
        return mouseOver;
    }
    public boolean isMousePressed() {
        return mousePressed;
    }
    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
    public String getName() {return name;}
}