package renderer;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class Button extends Rectangle {


    public boolean active, mouseOver, mousePressed;
    private String text;
    private Color bordColor;
    private Color fillColor;
    private Model model;


    public Button(int x, int y, int width, int height, String text, Color bordColor, Model model) {
        super(x, y, width, height);
        this.active = false;
        this.mouseOver = false;
        this.mousePressed = false;
        this.text = text;
        this.bordColor = bordColor;
        this.model = model;
    }

    public Button(int x, int y, int width, int height, String text, Color bordColor, Color fillColor) {
        super(x, y, width, height);
        this.active = false;
        this.mouseOver = false;
        this.mousePressed = false;
        this.text = text;
        this.bordColor = bordColor;
        this.fillColor = fillColor;
    }


    public Button(int x, int y, int width, int height, Model model) {
        super(x, y, width, height);
        this.active = false;
        this.mouseOver = false;
        this.mousePressed = false;
        this.model = model;
    }

    public void render(Graphics graphics) {

        //body
        if(fillColor!=null) {
            if(mouseOver || mousePressed ){
                graphics.setColor(new Color(218, 218, 64));
            }else {
                graphics.setColor(this.fillColor);
            }
            graphics.fillRect(x+1, y+1, width-2, height-2);
        }
        if(this.model!=null){
            Renderer.renderModel(model,x,y,width,height,graphics);
        }

        // Border
        if(bordColor!=null && this.active) {
            if(mouseOver || mousePressed ){
                graphics.setColor(new Color(218, 218, 64));
            }else {
                graphics.setColor(this.bordColor);
            }
            graphics.drawRect(x, y, width, height);
        }

        //active
        if (!active) {
            Renderer.increaseBrightness(this,-50,graphics);
        }
    }


    public void setActive(boolean active) {
        this.active = active;
    }
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public String getText() {
        return text;
    }

    public void resetBooleans(){
        mousePressed=false;
        mouseOver=false;
    }
}
