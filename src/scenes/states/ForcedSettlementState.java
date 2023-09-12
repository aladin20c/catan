package scenes.states;

import Components.Corner;
import Components.Side;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;

public class ForcedSettlementState extends State{

    private ArrayList<Corner> corners;

    public ForcedSettlementState(PlayingScene scene, ArrayList<Corner> corners) {
        super(scene);
        System.out.println("[ForcedSettlementState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(false);}

        this.corners=corners;
        int i=0;
        for (Corner corner : corners){
            buttons.add(new Button(corner.x-10,corner.y-10,20,20,String.valueOf(i), Color.CYAN,Color.cyan));
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
            scene.players[scene.currentPlayer].buildSettlement(corners.get(index),false);

            ArrayList<Side> sides=new ArrayList<>();
            for (Side side : corners.get(index).getSides()){
                if(side!=null && side.isBuildable && !sides.contains(side)) {
                    sides.add(side);
                }
            }
            scene.setState(new ForcedRoadState(scene,sides));
        }
    }

}
