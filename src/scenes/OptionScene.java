package scenes;

import Jade.Window;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionScene extends Scene{

    private String[] optionsMenu;
    private int selected;

    public OptionScene() {
        System.out.println("[OptionsScene]: Creating Options menu...");
    }

    @Override
    public void init(){
        this.optionsMenu = new String[] {"Map", "Players", "Speed","return"};
        this.selected=0;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(58, 52, 28));
        graphics.fillRect(0, 0, Window.get().getWidth(), Window.get().getHeight());
        graphics.setFont(new Font("Arail", Font.PLAIN, 42));
        for(int i=0;i<optionsMenu.length;i++) {
            if(selected == i) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.WHITE);
            }

            String data="";
            switch (i){
                case 0:data+="<   ";data+=Settings.map;data+="   >";break;
                case 1:data+="<   ";data+=Settings.numberOfPlayers;data+="   >";break;
                case 2:data+="<   ";data+=Settings.speed;data+="   >";break;
            }
            graphics.drawString(optionsMenu[i], Window.get().getWidth()/2 -200, 100 +i*120);
            graphics.drawString(data, Window.get().getWidth()/2 +100, 100 +i*120);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE) {
            Window.changeScene(0);
        }else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            if(selected > 0) selected--;
        }
        else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            if(selected < optionsMenu.length-1) selected++;
        }
        else if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_E) {
            if(selected == 3) Window.changeScene(0);
        }
        else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {

            switch (selected){
                case 0 :
                    Settings.map = Settings.map>=2?1:Settings.map+1;
                    break;
                case 1 :
                    Settings.numberOfPlayers= Settings.numberOfPlayers>=4?2:Settings.numberOfPlayers+1;
                    break;
                case 2 :
                    Settings.speed=Settings.speed>=3?1:Settings.speed+1;
                    break;
            }
        }
        else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            switch (selected){
                case 0 :
                    Settings.map = Settings.map<=1?2:Settings.map-1;
                    break;
                case 1 :
                    Settings.numberOfPlayers= Settings.numberOfPlayers<=2?4:Settings.numberOfPlayers-1;
                    break;
                case 2 :
                    Settings.speed=Settings.speed<=1?3:Settings.speed-1;
                    break;
            }
        }
    }



}
