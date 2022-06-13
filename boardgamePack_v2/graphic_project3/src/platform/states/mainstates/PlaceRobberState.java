package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.*;
import platform.states.GameStateManager;
import platform.utils.Ressources;
import platform.utils.SelectButton;

import java.util.ArrayList;

public class PlaceRobberState extends SelectState<Tile>{

    public PlaceRobberState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm,settings,player);
        view.setInactive();
        System.out.println("[GameStates][PlaceRobberStateState]: Creating place robberState state...");
    }

    public void inittargets(){
        targets=new ArrayList<>();
        for(Tile tile: settings.map.getBlocks()){
            if(tile.getType()!= Ressources.SEA && tile.getLable()!=7 && !tile.hasStealer()) {
                int x=(tile.corners[Map.top_left].getPosX()+tile.corners[Map.bottom_right].getPosX())/2;
                int y=(tile.corners[Map.top_left].getPosY()+tile.corners[Map.bottom_right].getPosY())/2;
                targets.add(new SelectButton<Tile>(x,y,30,tile));
            }
        }
    }


    public View createView(){return  this.new View();}

    public void clicked(SelectButton<Tile> target){
        for(Tile tile: settings.map.getBlocks()){
            tile.setStealer(false);
        }
        target.getEntity().setStealer(true);

        ArrayList<Player> arg=new ArrayList<>();
        for(Player p : target.getEntity().getCornerOwners()){
            if(p!=currentplayer) arg.add(p);
        }
        if(arg.isEmpty())this.gsm.removeState();
        else this.gsm.addState(new StealState(gsm,settings,currentplayer,arg));
    }


    public class StateView extends SelectState<Side>.View {}

}
