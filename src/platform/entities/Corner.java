package platform.entities;

import platform.entities.buildings.*;
import platform.render.ModelManager;
import platform.render.Renderer;
import platform.utils.Ressources;


import java.awt.*;
import java.util.ArrayList;

public class Corner extends StaticEntity{
    public static final int span=30;

    private boolean isBuildable;
    private Settlement building;
    public Tile[] tiles=new Tile[4];//{top_right,top_left,bottom_left,bottom_right}

    public Corner(Tile top_right_tile, Tile top_left_tile, Tile bottom_left_tile, Tile bottom_right_tile) {
        super(0,0,null);
        isBuildable=true;
        if(top_right_tile!=null && top_right_tile.getType()!=Ressources.SEA)tiles[Map.top_right]=top_right_tile;
        if(top_left_tile!=null && top_left_tile.getType()!=Ressources.SEA)tiles[Map.top_left]=top_left_tile;
        if(bottom_left_tile!=null && bottom_left_tile.getType()!=Ressources.SEA)tiles[Map.bottom_left]=bottom_left_tile;
        if(bottom_right_tile!=null && bottom_right_tile.getType()!=Ressources.SEA)tiles[Map.bottom_right]=bottom_right_tile;
    }
    public Corner(int posx, int posy, Tile top_right_tile, Tile top_left_tile, Tile bottom_left_tile, Tile bottom_right_tile) {
        super(posx-span/2,posy-span/2, null);
        isBuildable=true;
        if(top_right_tile!=null && top_right_tile.getType()!=Ressources.SEA)tiles[Map.top_right]=top_right_tile;
        if(top_left_tile!=null && top_left_tile.getType()!=Ressources.SEA)tiles[Map.top_left]=top_left_tile;
        if(bottom_left_tile!=null && bottom_left_tile.getType()!=Ressources.SEA)tiles[Map.bottom_left]=bottom_left_tile;
        if(bottom_right_tile!=null && bottom_right_tile.getType()!=Ressources.SEA)tiles[Map.bottom_right]=bottom_right_tile;
    }
    public void render(Graphics graphics){
        Renderer.renderEntity(this, graphics);
    }


    public ArrayList<Tile> getAdjascentTiles() {
        ArrayList<Tile> res=new ArrayList<>();
        for(Tile t : tiles)
            if(t!=null) res.add(t);
        return res;
    }
    public ArrayList<Side> getAdjascentSides() {
        ArrayList<Side> sides=new ArrayList<>();
        for(Tile t: getAdjascentTiles()){
            for(Side s: t.getAdjascentSides()){
                if( (s.corner_1==this || s.corner_2==this) && !sides.contains(s)) sides.add(s);
            }
        }
        return sides;
    }
    public ArrayList<Corner> getAdjascentCorners() {
        ArrayList<Corner> corners=new ArrayList<>();
        ArrayList<Side> sides=getAdjascentSides();
        for(Side s : sides) {
            if(s.corner_1!=this) corners.add(s.corner_1);
            else corners.add(s.corner_2);
        }
        return corners;
    }

    public void setSettlement(Player owner){
        if(isBuildable && owner.getPotentialSettelments().contains(this)) {
            this.building = new Settlement(owner);

            if(owner.getColor()== Color.BLUE){
                this.setModel(ModelManager.getmodel(ModelManager.BS));
            }else if(owner.getColor()== Color.RED) {
                this.setModel(ModelManager.getmodel(ModelManager.RS));
            }else if(owner.getColor()== Color.GREEN) {
                this.setModel(ModelManager.getmodel(ModelManager.GS));
            }else {
                this.setModel(ModelManager.getmodel(ModelManager.YS));
            }

            owner.settelments.add(this);
            setBuildable(false);
            owner.upgradeVP(1);
            for(Corner c:getAdjascentCorners()){
                c.setBuildable(false);
            }
        }
    }
    public void ForceSetSettlement(Player owner){
            this.building = new Settlement(owner);

            if(owner.getColor()== Color.BLUE){
                this.setModel(ModelManager.getmodel(ModelManager.BS));
            }else if(owner.getColor()== Color.RED) {
                this.setModel(ModelManager.getmodel(ModelManager.RS));
            }else if(owner.getColor()== Color.GREEN) {
                this.setModel(ModelManager.getmodel(ModelManager.GS));
            }else {
                this.setModel(ModelManager.getmodel(ModelManager.YS));
            }

            owner.settelments.add(this);
            setBuildable(false);
            owner.upgradeVP(1);
            for(Corner c:getAdjascentCorners()){
                c.setBuildable(false);
            }
    }
    public void setCity(Player owner){
        if(building!=null && building.owner==owner) {
            this.building = new City(owner);
            if(owner.getColor()== Color.BLUE){
                this.setModel(ModelManager.getmodel(ModelManager.BC));
            }else if(owner.getColor()== Color.RED) {
                this.setModel(ModelManager.getmodel(ModelManager.RC));
            }else if(owner.getColor()== Color.GREEN) {
                this.setModel(ModelManager.getmodel(ModelManager.GC));
            }else {
                this.setModel(ModelManager.getmodel(ModelManager.YC));
            }
            owner.upgradeVP(1);
        }
    }
    public void harnessAdjascentTiles(){
        if(hasSettlement()) {
            for (Tile t : getAdjascentTiles()) {
                if(t.getType()!=Ressources.DESERT && t.getType()!=Ressources.SEA && !t.hasStealer())building.harnessRessource(t.getType());
            }
        }
    }



    public boolean isIsolated(){
        if(!hasSettlement()) return false;
        ArrayList<Side> adjascentSides=getAdjascentSides();
        for(Side s: adjascentSides){
            if(s.hasRoad()) return false;
        }
        return true;
    }
    public boolean isBuildable() {return isBuildable;}
    public void setBuildable(boolean buildable) {isBuildable = buildable;}
    public Settlement getBuilding() {return building;}
    public boolean hasSettlement(){if(building!=null) return true;return false;}
}
