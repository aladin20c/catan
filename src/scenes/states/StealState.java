package scenes.states;

import Components.Corner;
import Components.Player;
import Components.Tile;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;
import utils.Resource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StealState extends State {


    ArrayList<Player> players;

    public StealState(PlayingScene scene, Tile tile) {
        super(scene);
        System.out.println("[StealState] making State");
        for (Button button : scene.controls) {button.setActive(button.getText().equals("exit") || button.getText().equals("next"));}
        for (Button button : scene.devCards) {button.setActive(false);}
        for (Button button : scene.resCards) {button.setActive(true);}
        players = new ArrayList<>();

        for (Corner corner : tile.getCorners()){
            if(corner.player!=null &&  !players.contains(corner.player) && corner.player!=scene.players[scene.currentPlayer]){
                players.add(corner.player);
            }
        }

        if(this.players.isEmpty()){
            scene.setState(new MainState(scene));
        }else {

            int i = 0;
            int w = 50;
            int h = 50;
            int xStart = 10;
            int yStart = scene.bottomBar.y - 10 - h;
            int xOffset = (int) (w * 1.1f);

            for (Player player : this.players) {
                buttons.add(new Button(xStart + xOffset * i, yStart, w, h, String.valueOf(i), Color.CYAN, player.getModel()));
                i++;
            }
            for (Button button : this.buttons) {
                button.setActive(true);
            }
        }

    }

    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {
            Window.changeScene(31);
        } else if (name.equals("next")) {

            scene.setState(new MainState(scene));

        } else if(name.matches("\\d+")){

            int index= Integer.parseInt(name);
            scene.players[scene.currentPlayer].stealFrom(this.players.get(index));
            scene.setState(new MainState(scene));

        }
    }

}
