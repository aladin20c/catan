package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.states.GameStateManager;
import platform.utils.Ressources;
import platform.utils.SelectButton;

import java.util.ArrayList;

public class MonopolyState extends MainState{

    public MonopolyState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm, settings, player);
        System.out.println("[GameStates][MonopolyState]: Creating place monopoly state...");
        view.setActive(view.rescards);
    }


    public void reinit(){}
    public View createView(){return  this.new View();}

    public void ResCard(Ressources r){
        currentplayer.monopoly(r,settings.getPlayers());
        this.gsm.deleteState();
    }
    public class View extends MainState.View{ }

}
