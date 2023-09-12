package observers;

import Components.Player;
import Jade.Window;
import scenes.PlayingScene;

public class RobberObserver {



    public static void checkRobberUsed(){
        PlayingScene scene = (PlayingScene) Window.getCurrentScene();

        Player tmp = null;
        int robbers=2;

        for(Player player : scene.players){
            if(player.robbersUsed>robbers){
                robbers=player.robbersUsed;
                tmp=player;
            }
        }

        if(tmp!=null && !tmp.hasLargestArmy){
            for(Player player : scene.players){
                if(player==tmp){
                    player.hasLargestArmy=true;
                    player.increaseVP(2);
                }else if(player.hasLargestArmy){
                    player.hasLargestArmy=false;
                    player.increaseVP(-2);
                }
            }
        }

    }




}
