package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.entities.Side;
import platform.states.GameStateManager;

import platform.utils.SelectButton;

import java.util.ArrayList;

public class BuildState extends SelectState<Corner>{

    public BuildState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm, settings, player);
        view.getButton("build").setActive(true);
        System.out.println("[GameStates][BuildState]: Creating build state...");
    }

    public View createView(){return  this.new View();}
    public void inittargets(){
        targets=new ArrayList<>();
        for (Corner corner : currentplayer.getPotentialSettelments())
            targets.add(new SelectButton<Corner>(corner.getPosX(),corner.getPosY(),30,corner));
    }

    public void clicked(SelectButton<Corner> target){
        target.getEntity().setSettlement(currentplayer);
        this.gsm.deleteState();
    }



    @Override
    public void build(){
        gsm.removeState();
    }


    public class View extends SelectState<Corner>.View{}
}
