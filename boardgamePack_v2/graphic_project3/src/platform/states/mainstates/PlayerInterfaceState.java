package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Player;
import platform.entities.Tile;
import platform.render.Renderer;
import platform.states.GameState;
import platform.states.GameStateManager;
import platform.utils.Ressources;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerInterfaceState extends MainState {

    public boolean diceRolled=false;
    ArrayList<Tile> tilesRolled=new ArrayList<>();

    public PlayerInterfaceState(GameStateManager gsm, GameSettings settings, Player currentPlayer) {
        super(gsm, settings, currentPlayer);
        view.getButton("roll").setActive(true);
    }

    public void reinit(){}
    public View createView(){ return this.new View();}

    public void skip(){
        currentplayer.SetCardsUsable();
        this.gsm.removeState();
        this.gsm.addActiveState(new PlayerInterfaceState(gsm,settings,currentplayer.getNext()));
    }
    public void roll(){
        Random r = new Random();
        diceRolled=true;
        view.setActive();
        view.getButton("roll").setActive(false);
        settings.setDice(r.nextInt(6)+1,r.nextInt(6)+1);
        int dice=settings.getDice1()+settings.getDice2();
        if(dice==7){
            this.gsm.addActiveState(new PlaceRobberState(gsm,settings,currentplayer));
        }else {
            for (Tile t : settings.map.getBlocks()) {
                if (t.getLable() == dice && !t.hasStealer()) {
                    tilesRolled.add(t);
                    t.harnessRessource();
                }
            }
        }
    }
    public void buy(){
        currentplayer.buyDevCard();
    }
    public void build(){
        if(currentplayer.canBuildSettelment()) {
            this.gsm.addActiveState(new BuildState(gsm, settings, currentplayer));
            currentplayer.cutSettelmentMaterial();
        }
    }
    public void upgrade(){
        if(currentplayer.canBuildCity()) {
            this.gsm.addActiveState(new UpgradeState(gsm, settings, currentplayer));
            currentplayer.cutCityMaterial();
        }
    }
    public void road(){
        if(currentplayer.canBuildRoad()) {
            this.gsm.addActiveState(new RoadState(gsm, settings, currentplayer));
            currentplayer.cutRoadMaterial();
        }
    }


    public boolean vp(){
        if(currentplayer.useCard("vp") && view.getButton("vp").isActive()) {
            currentplayer.upgradeVP(1);
            view.setInactive(view.devcards);
            return true;
        }
        return false;
    }
    public boolean robber(){
        if(currentplayer.useCard("robber")&& view.getButton("robber").isActive()){
            this.gsm.addActiveState(new PlaceRobberState(gsm,settings,currentplayer));
            view.setInactive(view.devcards);
            return true;
        }
        return false;
    }
    public boolean expansion(){
        if(currentplayer.useCard("expansion") && view.getButton("expansion").isActive() ) {
            this.gsm.addState(new ExpansionState(gsm, settings, currentplayer));
            this.gsm.addActiveState(new ExpansionState(gsm, settings, currentplayer));
            view.setInactive(view.devcards);
            return true;
        }
        return false;
    }
    public boolean monopoly(){
        if(currentplayer.useCard("monopoly")&& view.getButton("monopoly").isActive()) {
            this.gsm.addActiveState(new MonopolyState(gsm,settings,currentplayer));
            view.setInactive(view.devcards);
            return true;
        }
        return false;
    }
    public boolean progress(){
        if(currentplayer.useCard("progress") && view.getButton("progress").isActive()) {
            this.gsm.addState(new ProgressState(gsm, settings, currentplayer));
            this.gsm.addActiveState(new ProgressState(gsm, settings, currentplayer));
            view.setInactive(view.devcards);
            return true;
        }
        return false;
    }
    public void ResCard(Ressources r){}

    public class View extends MainState.View{
        @Override
        public void render(Graphics graphics){
            super.render(graphics);
            for(Tile t : tilesRolled){
                Renderer.increaseBrightness(t.getPosX(),t.getPosY(),90,90,-55,graphics);
            }
        }
    }
}
