package scenes.states;

import Components.Corner;
import Components.Tile;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;

public class PlaceRobberState extends State{

    private ArrayList<Tile> tiles;

    public PlaceRobberState(PlayingScene scene) {
        super(scene);
        System.out.println("[PlaceRobberState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit"));}
        for(Button button : scene.devCards){button.setActive(button.getText().equals("robber"));}
        for(Button button : scene.resCards){button.setActive(false);}
        this.tiles=scene.map.getFreeRobberTiles();

        int i=0;
        for (Tile tile : tiles){
            int x= tile.x + Tile.WIDTH/2;
            int y= tile.y + Tile.HEIGHT/2;
            buttons.add(new Button(x-10,y-10,20,20,String.valueOf(i), Color.CYAN,Color.cyan));
            i++;
        }
        for (Button button : this.buttons){
            button.setActive(true);
        }

    }

    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {
            Window.changeScene(31);
        } else if(name.equals("robber")){
            scene.setState(new MainState(scene));
        }else if(name.matches("\\d+")){
            int index= Integer.parseInt(name);

            for(Tile tile: scene.map.getTiles()){
                if(tile.hasStealer) tile.setHasStealer(false);
            }
            tiles.get(index).setHasStealer(true);
            scene.players[scene.currentPlayer].useCard("robber");

            boolean transition =false;
            for (Corner corner : tiles.get(index).getCorners()){
                if(corner.player!=null && corner.player!=scene.players[scene.currentPlayer]){
                    transition=true;
                    break;
                }
            }
            if(transition) {
                scene.setState(new StealState(scene, tiles.get(index)));
            }else{
                scene.setState(new MainState(scene));
            }
        }
    }
}
