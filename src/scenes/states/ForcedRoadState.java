package scenes.states;

import Components.Side;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;

public class ForcedRoadState extends State{

    private ArrayList<Side> sides;

    public ForcedRoadState(PlayingScene scene, ArrayList<Side> sides) {
        super(scene);
        System.out.println("[ForcedRoadState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit"));}
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
        }else if(name.matches("\\d+")){
            int index= Integer.parseInt(name);
            scene.players[scene.currentPlayer].buildRoad(sides.get(index),false);
            if(scene.turn==1){
                if(sides.get(0).corner1==sides.get(1).corner1 || sides.get(0).corner1==sides.get(1).corner2){
                    scene.players[scene.currentPlayer].harness(sides.get(0).corner1);
                }else{
                    scene.players[scene.currentPlayer].harness(sides.get(0).corner2);
                }
            }
            scene.nextPlayer();
        }
    }

}
