package platform.observers;

import platform.GameSettings;
import platform.entities.Player;
import platform.states.GameOver;
import platform.states.GameStateManager;

public class LargestArmyObserver implements Observer{

    GameSettings settings;
    GameStateManager gsm;

    public LargestArmyObserver(GameSettings settings, GameStateManager gsm) {
        this.settings = settings;
        this.gsm = gsm;
    }

    @Override
    public void inform(){
        Player tmp= null;
        for(Player p : settings.getPlayers()){
            if(tmp==null || p.getRobbersUsed()> tmp.getRobbersUsed()) tmp=p;
        }
        if(tmp==null || tmp.getRobbersUsed()<3) return;
        for (Player p : settings.getPlayers()){
            if(p==tmp) p.setLArgestArmy(true);
            else p.setLArgestArmy(false);
        }
    }
}

