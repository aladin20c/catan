package Components;


import observers.RoadObserver;
import observers.RobberObserver;
import renderer.Model;
import renderer.ModelManager;
import scenes.PlayingScene;
import scenes.Settings;
import utils.Resource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public abstract class Player {

    public String name;
    public Color color;

    public int SVP,VP,robbersUsed;
    public int brick,lumber,wheat,sheep,ore;
    public int longestPath,ways,cities,settlements;
    public boolean hasLongestRoad,hasLargestArmy;

    public ArrayList<Card> cards;
    public ArrayList<Corner> buildings ;
    public ArrayList<Side> roads;



    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.VP = 0;
        this.SVP = 0;
        this.robbersUsed = 0;
        this.brick = 0;
        this.lumber = 0;
        this.longestPath = 0;
        this.wheat = 0;
        this.sheep = 0;
        this.ore = 0;
        this.ways = 0;
        this.cities = 0;
        this.settlements = 0;
        this.hasLongestRoad = false;
        this.hasLargestArmy = false;
        this.cards =new ArrayList<>();
        this.buildings =new ArrayList<>();
        this.roads =new ArrayList<>();
    }

    public abstract boolean isHuman();
    public abstract boolean isBot();
    public abstract void applyStrategy(PlayingScene scene);


    public ArrayList<Corner> getPotentialSettlements(){
        if(buildings.size()<=1){
            /*an indication that you can build anywhere buildable*/
            return null;
        }
        ArrayList<Corner> res=new ArrayList<>();
        if(this.settlements>= Settings.MAXSETTLEMENTS){
            return res;
        }
        for(Side side : roads){
            if(side.corner1.isBuildable && !res.contains(side.corner1))res.add(side.corner1);
            if(side.corner2.isBuildable && !res.contains(side.corner2))res.add(side.corner2);
        }
        return res;
    }

    public ArrayList<Side> getPotentialRoads(){
        ArrayList<Side> res=new ArrayList<>();
        if(this.ways>= Settings.MAXROADS){
            return res;
        }
        for(Corner corner: buildings){
            for(Side side :corner.getSides()){
                if(side!=null) {
                    if (side.isBuildable && !res.contains(side)) res.add(side);
                }
            }
        }
        for(Side side:roads){
            if(side.corner1.player==null || side.corner1.player==this) {
                for (Side s1 : side.corner1.getSides()) {
                    if (s1 != null) {
                        if (s1.isBuildable && !res.contains(s1)) res.add(s1);
                    }
                }
            }
            if(side.corner2.player==null || side.corner2.player==this) {
                for (Side s1 : side.corner2.getSides()) {
                    if (s1 != null) {
                        if (s1.isBuildable && !res.contains(s1)) res.add(s1);
                    }
                }
            }
        }
        return res;
    }

    public ArrayList<Corner> getPotentialCities(){
        ArrayList<Corner> res=new ArrayList<>();
        if(this.cities>= Settings.MAXCITIES){
            return res;
        }
        for(Corner corner: buildings){
            if(corner.hasSettlement) res.add(corner);
        }
        return res;
    }

    public void buildSettlement(Corner corner,boolean bool){
        corner.setSettlement(this);
        this.buildings.add(corner);
        increaseVP();
        this.settlements++;
        if(bool) {
            this.lumber--;
            this.brick--;
            this.wheat--;
            this.sheep--;
        }
        RoadObserver.checkLongestRoads(corner);
    }


    public void buildCity(Corner corner,boolean bool){
        corner.setCity();
        this.settlements--;
        this.cities++;
        increaseVP();
        if(bool) {
            this.wheat -= 2;
            this.ore -= 3;
        }
    }

    public void buildRoad(Side side,boolean bool){
        side.setRoad(this);
        this.ways++;
        this.roads.add(side);
        if(bool) {
            this.lumber--;
            this.brick--;
        }
        RoadObserver.checkLongestRoads(side);
    }

    public void buyDevCard(ArrayList<Card> deck,boolean bool){
        Random r =new Random();
        int probability=r.nextInt(deck.size());
        Card card= deck.get(probability);
        deck.remove(card);
        cards.add(card);
        card.setCanBeUsed(false);
        if(card.getName().equals("vp")){
            increaseSVP();
        }
        this.wheat--;
        this.wheat--;
        this.ore--;
    }

    public boolean canBuyDevCard(ArrayList<Card> deck){
        return this.ore>0 && this.wheat>0 && this.sheep>0 && !deck.isEmpty();
    }

    public boolean canBuildRoad(ArrayList<Side> sides){
        return this.lumber>0 && this.brick>0 && !sides.isEmpty();
    }

    public boolean canBuildSettlement(ArrayList<Corner> corners){
        return this.lumber>0 && this.brick>0 && this.wheat>0 && this.sheep>0 && !corners.isEmpty();
    }

    public boolean canBuildCity(ArrayList<Corner> corners){
        return this.wheat>1  && this.ore>2 && !corners.isEmpty();
    }

    public boolean canUseCard(String string){
        for (Card card : cards){
                if(card.getName().equals(string) && card.canBeUsed()){
                    return true;
                }
        }
        return false;
    }

    public void useCard(String string){
        for (Card card : cards){
            if(card.getName().equals(string) && card.canBeUsed()){
                if(string.equals("robber")){
                    this.robbersUsed++;
                    RobberObserver.checkRobberUsed();
                }
                cards.remove(card);
                this.setCardsNonUsable();
                return;
            }
        }
    }

    public void addResources(Resource resource, int n){
        switch (resource){
            case BRICK : this.brick+=n;break;
            case LUMBER : this.lumber+=n;break;
            case WHEAT : this.wheat+=n;break;
            case SHEEP : this.sheep+=n;break;
            case ORE : this.ore+=n;break;
        }
     }

     public void setCardsUsable(){
        for(Card card: cards) card.setCanBeUsed(true);
    }

    public void setCardsNonUsable(){
        for(Card card: cards) card.setCanBeUsed(false);
    }


    public void harness(Corner corner){
        for(Tile tile : corner.getTiles()){
            if(tile!=null) {
                switch (tile.resource) {
                    case BRICK:
                        this.brick++;
                        break;
                    case LUMBER:
                        this.lumber++;
                        break;
                    case WHEAT:
                        this.wheat++;
                        break;
                    case SHEEP:
                        this.sheep++;
                        break;
                    case ORE:
                        this.ore++;
                        break;
                }
            }
        }
    }

    public int countResources(){
        return this.lumber  + this.brick + this.wheat + this.sheep + this.ore;
    }

    public int countResources(Resource resource){
        switch (resource){
            case BRICK : return this.brick;
            case LUMBER : return this.lumber;
            case WHEAT : return this.wheat;
            case SHEEP : return this.sheep;
            case ORE : return this.ore;
            default: return 0;
        }
    }

    public int countDevCards(){
        return cards.size();
    }

    public int countDevCards(String s){
        int res=0;
        for(Card card : cards){
            if(card.getName().equals(s)) res++;
        }
        return res;
    }

    public Model getModel(){
        String name="";
        if(isHuman()){
            name+="human_";
        }else{
            name+="bot_";
        }
        if (Color.BLUE.equals(color)) {
            name += "blue";
        } else if (Color.RED.equals(color)) {
            name += "red";
        } else if (Color.GREEN.equals(color)) {
            name += "green";
        } else if (Color.YELLOW.equals(color)) {
            name += "yellow";
        }
        return ModelManager.getmodel(name);
    }

    public void stealFrom(Player player){
        if(player.countResources()==0){return;}
        Random random=new Random();
        Resource[] r={Resource.ORE,Resource.LUMBER,Resource.WHEAT,Resource.SHEEP,Resource.BRICK};
        Settings.shuffle(r);

        for (int i=0;i<5;i++){
            if(player.countResources(r[i])>0){
                player.addResources(r[i],-1);
                this.addResources(r[i],1);
                return;
            }
        }


    }

    public void increaseVP(){
        this.VP++;
        this.SVP++;
    }

    public void increaseSVP(){
        this.SVP++;
    }

    public void increaseVP(int n){
        this.VP+=n;
        this.SVP+=n;
    }

}

