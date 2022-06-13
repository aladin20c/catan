package platform.entities;

import platform.render.ModelManager;
import platform.states.GameState;
import platform.states.mainstates.*;

import java.awt.*;
import java.util.Random;


public class Bot extends Player{
    public Bot(String name, Color color) {
        super(name, color);
            if( color.equals(Color.GREEN ))this.module= ModelManager.getmodel("bot_green");
            else if( color.equals(Color.BLUE))this.module= ModelManager.getmodel("bot_blue");
            else if( color.equals(Color.RED )) this.module= ModelManager.getmodel("bot_red");
            else if( color.equals(Color.YELLOW)) this.module= ModelManager.getmodel("bot_yellow");
    }
    public boolean isHuman(){return true;}
    public boolean isBot(){return false;}

    public void applyStrategy(GameState gameState) {
        Random random=new Random();
        if(gameState instanceof PlaceSettelmentState){

            int r= random.nextInt(((PlaceSettelmentState) gameState).getTargets().size());
            ((PlaceSettelmentState) gameState).clicked(((PlaceSettelmentState) gameState).getTargets().get(r));

        }else if(gameState instanceof PlaceRoadState){

            int r= random.nextInt(((PlaceRoadState) gameState).getTargets().size());
            ((PlaceRoadState) gameState).clicked(((PlaceRoadState) gameState).getTargets().get(r));

        }else if(gameState instanceof PlayerInterfaceState){

            PlayerInterfaceState g=((PlayerInterfaceState) gameState);
            if(!g.diceRolled)g.roll();
            boolean res = g.robber() || g.vp() || g.expansion() || g.progress()|| g.monopoly();
            g.upgrade();
            g.build();
            //g.road();
            g.buy();
            g.skip();

        }else if(gameState instanceof BuildState){

            int r= random.nextInt(((BuildState) gameState).getTargets().size());
            ((BuildState) gameState).clicked(((BuildState) gameState).getTargets().get(r));

        }else if(gameState instanceof RoadState){

            int r= random.nextInt(((RoadState) gameState).getTargets().size());
            ((RoadState) gameState).clicked(((RoadState) gameState).getTargets().get(r));

        }else if(gameState instanceof UpgradeState){

            int r= random.nextInt(((UpgradeState) gameState).getTargets().size());
            ((UpgradeState) gameState).clicked(((UpgradeState) gameState).getTargets().get(r));

        }else if(gameState instanceof PlaceRobberState){

            int r= random.nextInt(((PlaceRobberState) gameState).getTargets().size());
            ((PlaceRobberState) gameState).clicked(((PlaceRobberState) gameState).getTargets().get(r));

        }else if(gameState instanceof StealState){

            int r= random.nextInt(((StealState) gameState).getPlayerButtons().size());
            ((StealState) gameState).playerClicked(((StealState) gameState).getPlayerButtons().get(r).getName());

        }else if(gameState instanceof MonopolyState){

            int r= random.nextInt(5);
            ((MonopolyState) gameState).ResCard(MainState.ressources[r]);

        }else if(gameState instanceof ProgressState){

            int r= random.nextInt(5);
            ((ProgressState) gameState).ResCard(MainState.ressources[r]);

        }else if(gameState instanceof ExpansionState){

            int r= random.nextInt(((ExpansionState) gameState).getTargets().size());
            ((ExpansionState) gameState).clicked(((ExpansionState) gameState).getTargets().get(r));
        }
    }

}

/**/