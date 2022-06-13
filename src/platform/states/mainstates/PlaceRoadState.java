package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.entities.Side;
import platform.states.GameStateManager;
import platform.utils.SelectButton;

import java.awt.*;
import java.util.ArrayList;

public class PlaceRoadState extends SelectState<Side> {

    public PlaceRoadState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm,settings,player);
        System.out.println("[GameStates][PlacRoadState]: Creating place road state...");
    }

    public void inittargets(){
        targets=new ArrayList<>();
        for(Corner c: currentplayer.settelments){
            if(c.isIsolated()) {
                for (Side side : c.getAdjascentSides()) {
                    targets.add(new SelectButton<Side>((side.corner_1.getPosX()+side.corner_2.getPosX())/2,(side.corner_1.getPosY()+side.corner_2.getPosY())/2,30,side));
                }
                return;
            }
        }
    }

    public View createView(){return  this.new View();}

    public void clicked(SelectButton<Side> target){
        target.getEntity().setRoad(currentplayer);
        this.gsm.removeState();
    }


    public class StateView extends SelectState<Side>.View {
    }

}
