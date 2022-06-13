package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.entities.Side;
import platform.states.GameStateManager;
import platform.utils.SelectButton;

import java.util.ArrayList;

public class RoadState extends SelectState<Side>{

    public RoadState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm, settings, player);
        view.getButton("road").setActive(true);
        System.out.println("[GameStates][RoadState]: Creating Road state...");
    }

    public View createView(){return  this.new View();}

    public void inittargets(){
        targets=new ArrayList<>();
        for (Side side : currentplayer.getPotentialRoads())
            targets.add(new SelectButton<Side>((side.corner_1.getPosX()+side.corner_2.getPosX())/2,(side.corner_1.getPosY()+side.corner_2.getPosY())/2,30,side));
    }

    public void clicked(SelectButton<Side> target){
        target.getEntity().setRoad(currentplayer);
        this.gsm.deleteState();
    }

    @Override
    public void road(){
        gsm.removeState();
    }

    public class View extends PlaceRoadState.View{}
}
