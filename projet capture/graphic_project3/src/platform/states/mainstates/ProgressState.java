package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Player;
import platform.states.GameStateManager;
import platform.utils.Ressources;

public class ProgressState extends MainState{

    public ProgressState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm, settings, player);
        System.out.println("[GameStates][ProgressState]: Creating place progress state...");
        view.setActive(view.rescards);
    }


    public void reinit(){}
    public View createView(){return  this.new View();}
    public void ResCard(Ressources r){
        currentplayer.addRessource(r);
        this.gsm.removeState();
    }

    public class View extends MainState.View{ }
}
