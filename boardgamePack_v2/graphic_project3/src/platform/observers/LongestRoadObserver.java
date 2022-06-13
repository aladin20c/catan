package platform.observers;

import platform.GameSettings;
import platform.entities.Player;
import platform.states.GameStateManager;

public class LongestRoadObserver implements Observer{

    GameSettings settings;
    GameStateManager gsm;

    public LongestRoadObserver(GameSettings settings, GameStateManager gsm) {
        this.settings = settings;
        this.gsm = gsm;
    }

    @Override
    public void inform(){
        for(Player p : settings.getPlayers()){
            p.setRoadsBuilt(p.calculateLongestRoad());
        }

        Player tmp=null;
        for(Player p : settings.getPlayers()){
            if(tmp==null || p.getRoadsBuilt()> tmp.getRoadsBuilt()){
                tmp=p;
            }
        }
        if(tmp==null || tmp.getRoadsBuilt()<5) return;

        for (Player p : settings.getPlayers()){
            if(p==tmp) {
                p.setLongestRoad(true);
            } else {
                p.setLongestRoad(false);
            }
        }

    }

}

