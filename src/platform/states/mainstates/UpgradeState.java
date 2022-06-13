package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.states.GameStateManager;
import platform.utils.SelectButton;

import java.util.ArrayList;

public class UpgradeState extends SelectState<Corner>{

    public UpgradeState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm, settings, player);
        view.getButton("upgrade").setActive(true);
        System.out.println("[GameStates][UpgradeState]: Creating upgrade state...");
    }

    public View createView(){return  this.new View();}
    public void inittargets(){
        targets=new ArrayList<>();
        for (Corner corner : currentplayer.getPotentialCities())
            targets.add(new SelectButton<Corner>(corner.getPosX(),corner.getPosY(),30,corner));
    }

    public void clicked(SelectButton<Corner> target){
        target.getEntity().setCity(currentplayer);
        this.gsm.deleteState();
    }



    @Override
    public void upgrade(){
        gsm.removeState();
    }


    public class View extends SelectState<Corner>.View{}
}
