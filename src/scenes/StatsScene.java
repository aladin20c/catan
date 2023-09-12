package scenes;

import Components.Player;
import Jade.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

public class StatsScene extends Scene{

    public Player[] players;

    public StatsScene(PlayingScene scene) {
        System.out.println("[StatsScene]: stats ...");
        this.players=scene.players;
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

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.PLAIN, 30));
            graphics.drawString("Press any button", 200, 150);


            if(this.players!=null){
                Arrays.sort(this.players, Comparator.comparingInt(o -> o.SVP));
                int count=0;
                for (Player player : this.players){
                    graphics.drawString(player.name, Window.get().getWidth() / 2 - 300, 250 + count * 100);
                    graphics.drawString( String.valueOf(player.SVP), Window.get().getWidth() / 2 + 100, 250 + count * 100);
                    count++;
                }
            }
            renderOnce=true;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        Jade.Window.changeScene(55);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Window.changeScene(55);
    }


}
