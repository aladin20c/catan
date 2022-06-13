package platform.entities;


import platform.entities.buildings.Settlement;
import platform.render.Model;
import platform.render.ModelManager;
import platform.render.Renderer;
import platform.utils.Ressources;

import java.awt.*;
import java.util.ArrayList;

public class Tile extends StaticEntity {

    private int lable=0;
    private Ressources type;
    private boolean stealer=false;
    public Side[] sides=new Side[4];//{top,left,bottom,right}
    public Corner[] corners=new Corner[4];//{top_right,top_left,bottom_left,bottom_right}

    public Tile(int pos_x, int pos_y, Model model) {
        super(pos_x,pos_y,model);
    }
    public Tile(int pos_x, int pos_y, Model model,Ressources type) {
        super(pos_x,pos_y,model);
        this.type=type;
    }

    public void render(Graphics graphics){
        Renderer.renderEntity(this, graphics);
        if(type!= Ressources.SEA && type!=Ressources.DESERT) Renderer.drawTextInCenter(getModel(),lable+"");
        if(stealer){
            Model robber=ModelManager.getmodel(ModelManager.ROBBERPIECE);
            Renderer.renderModel(robber,posX+(getModel().width-robber.width)/2,posY+(getModel().height-robber.height)/2,graphics);
        }
    }



    public void harnessRessource(){
        ArrayList<Settlement> settelments=new ArrayList<>();
        for(Corner corner: getAdjascentCorners()){
            if(corner.hasSettlement() && !settelments.contains(corner.getBuilding())) settelments.add(corner.getBuilding());
        }
        for(Settlement s : settelments){
            s.harnessRessource(type);
        }
    }

    public ArrayList<Player> getCornerOwners(){
        ArrayList<Player> owners=new ArrayList<>();
        for(Corner corner: getAdjascentCorners()){
            if(corner.hasSettlement()) {
                if(!owners.contains(corner.getBuilding().owner)) owners.add(corner.getBuilding().owner);
            }
        }
        return owners;
    }
    public ArrayList<Corner> getAdjascentCorners() {
        ArrayList<Corner> corners= new ArrayList<>();
        for(Corner c :this.corners){
            if(c!=null) corners.add(c);
        }
        return corners;
    }
    public ArrayList<Side> getAdjascentSides() {
        ArrayList<Side> sides= new ArrayList<>();
        for(Side s: this.sides){
            if(s!=null) sides.add(s);
        }
        return sides;
    }
    public void setLable(int lable) {this.lable = lable;}
    public void setStealer(boolean stealer) {this.stealer = stealer;}
    public int getLable() {return lable;}
    public boolean hasStealer() {return stealer;}
    public Ressources getType() {return type;}
    public void setType(Ressources type) {this.type = type;}
}
