package Components;



import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

public class Corner {


    public int x,y;
    public boolean isBuildable;
    public boolean hasSettlement;
    public boolean hasCity;

    private Side[] sides;
    private Tile[] tiles;
    public Player player;

    public Corner(int x, int y) {
        this.isBuildable=true;
        this.hasSettlement=false;
        this.hasCity=false;
        this.x=x;
        this.y=y;
        this.sides=new Side[3];
        this.tiles=new Tile[3];
    }

    public Corner(Point2D.Double point) {
        this((int)(Math.round(point.x)),
                (int)(Math.round(point.y)));
    }


    public void render(Graphics graphics){
        if (this.hasCity){
            graphics.setColor(player.color);
            graphics.drawRect(this.x-10,this.y-10,20,20);
            graphics.drawRect(this.x-9,this.y-9,18,18);
            graphics.drawRect(this.x-8,this.y-8,16,16);
        }else if(this.hasSettlement){
            graphics.setColor(player.color);
            graphics.fillRect(this.x-10,this.y-10,20,20);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corner)) return false;
        Corner corner = (Corner) o;
        return x == corner.x && y == corner.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Corner{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


    public void addSide(Side side){
        if(this.sides==null) this.sides=new Side[3];
        for (int i=0; i<3;i++){
            if(this.sides[i]==null){
                this.sides[i]=side;
                return;
            }
        }
    }
    public void addTile(Tile tile){
        if(this.tiles==null) this.tiles=new Tile[3];
        for (int i=0; i<3;i++){
            if(this.tiles[i]==null){
                this.tiles[i]=tile;
                return;
            }
        }
    }
    public Side[] getSides() {
        return sides;
    }
    public Tile[] getTiles() {
        return tiles;
    }

    public void setSettlement(Player player){
        for(Side side : this.sides){
            if(side !=null) {
                side.corner1.isBuildable=false;
                side.corner2.isBuildable=false;
            }
        }
        this.isBuildable=false;
        this.hasSettlement=true;
        this.player=player;
    }

    public void setCity(){
        this.hasSettlement=false;
        this.hasCity=true;
    }

}
