package platform.states.mainstates;

import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.entities.Side;
import platform.entities.Tile;
import platform.states.GameStateManager;
import platform.utils.SelectButton;

import java.awt.*;
import java.util.ArrayList;


public abstract class SelectState<T> extends MainState {
    protected ArrayList<SelectButton<T>> targets;

    public SelectState(GameStateManager gsm, GameSettings settings, Player player) {
        super(gsm,settings,player);
        inittargets();
    }


    public abstract void inittargets();
    public void reinit(){inittargets();}
    public abstract void clicked(SelectButton<T> target);
    @Override
    public void render(Graphics graphics) {
        view.render(graphics);
    }
    public ArrayList<SelectButton<T>> getTargets() {return targets;}





    public class View extends MainState.View{

        @Override
        public void render(Graphics graphics) {
            super.render(graphics);
            for (SelectButton s : targets) s.render(graphics);
        }
        @Override
        public void mouseClicked( int x, int y) {
            super.mouseClicked(x,y);
            for (SelectButton<T> s : targets) {
                if (s.contains(x, y)) {
                    clicked(s);
                }
            }
        }
        @Override
        public void mouseMoved( int x, int y) {
            super.mouseMoved(x,y);
            for (SelectButton s : targets) s.setMouseOver(false);
            for (SelectButton s : targets) {
                if (s.contains(x, y)) s.setMouseOver(true);
            }
        }
        @Override
        public void mousePressed( int x, int y) {
            super.mousePressed(x,y);
            for (SelectButton s : targets) {
                if (s.contains(x, y)) s.setMousePressed(true);
            }
        }
        @Override
        public void mouseReleased( int x, int y) {
            super.mouseReleased(x,y);
            for (SelectButton s : targets) s.resetBooleans();
        }
    }

}
