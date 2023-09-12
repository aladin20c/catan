package scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

import Jade.Keyboard;
import Jade.Window;

public class MenuScene extends Scene{

    private String[] optionsMenu;
    private int selected;

    public MenuScene() {
        System.out.println("[MenuScene]: Creating menu...");
    }

    @Override
    public void init() {
        this.optionsMenu = new String[] {"Start", "Options", "Quit"};
        this.selected=0;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        if(!renderOnce) {
            graphics.setColor(new Color(58, 52, 28));
            graphics.fillRect(0, 0, Window.get().getWidth(), Window.get().getHeight());
            renderOnce=true;
        }
        graphics.setFont(new Font("Arail", Font.PLAIN, 42));
        for(int i=0;i<optionsMenu.length;i++) {
            if(selected == i) {
                graphics.setColor(Color.GREEN);
            }else {
                graphics.setColor(Color.WHITE);
            }
            graphics.drawString(optionsMenu[i], Window.get().getWidth()/2 -200, 100 +i*120);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
            if(selected > 0) selected--;
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
            if(selected < optionsMenu.length-1) selected++;
        } else if(e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_E) {
            if(selected == 0) {
                //initialising observers
                //this.gsm.applyStrategy();
                Window.changeScene(2);
            } else if(selected == 1) {
                Window.changeScene(1);
            } else if(selected == 2) {
                System.exit(0);
            }
        }
    }
}
