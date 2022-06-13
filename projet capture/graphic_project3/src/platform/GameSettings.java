package platform;

import platform.entities.Bot;
import platform.entities.Human;
import platform.entities.Map;
import platform.entities.Player;
import platform.observers.Vpobserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameSettings {

    public final static int SPAN=30;
    public final static int MAXPLAYERS=4;
    public int humans;
    public int bots;
    public int mapType;
    public Map map;
    public Player[] players;
    private int dice_1 = 1;
    private int dice_2 = 1;

    public GameSettings() {
        this.humans = 1;
        this.bots = 2;
        this.mapType = 1;
    }

    public void init(){
        players = new Player[humans+bots];
        ArrayList<Color> possible_colors=new ArrayList<>();
        possible_colors.add(Color.BLUE);
        possible_colors.add(Color.RED);
        possible_colors.add(Color.GREEN);
        possible_colors.add(Color.YELLOW);

        Random r =new Random();

        for(int i=0;i<humans;i++) {
            int n= r.nextInt(possible_colors.size());
            Color c = possible_colors.get(n);
            players[i]=new Human("human#"+i, c);
            possible_colors.remove(c);
        }

        for(int i=0;i<bots;i++) {
            int n= r.nextInt(possible_colors.size());
            Color c = possible_colors.get(n);
            players[i+humans] = new Bot("bot#"+i+"", c);
            possible_colors.remove(c);
        }
        shuffle(players);
        for(int i=0;i<players.length;i++){
            players[i].setNext(players[(i+1)% players.length]);
        }
        this.map=new Map(mapType);
    }

    private static void shuffle(Player[] array) {
        Random random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            //swap(array, i - 1, random.nextInt(i));
            int r = random.nextInt(i);
            Player tmp = array[i - 1];
            array[i - 1] = array[r];
            array[r] = tmp;
        }
    }

    public int getHumans() {return humans;}
    public void setHumans(int humans) {this.humans = humans;}
    public int getBots() {return bots;}
    public void setBots(int bots) {this.bots = bots;}
    public int getMapType() {return mapType;}
    public void setMapType(int map) {this.mapType = map;}
    public Player[] getPlayers() {return players;}
    public void setDice(int dice_1,int dice_2) {
        this.dice_1 = dice_1;
        this.dice_2 = dice_2;
    }

    public int getDice1() {
        return dice_1;
    }
    public int getDice2() {
        return dice_2;
    }
}
