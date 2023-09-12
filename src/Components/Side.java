package Components;



import java.awt.*;
import java.util.Objects;


public class Side{


    public boolean isBuildable;
    public boolean hasRoad;
    public Corner corner1;
    public Corner corner2;
    public Player player;

    public Side(Corner corner_1, Corner corner_2) {
        isBuildable=true;
        this.corner1 = corner_1;
        this.corner2 = corner_2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Side)) return false;
        return (corner1.equals(((Side) o).corner1) && corner2.equals(((Side) o).corner2)) || (corner1.equals(((Side) o).corner2) && corner2.equals(((Side) o).corner1)) ;
    }

    @Override
    public int hashCode() {
        return (Objects.hash(corner1, corner2)+Objects.hash(corner2, corner1))/2;
    }


    public void render(Graphics graphics){
        if (this.hasRoad){
            graphics.setColor(player.color);
            graphics.drawLine(corner1.x,corner1.y,corner2.x,corner2.y);
            graphics.drawLine(corner1.x+1,corner1.y+1,corner2.x+1,corner2.y+1);
            graphics.drawLine(corner1.x-1,corner1.y-1,corner2.x-1,corner2.y-1);
        }
    }


    public void setRoad(Player player){
        this.player=player;
        this.isBuildable=false;
        this.hasRoad=true;
    }

}
