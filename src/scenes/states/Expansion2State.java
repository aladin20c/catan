package scenes.states;

import Components.Side;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;

public class Expansion2State extends State{

    private ArrayList<Side> sides;

    public Expansion2State(PlayingScene scene) {
        super(scene);
        System.out.println("[Expansion2State] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(false);}

        this.sides=scene.players[scene.currentPlayer].getPotentialRoads();
        int i=0;
        for (Side side : sides){
            int x= (side.corner1.x + side.corner2.x)/2;
            int y= (side.corner1.y + side.corner2.y)/2;
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

        }else if(name.matches("\\d+")){

            int index= Integer.parseInt(name);
            scene.players[scene.currentPlayer].buildRoad(sides.get(index),false);
            scene.setState(new MainState(scene));
        }
    }
}
