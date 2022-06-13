package platform.states;

import platform.DisplayJframe;
import platform.GameSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsState extends GameState{

    private String[] optionsMenu;
    private int selected;

    public OptionsState(GameStateManager gsm,GameSettings settings) {
        super(gsm,settings);
        this.optionsMenu = new String[] {"Map", "Humans", "Bots","return"};
        selected=0;
        System.out.println("[GameStates][OptionsState]: Creating Options menu...");
    }

    public void reinit(){selected=0;}

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(51, 231, 255));
        graphics.fillRect(0, 0, DisplayJframe.WIDTH, DisplayJframe.HEIGHT);

        graphics.setFont(new Font("Arail", Font.PLAIN, 42));

        for(int i=0;i<optionsMenu.length;i++) {
            if(selected == i) {graphics.setColor(Color.GREEN);}
            else {graphics.setColor(Color.WHITE);}

            String data="";
            switch (i){
                case 0:data+="<   ";data+=settings.getMapType();data+="   >";break;
                case 1:data+="<   ";data+=settings.getHumans();data+="   >";break;
                case 2:data+="<   ";data+=settings.getBots();data+="   >";break;
            }
            graphics.drawString(optionsMenu[i], DisplayJframe.WIDTH/2 -200, 100 +i*120);
            graphics.drawString(data, DisplayJframe.WIDTH/2 +100, 100 +i*120);
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ESCAPE) {
            this.gsm.removeState();
        }else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            if(selected > 0) selected--;
        }
        else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            if(selected < optionsMenu.length-1) selected++;
        }
        else if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_E) {
            if(selected == 3) this.gsm.removeState();
        }else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            if(selected==0){
                switch (settings.getMapType()){
                    case 1:settings.setMapType(3);break;
                    case 2:settings.setMapType(1);break;
                    case 3:settings.setMapType(2);break;
                }
            }else if(selected==1){
                if(settings.getHumans()>1) settings.setHumans(settings.getHumans()-1);
            }else if(selected==2){
                if(settings.getHumans()>0 && settings.getBots()>0) settings.setBots(settings.getBots()-1);
            }
        }
        else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if(selected==0){
                switch (settings.getMapType()){
                    case 1:settings.setMapType(2);break;
                    case 2:settings.setMapType(3);break;
                    case 3:settings.setMapType(1);break;
                }
            }else if(selected==1){
                if(settings.getHumans()+settings.getBots()<GameSettings.MAXPLAYERS) settings.setHumans(settings.getHumans()+1);
            }else if(selected==2){
                if(settings.getHumans()+settings.getBots()<GameSettings.MAXPLAYERS) settings.setBots(settings.getBots()+1);
            }
        }
    }

    @Override
    public void keyReleased(int key) {}

}
