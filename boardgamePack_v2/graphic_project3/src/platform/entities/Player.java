package platform.entities;

import platform.entities.buildings.City;
import platform.observers.LargestArmyObserver;
import platform.observers.LongestRoadObserver;
import platform.observers.Observer;
import platform.observers.Vpobserver;
import platform.render.Model;
import platform.states.GameState;
import platform.utils.Card;
import platform.utils.Ressources;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public abstract class Player {

    public String name;
    public Model module;
    private Player next;
    private Color color;
    private int VP;
    private int robbersUsed;
    private int roadsBuilt;
    private boolean hasLongestRoad;
    private boolean hasLArgestArmy;


    private ArrayList<Ressources> ressources=new ArrayList<>();
    private ArrayList<Card> cards=new ArrayList<>();
    public ArrayList<Corner> settelments=new ArrayList<>();
    public ArrayList<Side> roads=new ArrayList<>();

    public Vpobserver vpobserver;
    public LongestRoadObserver longestroadobserver ;
    public LargestArmyObserver largestarmyobserver;


    public Player(String name, Color color) {
        this.color=color;
        this.name = name;
        this.VP=0;
        this.robbersUsed=0;
        this.hasLongestRoad=false;
        this.hasLArgestArmy=false;
        this.roadsBuilt=0;
    }


    //longest road______________________________
    private boolean isValid(Side s, boolean[] visited){
        return roads.contains(s) && !visited[roads.indexOf(s)];
    }

    private static boolean[] CopyOf(boolean[] t){
        boolean[] res=new boolean[t.length];
        for (int i=0;i<t.length;i++)
            res[i]=t[i];
        return res;
    }

    public int calculateLongestRoad(){
        int longest_road=0;
        for(Side chemin:roads){
            boolean[] visited=new boolean[roads.size()];
            longest_road=Math.max(longest_road,longestItineary(chemin,visited));
        }
        return longest_road;
    }
    public int longestItineary(Side side, boolean[] visited){
        visited[roads.indexOf(side)]=true;
        ArrayList<Side> next_sides=new ArrayList<>();
        ArrayList<Corner> next_corners=side.getAdjascentCorners();

        for(Corner c : next_corners) {
            if (!c.hasSettlement() || settelments.contains(c.getBuilding())) {
                ArrayList<Side> tmp = c.getAdjascentSides();
                for (Side s : tmp) {
                    if (isValid(s, visited)) next_sides.add(s);
                }
            }
        }
        int res=1;
        int longest_itineary=0;
        for(Side s : next_sides){
            longest_itineary=Math.max(longest_itineary,longestItineary(s,CopyOf(visited)));
        }
        return res+longest_itineary;
    }
    //__________________________________________

    public ArrayList<Corner> getPotentialSettelments(){
        ArrayList<Corner> ps=new ArrayList<>();
        for(Side s : roads){
            if(s.corner_1.isBuildable() && !ps.contains(s.corner_1))ps.add(s.corner_1);
            if(s.corner_2.isBuildable() && !ps.contains(s.corner_2))ps.add(s.corner_2);
        }
        return ps;
    }
    public ArrayList<Side> getPotentialRoads(){
        ArrayList<Side> pr=new ArrayList<>();
        for(Corner c:settelments){
            for(Side s:c.getAdjascentSides()){
                if(s.isBuildable() && !pr.contains(s)) pr.add(s);
            }
        }

        for(Side s:roads){
            for(Side s_1:s.get_future_roads_from_this_road(this)){
                if(!pr.contains(s_1)) pr.add(s_1);
            }
        }
        return pr;
    }
    public ArrayList<Corner> getPotentialCities(){
        ArrayList<Corner> pc=new ArrayList<>();
        for(Corner c:settelments){
            if(c.getBuilding()!=null && !(c.getBuilding() instanceof City)) pc.add(c);
        }
        return pc;
    }

    //__________________________________________

    public abstract boolean isHuman();
    public abstract boolean isBot();
    public abstract void applyStrategy(GameState gameState);


    public Color getColor() {return color;}
    public void setColor(Color color) {this.color = color;}
    public Model getModule() {return module;}
    public Player getNext() {return next;}
    public void setNext(Player next) {this.next = next;}
    public int getRoadsBuilt() {return roadsBuilt;}
    public void setRoadsBuilt(int roadsBuilt) {this.roadsBuilt = roadsBuilt;}
    public int getVP() {return VP;}
    public int getRobbersUsed() {return robbersUsed;}
    public boolean hasLArgestArmy() {return hasLArgestArmy;}
    public boolean hasLongestRoad() {return hasLongestRoad;}
    public ArrayList<Ressources> getRessources() {return ressources;}
    public void incrementRobberUsed(){
        robbersUsed++;
        largestarmyobserver.inform();
    }
    public ArrayList<Card> getCards() {return cards;}


    public void upgradeVP(int n) {
        this.VP =this.VP+n;
        vpobserver.inform();
    }

    public void setLArgestArmy(boolean newVal) {
        if(this.hasLArgestArmy!=newVal){
            if(newVal)upgradeVP(2);
            else upgradeVP(-2);
        }
        this.hasLArgestArmy = newVal;
    }

    public void setLongestRoad(boolean newVal) {
        if(this.hasLongestRoad!=newVal){
            if(newVal)upgradeVP(2);
            else upgradeVP(-2);
        }
        this.hasLongestRoad = newVal;
    }












    //cards Section___________________________________________________________________________________________
    public void buyDevCard(){
        if(!canBuyDev()) return;
        Random r =new Random();
        int probability=r.nextInt(25);
        if(probability<=13) addCard(Card.robber);
        else if(probability<=18) addCard(Card.vp);
        else if(probability<=20) addCard(Card.progress);
        else if(probability<=22) addCard(Card.expansion);
        else addCard(Card.monopoly);
        cutDevMaterial();
    }

    public void giveResCardTo(Player player){
        if(!player.ressources.isEmpty()) {
            Random random=new Random();
            int r=random.nextInt(ressources.size());
            Ressources res=ressources.get(r);
            ressources.remove(r);
            player.ressources.add(res);
        }
    }

    public void monopoly(Ressources r, Player[] players){
        for (Player p : players){
            if(p!=this) {
                while (p.getRessources().contains(r)) {
                    p.ressources.remove(r);
                    ressources.add(r);
                }
            }
        }
    }

    public void addCard(String s){
        cards.add(new Card(s));
    }
    public boolean useCard(String s){
        for(Card c : cards){
            if(c.getName().equals(s) && c.canBeUsed()) {
                if(s.equals("robber"))incrementRobberUsed();
                cards.remove(c);
                return true;
            }
        }
        return false;
    }
    public int count(String s){
        int res=0;
        for(Card c : cards){
            if(c.getName().equals(s)) res++;
        }
        return res;
    }
    public void addRessource(Ressources r){ressources.add(r);}
    public int count(Ressources r){
        int res=0;
        for(Ressources ressource : ressources){
            if(ressource==r) res++;
        }
        return res;
    }

    public boolean canBuildRoad(){
        return ressources.contains(Ressources.LUMBER) && ressources.contains(Ressources.BRICK) && !getPotentialRoads().isEmpty();
    }
    public boolean canBuildSettelment(){
        return ressources.contains(Ressources.LUMBER) && ressources.contains(Ressources.BRICK) && ressources.contains(Ressources.WHEAT) && ressources.contains(Ressources.SHEEP) && !getPotentialSettelments().isEmpty();
    }
    public boolean canBuildCity(){
        return count(Ressources.WHEAT)==2 && count(Ressources.ORE)==3 && !getPotentialCities().isEmpty();
    }
    public boolean canBuyDev(){return ressources.contains(Ressources.ORE) && ressources.contains(Ressources.WHEAT) && ressources.contains(Ressources.SHEEP);}

    public void cutRoadMaterial(){
        ressources.remove(Ressources.LUMBER);
        ressources.remove(Ressources.BRICK);
    }
    public void cutSettelmentMaterial(){
        ressources.remove(Ressources.LUMBER);
        ressources.remove(Ressources.BRICK);
        ressources.remove(Ressources.WHEAT);
        ressources.remove(Ressources.SHEEP);
    }
    public void cutCityMaterial(){
        ressources.remove(Ressources.WHEAT);
        ressources.remove(Ressources.WHEAT);
        ressources.remove(Ressources.ORE);
        ressources.remove(Ressources.ORE);
        ressources.remove(Ressources.ORE);
    }
    public void cutDevMaterial(){
        ressources.remove(Ressources.SHEEP);
        ressources.remove(Ressources.WHEAT);
        ressources.remove(Ressources.ORE);
    }

    public void SetCardsUsable(){
        for(Card c: cards) c.setCanBeUsed(true);
    }
    public int countressources(){
        return ressources.size();
    }
    public int countdevcards(){
        return cards.size();
    }

}

