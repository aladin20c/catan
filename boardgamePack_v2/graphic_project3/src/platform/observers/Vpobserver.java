package platform.observers;

import platform.GameSettings;
import platform.entities.Player;
import platform.states.GameOver;
import platform.states.GameStateManager;

public class Vpobserver implements Observer{

    GameSettings settings;
    GameStateManager gsm;

    public Vpobserver(GameSettings settings, GameStateManager gsm) {
        this.settings = settings;
        this.gsm = gsm;
    }

    @Override
    public void inform(){
        for(Player p : settings.getPlayers()){
            if(p.getVP()>=10)  this.gsm.addState(new GameOver(gsm,settings));
        }
    }
}
