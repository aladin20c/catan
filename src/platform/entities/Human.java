package platform.entities;
import platform.render.ModelManager;
import platform.states.GameState;
import platform.states.mainstates.*;
import platform.utils.Ressources;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Human extends Player{
    public Human(String name, Color color) {
        super(name, color);
        if( color.equals(Color.GREEN ))this.module= ModelManager.getmodel("human_green");
        else if( color.equals(Color.BLUE ))this.module= ModelManager.getmodel("human_blue");
        else if( color.equals(Color.RED) ) this.module= ModelManager.getmodel("human_red");
        else if( color.equals(Color.YELLOW )) this.module= ModelManager.getmodel("human_yellow");
        for(int i=0;i<10;i++) {
            addCard("vp");
            addCard("robber");
            addCard("expansion");
            addCard("progress");
            addCard("monopoly");
        }
    }
    public boolean isHuman(){return true;}
    public boolean isBot(){return false;}
    @Override
    public void applyStrategy(GameState gameState){}
}
