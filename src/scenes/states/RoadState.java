package scenes.states;

import Components.Side;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;

public class RoadState extends State{

    private ArrayList<Side> sides;

    public RoadState(PlayingScene scene, ArrayList<Side> sides) {
        super(scene);
        System.out.println("[RoadState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit") || button.getText().equals("road"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(false);}

        this.sides=sides;
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
        } else if(name.equals("road")){
            scene.setState(new MainState(scene));
        }else if(name.matches("\\d+")){

            int index= Integer.parseInt(name);
            scene.players[scene.currentPlayer].buildRoad(sides.get(index),true);
            scene.setState(new MainState(scene));

        }
    }
}
