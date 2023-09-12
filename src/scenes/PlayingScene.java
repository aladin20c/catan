package scenes;

import Components.*;
import Jade.Window;
import renderer.Model;
import renderer.ModelManager;
import renderer.Button;
import renderer.Renderer;
import scenes.states.*;
import utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayingScene extends Scene{

    public Map map;
    public Player[] players;
    public Human human;
    public int currentPlayer;
    public int dice1;
    public int dice2;

    public ArrayList<Card> deck;


    public Rectangle canvas =new Rectangle(0,0, Window.get().getWidth()-200, Window.get().getHeight()-100);
    public Rectangle sideBar =new Rectangle(Window.get().getWidth()-200,0, 200,Window.get().getHeight()-100);
    public Rectangle bottomBar =new Rectangle(0, Window.get().getHeight()-100,Window.get().getWidth(),100);
    public ArrayList<Button> controls = new ArrayList<>();
    public ArrayList<Button> devCards = new ArrayList<>();
    public ArrayList<Button> resCards = new ArrayList<>();


    public int turn;
    public float time;
    public float waitingTime;

    public boolean diceRolled;
    public State state;


    public PlayingScene() {
        System.out.println("[playing]: playing...");
    }

    @Override
    public void init() {
        this.map=new Map(Settings.map,200,100,70);
        this.players=new Player[Settings.numberOfPlayers];
        this.dice1=1;
        this.dice2=1;
        this.currentPlayer=0;
        this.turn=0;
        this.diceRolled=false;
        switch (Settings.speed){
            case 1: this.waitingTime=20;break;
            case 2: this.waitingTime=30;break;
            default: this.waitingTime=40;break;
        }

        Color[] possible_colors=new Color[4];
        possible_colors[0]=Color.BLUE;
        possible_colors[1]=Color.RED;
        possible_colors[2]=Color.GREEN;
        possible_colors[3]=Color.YELLOW;
        Settings.shuffle(possible_colors);

        this.human=new Human("Human",possible_colors[0]);
        players[0]=human;
        for(int i=1;i< players.length;i++) {
            players[i]=new Bot("bot#"+i, possible_colors[i]);
        }
        Settings.shuffle(players);

        initButtons();

        this.deck=new ArrayList<>();
        for (int i=0;i<13;i++){
            deck.add(new Card(Card.robber));
        }
        for (int i=0;i<4;i++){
            deck.add(new Card(Card.vp));
        }
        for (int i=0;i<2;i++){
            deck.add(new Card(Card.expansion));
        }
        for (int i=0;i<2;i++){
            deck.add(new Card(Card.progress));
        }
        for (int i=0;i<2;i++){
            deck.add(new Card(Card.monopoly));
        }
        Collections.shuffle(deck);

        if(players[currentPlayer].isHuman()){
            ArrayList<Corner> corners=new ArrayList<>();
            for (Corner corner: map.getCorners()){
                if(corner.isBuildable) corners.add(corner);
            }
            setState(new ForcedSettlementState(this,corners));
        }else{
            setState(new WaitingState(this));
        }
        this.time= Time.getTime();
    }

    /*--------------------------------VIEW--------------------------------------------------*/


    public void initButtons() {
        //controls
        String[] controlNames = {"exit", "next", "roll", "buy", "build", "upgrade", "road"};


        int w = 50;
        int h = 50;
        int xStart = Window.get().getWidth() - sideBar.width- w - 10;
        int yStart = bottomBar.y + 10;
        int xOffset = (int) (w * 1.1f);


        for (int i = 0; i < controlNames.length; i++) {
            Button button = new Button(xStart - xOffset * i, yStart, w, h,controlNames[i],Color.gray,Color.lightGray);
            controls.add(button);
        }

        //devCards
        String[] devNames = {"vp", "robber", "expansion", "progress", "monopoly"};
        w = 33;
        h = 50;
        xStart = bottomBar.x + 10;
        yStart = bottomBar.y + 10;
        xOffset = (int) (w * 1.1f);

        for (int i = 0; i < devNames.length; i++) {
            Button button = new Button(xStart + xOffset * i, yStart, w, h,devNames[i],Color.gray,ModelManager.getmodel(devNames[i]));
            devCards.add(button);
        }

        //resCards
        String[] resNames = {"lumbercard", "brickcard", "wheatcard", "sheepcard", "orecard"};
        w = 33;
        h = 50;
        xStart = bottomBar.x + 10;
        yStart = bottomBar.y + 10;
        xOffset = (int) (w * 1.1f);

        for (int i = 0; i < resNames.length; i++) {
            Button button = new Button(xStart +devNames.length*xOffset+ xOffset * i, yStart, w, h,resNames[i],Color.gray,ModelManager.getmodel(resNames[i]));
            resCards.add(button);
        }
    }

    @Override
    public void render(Graphics graphics) {
        //background

        graphics.setColor(new Color(74, 83, 164));
        graphics.fillRect(this.canvas.x, this.canvas.y, this.canvas.width, this.canvas.height);

        graphics.setColor(new Color(16, 7, 7));
        graphics.fillRect(this.bottomBar.x, this.bottomBar.y, this.bottomBar.width, this.bottomBar.height);
        graphics.fillRect(this.sideBar.x,this.sideBar.y,this.sideBar.width,this.sideBar.height);

        //map
        if(map!=null) this.map.render(graphics);

        //render buttons

        for (Button button : controls) {
            button.render(graphics);
            Renderer.drawCenteredText(graphics, button.getText(), button, 15);
        }
        for (Button button : resCards) {
            button.render(graphics);
            switch (button.getText()){
                case "lumbercard": Renderer.drawBottomRightedText(graphics,""+human.lumber,button,20);break;
                case  "brickcard":Renderer.drawBottomRightedText(graphics,""+human.brick,button,20);break;
                case  "wheatcard":Renderer.drawBottomRightedText(graphics,""+human.wheat,button,20);break;
                case  "sheepcard":Renderer.drawBottomRightedText(graphics,""+human.sheep,button,20);break;
                case  "orecard":Renderer.drawBottomRightedText(graphics,""+human.ore,button,20);break;
            }
        }
        for (Button button : devCards) {
            button.render(graphics);
            Renderer.drawBottomRightedText(graphics,""+human.countDevCards(button.getText()),button,20);
        }

        //render players
        if(this.players!=null) {
            for (int i = 0; i < Settings.numberOfPlayers; i++) {
                int x = sideBar.x + 30;
                int y = sideBar.y + i * 80 + 30;
                Rectangle rectangle = new Rectangle(x - 10, y - 10, 70, 70);

                if (i == currentPlayer) {
                    graphics.setColor(Color.gray);
                    graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }

                Model m = players[i].getModel();
                Renderer.renderModel(m, x, y, 50, 50, graphics);
                graphics.setColor(Color.red);
                if(players[i].isBot()) {
                    Renderer.drawBottomRightedText(graphics, "" + players[i].VP, rectangle, 20);
                }else{
                    Renderer.drawBottomRightedText(graphics,  players[i].VP+"("+players[i].SVP+")", rectangle, 20);
                }

                //stats rendering
                int xRecStart = rectangle.x + rectangle.width;
                int yRecStart = rectangle.y;
                int w = 20;
                int h = 35;
                Rectangle[] rectangles = new Rectangle[4];
                for (int j = 0; j < rectangles.length; j++) {
                    rectangles[j] = new Rectangle(xRecStart, yRecStart, w, h);
                    xRecStart += w + 2;
                }
                int[] values = {players[i].countResources(), players[i].countDevCards(), players[i].longestPath, players[i].robbersUsed};
                Color[] colors = {new Color(172, 250, 100), new Color(234, 193, 77), new Color(221, 240, 250), new Color(248, 205, 205)};

                boolean[] visible = {true, true, players[i].hasLongestRoad, players[i].hasLargestArmy};

                for (int j = 0; j < rectangles.length; j++) {
                    graphics.setColor(colors[j]);
                    if (visible[j]) {
                        graphics.drawRect(rectangles[j].x, rectangles[j].y, rectangles[j].width, rectangles[j].height);
                    }
                    Renderer.drawCenteredText(graphics, "" + values[j], rectangles[j], 20);
                }
            }
        }

        //render dice
        int y_dice = Window.get().getHeight() - bottomBar.height - 60;
        int x_dice_1 = Window.get().getWidth() - sideBar.width - 120;
        int x_dice_2 = Window.get().getWidth() - sideBar.width - 60;
        Renderer.renderModel(ModelManager.getmodel("de" + dice1), x_dice_1, y_dice,50,50, graphics);
        Renderer.renderModel(ModelManager.getmodel("de" + dice2), x_dice_2, y_dice, 50,50,graphics);

        this.state.render(graphics);
    }

    /*--------------------------------controls--------------------------------------------------*/

    @Override
    public void update() {

        if(players!=null) {
            if (players[currentPlayer].isBot()) {
                if ((Time.getTime() - time ) >= Settings.speed) {
                    players[currentPlayer].applyStrategy(this);
                    nextPlayer();
                }
            }
        }
    }


    public void rollDice(){
        this.diceRolled=true;
        Random random=new Random();
        this.dice1=1+random.nextInt(6);
        this.dice2=1+random.nextInt(6);
        int label=dice1+dice2;

        if(label==7){
            if(players[currentPlayer].isHuman()){
                this.setState(new ForcedPlaceRobberState(this));
            }
        }else{
            for (Tile tile : map.getTiles()){
                if(tile.lable==label){
                    for (Corner corner:tile.getCorners()){
                        if(corner.hasCity){
                            corner.player.addResources(tile.resource,2);
                        }else if(corner.hasSettlement){
                            corner.player.addResources(tile.resource,1);
                        }
                    }
                }
            }
            if(players[currentPlayer].isHuman()){
                this.setState(new MainState(this));
            }
        }
        //update timer//TODO
    }


    public void nextPlayer(){
        this.diceRolled=false;
        players[currentPlayer].setCardsUsable();

        if(this.turn==0){

            currentPlayer=(currentPlayer+1)%Settings.numberOfPlayers;
            if(currentPlayer==0){
                turn++;
                currentPlayer=Settings.numberOfPlayers-1;
            }

        }else if(this.turn==1){

            currentPlayer-=1;
            if(currentPlayer==-1){
                currentPlayer=0;
                turn++;
            }

        }else{

            currentPlayer=(currentPlayer+1)%Settings.numberOfPlayers;
            if(currentPlayer==0){
                turn++;
            }

        }

        if(this.turn<=1){

            if(players[currentPlayer].isBot()){
                if(!(state instanceof WaitingState)) {
                    setState(new WaitingState(this));
                }
            }else{
                ArrayList<Corner> corners=new ArrayList<>();
                for (Corner corner: map.getCorners()){
                    if(corner.isBuildable) corners.add(corner);
                }
                setState(new ForcedSettlementState(this,corners));
            }


        }else{

            if(players[currentPlayer].isBot()){
                if(!(state instanceof WaitingState)) {
                    setState(new WaitingState(this));
                }
            }else{
                setState(new RollingState(this));
            }


        }
        checkWin();
        this.time=Time.getTime();
    }

    public void setState(State state){
        this.state=state;
    }

    public void checkWin(){
        for (Player player : this.players){
            if(player.SVP>=10){
                Window.changeScene(3);
            }
        }
    }


    //___________________________________________________________________________________________________________
    @Override
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            Window.changeScene(31);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        int x=e.getX();
        int y=e.getY();

        for(Button button : controls){
            if(button.contains(x,y) && button.active){
                this.state.buttonClicked(button.getText());
                return;
            }
        }
        for(Button button : devCards){
            if(button.contains(x,y) && button.active){
                this.state.buttonClicked(button.getText());
                return;
            }
        }
        for(Button button : resCards){
            if(button.contains(x,y) && button.active){
                this.state.buttonClicked(button.getText());
                return;
            }
        }
        for(Button button : this.state.buttons){
            if(button.contains(x,y) && button.active){
                this.state.buttonClicked(button.getText());
                return;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();

        for(Button button : controls){
            button.setMouseOver(false);
        }

        for(Button button : controls){
            if(button.contains(x,y) && button.active){
                button.setMouseOver(true);
                return;
            }
        }

        for(Button button : devCards){
            button.setMouseOver(false);
        }
        for(Button button : devCards){
            if(button.contains(x,y) && button.active){
                button.setMouseOver(true);
                return;
            }
        }

        for(Button button : resCards){
            button.setMouseOver(false);
        }
        for(Button button : resCards){
            if(button.contains(x,y) && button.active){
                button.setMouseOver(true);
                return;
            }
        }

        for(Button button : this.state.buttons){
            button.setMouseOver(false);
        }
        for(Button button : this.state.buttons){
            if(button.contains(x,y) && button.active){
                button.setMouseOver(true);
                return;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        for(Button button : controls){
            if(button.contains(x,y) && button.active){
                button.setMousePressed(true);
                return;
            }
        }
        for(Button button : devCards){
            if(button.contains(x,y) && button.active){
                button.setMousePressed(true);
                return;
            }
        }
        for(Button button : resCards){
            if(button.contains(x,y) && button.active){
                button.setMousePressed(true);
                return;
            }
        }
        for(Button button : this.state.buttons){
            if(button.contains(x,y) && button.active){
                button.setMousePressed(true);
                return;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for(Button button : controls){
            button.resetBooleans();
        }
        for(Button button : devCards){
            button.resetBooleans();
        }
        for(Button button : resCards){
            button.resetBooleans();
        }
        for(Button button : this.state.buttons){
            button.resetBooleans();
        }
    }

}
