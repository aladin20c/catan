package platform.entities;

import platform.entities.buildings.Building;
import platform.entities.buildings.Road;
import platform.render.ModelManager;
import platform.render.Renderer;
import platform.utils.Direction;
import platform.utils.Ressources;

import java.awt.*;
import java.util.ArrayList;

public class Side extends StaticEntity{

    private boolean isBuildable;
    private Road building;

    public Direction direction;
    public Tile tile_1;//top or left
    public Tile tile_2;//right or bottom
    public Corner corner_1;//top or left
    public Corner corner_2;//right or bottom

    public Side(Tile tile_1, Tile tile_2, Corner corner_1, Corner corner_2,Direction direction) {
        super(0,0,null);
        isBuildable=true;
        this.direction=direction;
        this.tile_1 = tile_1;
        this.tile_2 = tile_2;
        this.corner_1 = corner_1;
        this.corner_2 = corner_2;
    }
    public Side(int posx, int posy, Tile tile_1, Tile tile_2, Corner corner_1, Corner corner_2,Direction direction) {
        super(posx,posy,null);
        isBuildable=true;
        this.direction=direction;
        if(tile_1!=null && tile_1.getType()!= Ressources.SEA)this.tile_1 = tile_1;
        if(tile_2!=null && tile_2.getType()!= Ressources.SEA)this.tile_2 = tile_2;
        this.corner_1 = corner_1;
        this.corner_2 = corner_2;
    }
    public void render(Graphics graphics){
        Renderer.renderEntity(this, graphics);
    }


    public ArrayList<Tile> getAdjascentTiles() {
        ArrayList<Tile> tiles= new ArrayList<>();
        if(tile_1!=null) tiles.add(tile_1);
        if(tile_2!=null) tiles.add(tile_2);
        return tiles;
    }
    public ArrayList<Corner> getAdjascentCorners() {
        ArrayList<Corner> corners= new ArrayList<>();
        if(corner_1!=null) corners.add(corner_1);
        if(corner_2!=null) corners.add(corner_2);
        return corners;
    }
    public ArrayList<Side> getAdjascentSides() {
        ArrayList<Side> sides_corner_1=corner_1.getAdjascentSides();
        ArrayList<Side> sides_corner_2=corner_2.getAdjascentSides();
        ArrayList<Side> sides= new ArrayList<>();

        for(Side s: sides_corner_1){
            if(s!=this && !sides.contains(s)) sides.add(s);
        }
        for(Side s: sides_corner_2){
            if(s!=this && !sides.contains(s)) sides.add(s);
        }
        return sides;
    }

    public ArrayList<Side> get_future_roads_from_this_road(Player owner) {
        ArrayList<Side> sides= new ArrayList<>();

        if(corner_1.getBuilding()==null || corner_1.getBuilding().owner==owner){
            ArrayList<Side> sides_corner_1=corner_1.getAdjascentSides();
            for(Side s: sides_corner_1){
                if(s!=this && s.isBuildable && !sides.contains(s)) sides.add(s);
            }
        }
        if(corner_2.getBuilding()==null || corner_2.getBuilding().owner==owner){
            ArrayList<Side> sides_corner_2=corner_2.getAdjascentSides();
            for(Side s: sides_corner_2){
                if(s!=this && s.isBuildable && !sides.contains(s)) sides.add(s);
            }
        }
        return sides;
    }


    public void setRoad(Player owner){
        if(isBuildable && owner.getPotentialRoads().contains(this)) {
            this.building = new Road(owner);
            owner.roads.add(this);
            setBuildable(false);
            if(direction==Direction.VERTICAL) {
                if (owner.getColor() == Color.BLUE) {
                    this.setModel(ModelManager.getmodel(ModelManager.BRV));
                } else if (owner.getColor() == Color.RED) {
                    this.setModel(ModelManager.getmodel(ModelManager.RRV));
                } else if (owner.getColor() == Color.GREEN) {
                    this.setModel(ModelManager.getmodel(ModelManager.GRV));
                } else {
                    this.setModel(ModelManager.getmodel(ModelManager.YRV));
                }
            }else{
                if (owner.getColor() == Color.BLUE) {
                    this.setModel(ModelManager.getmodel(ModelManager.BRH));
                } else if (owner.getColor() == Color.RED) {
                    this.setModel(ModelManager.getmodel(ModelManager.RRH));
                } else if (owner.getColor() == Color.GREEN) {
                    this.setModel(ModelManager.getmodel(ModelManager.GRH));
                } else {
                    this.setModel(ModelManager.getmodel(ModelManager.YRH));
                }
            }
            owner.longestroadobserver.inform();
        }
    }




    public boolean hasRoad() {return building!=null;}
    public boolean isBuildable() {return isBuildable;}
    public void setBuildable(boolean buildable) {isBuildable = buildable;}
    public Road getBuilding() {return building;}
}
