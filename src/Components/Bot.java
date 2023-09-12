package Components;


import scenes.PlayingScene;
import scenes.Settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player {
    public Bot(String name, Color color) {
        super(name, color);
    }

    public boolean isHuman(){return false;}
    public boolean isBot(){return true;}

    public void applyStrategy(PlayingScene scene) {

        Random random=new Random();

        if(scene.turn==0){
            ArrayList<Corner> potentialCorners=new ArrayList<>();
            int count=0;
            for(Corner corner : scene.map.getCorners()){
                if(corner.isBuildable){
                    potentialCorners.add(corner);
                    count++;
                }
            }

            Corner corner=potentialCorners.get(random.nextInt(count));
            this.buildSettlement(corner,false);
            int tmp=0;
            for(Side side : corner.getSides()){
                if(side!=null)tmp++;
            }
            this.buildRoad(corner.getSides()[random.nextInt(tmp)],false);
            return;
        }

        if(scene.turn==1){
            ArrayList<Corner> potentialCorners=new ArrayList<>();
            int count=0;
            for(Corner corner : scene.map.getCorners()){
                if(corner.isBuildable){
                    potentialCorners.add(corner);
                    count++;
                }
            }

            Corner corner=potentialCorners.get(random.nextInt(count));
            this.buildSettlement(corner,false);
            int tmp=0;
            for(Side side : corner.getSides()){
                if(side!=null)tmp++;
            }
            this.buildRoad(corner.getSides()[random.nextInt(tmp)],false);
            this.harness(corner);
            return;
        }

        //boolean res = g.robber() || g.expansion() || g.progress()|| g.monopoly(); //todo dfrtugyihojugyftdrsert


        scene.rollDice();

        //todo steal

        ArrayList<Corner> cities = getPotentialCities();

        while(!cities.isEmpty() && this.ore>=3 && this.wheat>=2 && this.cities< Settings.MAXCITIES){
            Corner city= cities.get(random.nextInt(cities.size()));
            this.buildCity(city,true);
            cities.remove(city);
        }

        ArrayList<Corner> settlements = getPotentialSettlements();

        while(!settlements.isEmpty() && this.lumber>0 && this.brick>0 && this.wheat>0 && this.sheep>0 && this.settlements< Settings.MAXSETTLEMENTS){
            Corner settlement= settlements.get(random.nextInt(settlements.size()));
            this.buildSettlement(settlement,true);
            settlements.remove(settlement);
        }

        ArrayList<Side> roads = getPotentialRoads();

        while(!roads.isEmpty() && this.lumber>0 && this.brick>0 && this.ways< Settings.MAXROADS){
            Side road= roads.get(random.nextInt(roads.size()));
            this.buildRoad(road,true);
            roads.remove(road);
        }

        while(this.canBuyDevCard(scene.deck)) {
            buyDevCard(scene.deck,true);
        }

    }
}



