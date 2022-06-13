package platform.entities;
import platform.render.ModelManager;
import platform.states.GameState;

import java.awt.*;

public class Human extends Player{
    public Human(String name, Color color) {
        super(name, color);
        if( color.equals(Color.GREEN ))this.module= ModelManager.getmodel("human_green");
        else if( color.equals(Color.BLUE ))this.module= ModelManager.getmodel("human_blue");
        else if( color.equals(Color.RED) ) this.module= ModelManager.getmodel("human_red");
        else if( color.equals(Color.YELLOW )) this.module= ModelManager.getmodel("human_yellow");
    }
    public boolean isHuman(){return true;}
    public boolean isBot(){return false;}
    @Override
    public void applyStrategy(GameState gameState){}
}
