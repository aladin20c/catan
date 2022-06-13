package platform.utils;

import platform.entities.StaticEntity;

import java.awt.*;

public class SelectButton<T>{
    private int x;
    private int y;
    private int span;
    private T entity;

    private boolean mouseOver;
    private boolean mousePressed;

    public SelectButton(int x, int y, int span,T entity) {
        this.x = x;
        this.y = y;
        this.span = span;
        this.entity=entity;
    }

    public void render(Graphics g) {
        // Body
        drawBody(g);
        // Border
        drawBorder(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.gray);
        g.drawOval(x, y, span, span);
        if (mousePressed) {
            g.drawOval(x, y, span-1, span-1);
            g.drawOval(x, y, span-2, span-2);
        }
    }

    private void drawBody(Graphics g) {
        if (mouseOver) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, span, span);
        }
    }

    public boolean contains(int x,int y){
        int delta= (int)Math.sqrt ((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
        return delta<span;
    }
    public void resetBooleans(){
        mouseOver=false;
        mousePressed=false;
    }
    public void setMouseOver(boolean mouseOver) {this.mouseOver = mouseOver;}
    public void setMousePressed(boolean mousePressed) {this.mousePressed = mousePressed;}
    public boolean isMouseOver() {return mouseOver;}
    public boolean isMousePressed() {return mousePressed;}
    public T getEntity() {return entity;}
    public void setEntity(T entity) {this.entity = entity;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpan() {
        return span;
    }
}