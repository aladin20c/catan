package scenes;

import Jade.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class GameOverScene extends Scene {


    public GameOverScene() {
        System.out.println("[GameOverScene]: game over ...");
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        if(!renderOnce) {
            graphics.setColor(new Color(58, 52, 28));
            graphics.fillRect(0, 0, Window.get().getWidth(), Window.get().getHeight());
            graphics.setColor(Color.RED);

            graphics.setFont(new Font("Arial", Font.PLAIN, 30));
            graphics.drawString("Game Over!", 200, 100);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.PLAIN, 15));
            graphics.drawString("Press any button", 200, 150);

            graphics.setColor(Color.black);
            graphics.setFont(new Font("Arial", Font.PLAIN, 37));
            renderOnce=true;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        Window.changeScene(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Window.changeScene(0);
    }
}
