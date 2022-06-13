package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.entities.Side;
import platform.states.GameStateManager;

import platform.utils.SelectButton;

import java.awt.*;
import java.util.ArrayList;

public class PlaceSettelmentState extends SelectState<Corner>{
    private boolean getRessources=false;

    public PlaceSettelmentState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm, settings, player);
        System.out.println("[GameStates][PlacSettelmentState]: Creating place settelment state...");
    }

    public PlaceSettelmentState(GameStateManager gsm, GameSettings settings, Player player, boolean getRessources) {
        super(gsm, settings, player);
        this.getRessources = getRessources;
        System.out.println("[GameStates][PlacSettelmentState]: Creating place settelment state...");
    }

    public void inittargets(){
        targets=new ArrayList<>();
        for (Corner corner : settings.map.getCorners()) {
            if(corner.isBuildable()){
                targets.add(new SelectButton<Corner>(corner.getPosX(),corner.getPosY(),30,corner));
            }
        }
    }

    public View createView(){return  this.new View();}

    public void clicked(SelectButton<Corner> target){
        target.getEntity().ForceSetSettlement(currentplayer);
        if(getRessources)target.getEntity().harnessAdjascentTiles();
        this.gsm.removeState();
    }

    public class View extends SelectState<Corner>.View{ }

}
