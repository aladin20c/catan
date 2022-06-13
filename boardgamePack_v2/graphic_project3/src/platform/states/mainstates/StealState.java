package platform.states.mainstates;

import platform.DisplayJframe;
import platform.GameSettings;
import platform.entities.Player;
import platform.render.ModelManager;
import platform.states.GameStateManager;
import platform.utils.CustomButton;
import platform.utils.SelectButton;

import java.awt.*;
import java.util.ArrayList;


public class StealState extends MainState{

    private ArrayList<CustomButton> playerButtons;

    public StealState(GameStateManager gsm, GameSettings settings, Player currentPlayer, ArrayList<Player> players) {
        super(gsm, settings, currentPlayer);
        initPlayersButtons(players);
        System.out.println("[GameStates][StealStateState]: Creating steal State state...");
    }

    public void reinit(){}
    public StealState.View createView(){return this.new View();}

    private void initPlayersButtons(ArrayList<Player> players) {
        playerButtons=new ArrayList<>();
        int w = 50;
        int h = 50;
        int xStart = ((View)this.view).choicebar.x+10;
        int yStart = ((View)this.view).choicebar.y+10;
        int xOffset = (int) (w * 1.1f);
        int i=0;
        for( Player p: players){
            CustomButton cb=new CustomButton(p.getModule(),p.name,xStart + xOffset * i, yStart, w, h);
            playerButtons.add(cb);
            i++;
        }
    }

    public void playerClicked(String s){
        for(Player p : settings.getPlayers()){
            if(p.name.equals(s)) p.giveResCardTo(currentplayer);
        }
        this.gsm.deleteState();
        this.gsm.deleteState();
    }

    public ArrayList<CustomButton> getPlayerButtons() {
        return playerButtons;
    }

    public class View extends MainState.View {
        private Rectangle choicebar;
        public View() {
            choicebar=new Rectangle(0, DisplayJframe.HEIGHT - 2*bottomBar.height, DisplayJframe.WIDTH-sideBar.width, bottomBar.height);
        }
        @Override
        public void render(Graphics graphics){
            super.render(graphics);
            graphics.setColor(new Color(231, 219, 193));
            graphics.fillRect(choicebar.x,choicebar.y,choicebar.width,choicebar.height);
            for(CustomButton pb : playerButtons) pb.render(graphics);
        }

        @Override
        public void mouseClicked( int x, int y) {
            super.mouseClicked(x,y);
            for (CustomButton cb : playerButtons) {
                if (cb.contains(x, y)) {
                    playerClicked(cb.getName());
                }
            }
        }
        @Override
        public void mouseMoved( int x, int y) {
            super.mouseMoved(x,y);
            for (CustomButton cb : playerButtons) cb.setMouseOver(false);
                for (CustomButton cb : playerButtons) {
                if (cb.contains(x, y)) cb.setMouseOver(true);
            }
        }
        @Override
        public void mousePressed( int x, int y) {
            super.mousePressed(x,y);
            for (CustomButton cb : playerButtons) {
                if (cb.contains(x, y)) cb.setMousePressed(true);
            }
        }
        @Override
        public void mouseReleased( int x, int y) {
            super.mouseReleased(x,y);
            for (CustomButton cb : playerButtons)  cb.resetBooleans();
        }
    }

}
