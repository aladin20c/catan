package platform.states.mainstates;

import platform.DisplayJframe;
import platform.GameSettings;
import platform.entities.Corner;
import platform.entities.Player;
import platform.entities.Side;
import platform.entities.Tile;
import platform.render.Model;
import platform.render.ModelManager;
import platform.render.Renderer;
import platform.states.GameOver;
import platform.states.GameState;
import platform.states.GameStateManager;
import platform.utils.CustomButton;
import platform.utils.Ressources;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class MainState extends GameState {

    public final static Ressources[] ressources={Ressources.LUMBER,Ressources.BRICK,Ressources.WHEAT,Ressources.SHEEP,Ressources.ORE};
    protected View view;

    public MainState(GameStateManager gsm, GameSettings settings,Player currentPlayer) {
        super(gsm, settings,currentPlayer);
        this.view=createView();
        this.view.setInactive();
    }
    public abstract View createView();

    @Override
    public void applyStrategy(){
        currentplayer.applyStrategy(this);
    }

    @Override
    public void render(Graphics graphics) {
        view.render(graphics);
    }

    @Override
    public void mouseClicked( int x, int y) {view.mouseClicked(x,y);}
    @Override
    public void mouseMoved( int x, int y) {view.mouseMoved(x,y);}
    @Override
    public void mousePressed( int x, int y) {view.mousePressed(x,y);}
    @Override
    public void mouseReleased( int x, int y) {view.mouseReleased(x,y);}


    public void exit(){this.gsm.addState(new GameOver(gsm,settings));}
    public void skip(){}
    public void roll(){}
    public void buy(){}
    public void build(){}
    public void upgrade(){}
    public void road(){}


    public boolean vp(){return false;}
    public boolean robber(){return false;}
    public boolean expansion(){return false;}
    public boolean monopoly(){return false;}
    public boolean progress(){return false;}


    public void ResCard(Ressources r){}



    public abstract class View{
        protected ArrayList<CustomButton> controls = new ArrayList<>();
        protected ArrayList<CustomButton> devcards = new ArrayList<>();
        protected ArrayList<CustomButton> rescards = new ArrayList<>();
        protected Rectangle bottomBar;
        protected Rectangle sideBar;


        public View() {
            bottomBar=new Rectangle(0, DisplayJframe.HEIGHT - 90, DisplayJframe.WIDTH, 90);
            sideBar=new Rectangle(DisplayJframe.WIDTH-190, 0, 190, DisplayJframe.HEIGHT-bottomBar.height);
            initControls();
            initDevCards();
            initResCards();
        }
        private void initControls() {
            String[] controlnames={"exit","skip","roll","buy","build","upgrade","road"};
            String[] controlmodels={"","","","",ModelManager.SETTELMENT,ModelManager.CITY,ModelManager.ROAD};

            int w = 50;
            int h = 50;
            int xStart = bottomBar.x+ bottomBar.width-w;
            int yStart = bottomBar.y+10;
            int xOffset = (int) (w * 1.1f);

            for(int i=0;i<controlnames.length;i++){
                CustomButton cb=new CustomButton(ModelManager.getmodel(controlmodels[i]),controlnames[i],xStart - xOffset * i, yStart, w, h);
                controls.add(cb);
            }
        }
        public void initDevCards(){
            String[] devnames={"vp","robber","expansion","progress","monopoly"};
            int h = 50;
            int w=33;
            int xStart = bottomBar.x;
            int yStart = bottomBar.y+10;
            int xOffset = (int) (w * 1.1f);

            for(int i=6;i<devnames.length+6;i++){
                int j=i-6;
                CustomButton cb= new CustomButton(ModelManager.getmodel(devnames[j]),devnames[j],xStart+ i * xOffset,yStart,w,h);
                devcards.add(cb);
            }
        }
        public void initResCards(){
            String[] resnames={"lumbercard","brickcard","wheatcard","sheepcard","orecard"};
            int h = 50;
            int w=33;
            int xStart = bottomBar.x+10;
            int yStart = bottomBar.y+10;
            int xOffset = (int) (w * 1.1f);

            for(int i=0;i<resnames.length;i++){
                CustomButton cb= new CustomButton(ModelManager.getmodel(resnames[i]),resnames[i],xStart+ i * xOffset,yStart,w,h);
                rescards.add(cb);
            }
        }


        public void mouseClicked(int x, int y){
            for(CustomButton cb : controls){
                if(cb.contains(x,y) && cb.isActive()){
                    cardAction(cb.getName());
                    break;
                }
            }
            for(CustomButton cb : devcards) {
                if (cb.contains(x, y) && cb.isActive()) {
                    if (cb.contains(x, y)) {
                        cardAction(cb.getName());
                        break;
                    }
                }
            }
            for(CustomButton cb : rescards) {
                if (cb.contains(x, y) && cb.isActive()) {
                    if (cb.contains(x, y)) {
                        cardAction(cb.getName());
                        break;
                    }
                }
            }
        }
        public void mouseMoved(int x, int y) {
            for(CustomButton b: controls) {
                b.setMouseOver(false);
            }
            for(CustomButton b:controls) {
                if (b.contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }

            for(CustomButton b: devcards) {
                b.setMouseOver(false);
            }
            for(CustomButton b:devcards) {
                if (b.contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }

            for(CustomButton b: rescards) {
                b.setMouseOver(false);
            }
            for(CustomButton b:rescards) {
                if (b.contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
        public void mousePressed(int x, int y) {
            for(CustomButton b:controls) {
                if (b.contains(x, y)) {
                    b.setMousePressed(true);
                }
            }
            for(CustomButton b:devcards) {
                if (b.contains(x, y)) {
                    b.setMousePressed(true);
                }
            }
            for(CustomButton b:rescards) {
                if (b.contains(x, y)) {
                    b.setMousePressed(true);
                }
            }
        }
        public void mouseReleased(int x, int y) {
            for(CustomButton b:controls) {
                b.resetBooleans();
            }
            for(CustomButton b:devcards) {
                b.resetBooleans();
            }
            for(CustomButton b:rescards) {
                b.resetBooleans();
            }
        }

        public void setInactive(){
            for (CustomButton cb : controls) cb.setActive(false);
            for (CustomButton cb : rescards) cb.setActive(false);
            for (CustomButton cb : devcards) cb.setActive(false);
            getButton("exit").setActive(true);
        }
        public void setActive(){
            for (CustomButton cb : controls) cb.setActive(true);
            for (CustomButton cb : rescards) cb.setActive(true);
            for (CustomButton cb : devcards) cb.setActive(true);
        }
        public void setActive(ArrayList<CustomButton> a){
            for (CustomButton cb : a) cb.setActive(true);
        }
        public void setInactive(ArrayList<CustomButton> a){
            for (CustomButton cb : a) cb.setActive(false);
        }
        public void cardAction(String cb) {
            switch (cb) {
                case "exit":exit();break;
                case "skip":skip();break;
                case "roll":roll();break;
                case "buy":buy();break;
                case "build":build();break;
                case "upgrade":upgrade();break;
                case "road":road();break;

                case "lumbercard":ResCard(Ressources.LUMBER);break;
                case "brickcard":ResCard(Ressources.BRICK);break;
                case "wheatcard":ResCard(Ressources.WHEAT);break;
                case "sheepcard":ResCard(Ressources.SHEEP);break;
                case "orecard":ResCard(Ressources.ORE);break;

                case "vp":vp();break;
                case "robber":robber();break;
                case "expansion":expansion();break;
                case "progress":progress();break;
                case "monopoly":monopoly();break;

            }
        }

        public void render(Graphics graphics) {
            for (Tile block : settings.map.getBlocks()) {
                block.render(graphics);
            }
            for (Corner corner : settings.map.getCorners()) {
                corner.render(graphics);
            }
            for (Side side : settings.map.getSides()) {
                side.render(graphics);
            }
            // Background
            graphics.setColor(new Color(24, 34, 37));
            graphics.fillRect(bottomBar.x, bottomBar.y, bottomBar.width, bottomBar.height);
            graphics.fillRect(sideBar.x, sideBar.y, sideBar.width, sideBar.height);
            renderButtons(graphics);
            renderPlayers(graphics);
            renderDice(graphics);
        }
        public void renderButtons(Graphics graphics) {
            for (CustomButton cb : controls) cb.render(graphics);
            for(int i=0;i<rescards.size();i++){
                CustomButton cb = rescards.get(i);
                cb.render(graphics);
                String txt = currentplayer.count(ressources[i]) + "";
                Renderer.drawTextInBottomRight(txt, graphics, cb);
            }
            for (CustomButton cb : devcards) {
                cb.render(graphics);
                String txt = currentplayer.count(cb.getName()) + "";
                Renderer.drawTextInBottomRight(txt, graphics, cb);
            }
        }
        public void renderPlayers(Graphics graphics) {
            for (int i = 0; i < settings.getPlayers().length; i++) {
                int x = sideBar.x + 30;
                int y = sideBar.y + i * 80 + 30;
                Rectangle rec = new Rectangle(x - 10, y - 10, settings.getPlayers()[i].getModule().width + 20, settings.getPlayers()[i].getModule().height + 20);

                if (settings.getPlayers()[i] == currentplayer) {
                    graphics.setColor(Color.gray);
                    graphics.fillRect(rec.x, rec.y, rec.width, rec.height);
                }
                Model m = settings.getPlayers()[i].getModule();
                Renderer.renderModel(m, x, y, graphics);
                Renderer.drawTextInBottomRight(""+settings.getPlayers()[i].getVP(),graphics,rec);



                //stats rendering
                int xRecStart=rec.x+rec.width;
                int yRecStart=rec.y;
                int w=20;
                int h=35;
                Rectangle[] rectangles = new Rectangle[4];
                for(int j=0;j<rectangles.length;j++){
                    rectangles[j]=new Rectangle(xRecStart,yRecStart,w,h);
                    xRecStart += w + 2;
                }
                int[] valeurs={settings.getPlayers()[i].countressources(),settings.getPlayers()[i].countdevcards(),settings.getPlayers()[i].getRoadsBuilt(),settings.getPlayers()[i].getRobbersUsed()};
                Color[] colors={new Color(172, 250, 100),new Color(234, 193, 77),new Color(221, 240, 250),new Color(248, 205, 205)};
                boolean[] visible={true,true,settings.getPlayers()[i].hasLongestRoad(),settings.getPlayers()[i].hasLArgestArmy()};

                for(int j=0;j<rectangles.length;j++){
                    if(visible[j])Renderer.drawRect(rectangles[j],colors[j],graphics);
                    Renderer.drawTextInCenter(rectangles[j],valeurs[j],18,colors[j],graphics);
                }

            }
        }
        public void renderDice(Graphics graphics) {
            int y_dice = DisplayJframe.HEIGHT - bottomBar.height - 60;
            int x_dice_1 = DisplayJframe.WIDTH - sideBar.width - 120;
            int x_dice_2 = DisplayJframe.WIDTH - sideBar.width - 60;
            Renderer.renderModel(ModelManager.getmodel("de" + settings.getDice1()), x_dice_1, y_dice, graphics);
            Renderer.renderModel(ModelManager.getmodel("de" + settings.getDice2()), x_dice_2, y_dice, graphics);
        }
        public CustomButton getButton(String s){
            for (CustomButton cb : controls) {
                if(cb.getName().equals(s)) return cb;
            }
            for (CustomButton cb : rescards) {
                if(cb.getName().equals(s)) return cb;
            }
            for (CustomButton cb : devcards) {
                if(cb.getName().equals(s)) return cb;
            }
            return null;
        }
    }

}

