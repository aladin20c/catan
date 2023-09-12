package Jade;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    private static Mouse instance;
    public boolean mouseButton1Down=false;
    public boolean mouseButton2Down=false;
    public boolean mouseButton3Down=false;
    private  double xPos, yPos, lastY, lastX;
    private  boolean isDragging;


    private Mouse() {
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static Mouse get() {
        if (Mouse.instance == null) {
            Mouse.instance = new Mouse();
        }
        return Mouse.instance;
    }

    public static void mousePosCallback(double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButton1Down || get().mouseButton2Down || get().mouseButton3Down;
    }

    public static float getX() {
        return (float)get().xPos;
    }

    public static float getY() {
        return (float)get().yPos;
    }

    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        return button==1 && get().mouseButton1Down || button==2 && get().mouseButton2Down || button==3 && get().mouseButton3Down ;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Window.getCurrentScene().mouseClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Window.getCurrentScene().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Window.getCurrentScene().mouseExited(e);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()){
            case MouseEvent.BUTTON1:mouseButton1Down=true;
            case MouseEvent.BUTTON2:mouseButton2Down=true;
            case MouseEvent.BUTTON3:mouseButton3Down=true;
            default: break;
        }
        mousePosCallback(e.getX(), e.getY());
        Window.getCurrentScene().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()){
            case MouseEvent.BUTTON1:mouseButton1Down=false;
            case MouseEvent.BUTTON2:mouseButton2Down=false;
            case MouseEvent.BUTTON3:mouseButton3Down=false;
            default: break;
        }
        mousePosCallback(e.getX(), e.getY());
        Window.getCurrentScene().mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosCallback(e.getX(), e.getY());
        Window.getCurrentScene().mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosCallback(e.getX(), e.getY());
        Window.getCurrentScene().mouseMoved(e);
    }
}
