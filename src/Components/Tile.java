package Components;


import renderer.Model;
import renderer.ModelManager;
import renderer.Renderer;
import utils.Hexagon;
import utils.Resource;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

import static utils.Resource.*;

public class Tile{

    public static int WIDTH=90;
    public static int HEIGHT=90;
    public int x,y;
    public Resource resource;
    public Model model;
    public int lable;
    public boolean hasStealer;
    private Corner[] corners;



    public Tile(int x, int y, Resource resource, int lable) {
        this.x = x;
        this.y = y;
        this.resource = resource;
        switch (this.resource){
            case BRICK : model=ModelManager.getmodel(ModelManager.BRICK);break;
            case LUMBER : model=ModelManager.getmodel(ModelManager.LUMBER);break;
            case WHEAT : model=ModelManager.getmodel(ModelManager.WHEAT);break;
            case SHEEP : model=ModelManager.getmodel(ModelManager.SHEEP);break;
            case ORE : model=ModelManager.getmodel(ModelManager.ORE);break;
            case DESERT : model=ModelManager.getmodel(ModelManager.DESERT);break;
            default: model=ModelManager.getmodel(ModelManager.SEA);break;
        }
        this.lable = lable;
        this.hasStealer=false;
        this.corners=new Corner[6];
    }

    public Tile(Hexagon hexagon) {
        double x1=hexagon.corners[0].x;
        double y1=hexagon.corners[0].y;
        for(Point2D.Double point : hexagon.corners){
            x1=Math.min(x1, point.x);
            y1=Math.min(y1, point.y);
        }
        this.x=(int) Math.round(x1);
        this.y=(int) Math.round(y1);
        this.resource = null;
        this.model = null;
        this.lable = 0;
        this.hasStealer=false;
        this.corners=new Corner[6];
    }



    public void render(Graphics graphics){
        //graphics.setColor(Color.WHITE);graphics.drawRect(this.x,this.y,Tile.WIDTH,Tile.HEIGHT);
        Renderer.renderModel(this.model,this.x,this.y,Tile.WIDTH,Tile.HEIGHT,graphics);
        if(this.hasStealer){
            Renderer.renderModel(ModelManager.getmodel(ModelManager.ROBBERPIECE),
                    x+(Tile.WIDTH-50)/2,
                    y+(Tile.HEIGHT-75)/2,
                    50,
                    60,
                    graphics);
        }
        if(resource!= EMPTY && resource!= Resource.DESERT) {
            graphics.setColor(Color.WHITE);
            Renderer.drawCenteredText(graphics,""+lable,new Rectangle(this.x,this.y,Tile.WIDTH,Tile.HEIGHT),30);
        }
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", resource=" + resource +
                ", lable=" + lable +
                ", hasStealer=" + hasStealer +
                ", corners=" + Arrays.toString(corners) +
                '}';
    }


    public void setResource(Resource resource) {
        this.resource = resource;
        switch (this.resource){
            case BRICK : this.model=ModelManager.getmodel(ModelManager.BRICK);break;
            case LUMBER : this.model=ModelManager.getmodel(ModelManager.LUMBER);break;
            case WHEAT : this.model=ModelManager.getmodel(ModelManager.WHEAT);break;
            case SHEEP : this.model=ModelManager.getmodel(ModelManager.SHEEP);break;
            case ORE : this.model=ModelManager.getmodel(ModelManager.ORE);break;
            case DESERT : this.model=ModelManager.getmodel(ModelManager.DESERT);break;
            default: this.model=ModelManager.getmodel(ModelManager.SEA);break;
        }
    }
    public void setLable(int lable) {
        this.lable = lable;
    }
    public void setHasStealer(boolean hasStealer) {
        this.hasStealer = hasStealer;
    }
    public Corner[] getCorners() {
        return corners;
    }
    public void addCorner(Corner corner) {
        if(this.corners==null) this.corners=new Corner[6];
        for (int i=0; i<6;i++){
            if(this.corners[i]==null){
                this.corners[i]=corner;
                return;
            }
        }
    }
}
