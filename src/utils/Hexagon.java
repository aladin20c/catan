package utils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Hexagon {


    private Hexagon(){}

    public Point2D.Double center;
    public int side;
    public Point2D.Double[] corners;

    public Hexagon(double x, double y, int a){
        this.center=new Point2D.Double(x,y);
        this.side=a;
        this.corners=new Point2D.Double[6];
        this.corners[0]=new Point2D.Double(x,y+a);
        this.corners[1]=new Point2D.Double((x-Math.sqrt(3)*a/2),y+a/2);
        this.corners[2]=new Point2D.Double((x-Math.sqrt(3)*a/2),y-a/2);
        this.corners[3]=new Point2D.Double(x,y-a);
        this.corners[4]=new Point2D.Double((x+Math.sqrt(3)*a/2),y-a/2);
        this.corners[5]=new Point2D.Double((x+Math.sqrt(3)*a/2),y+a/2);
    }

    public Hexagon(Point2D.Double center, int a){
        this(center.x, center.y, a);
    }

    //get all points for 3*3 map (6*19=114 points)
    public static ArrayList<Hexagon> map3x3(Point2D.Double TopLeftPointCenter, int a){
        ArrayList<Hexagon> hexagons=new ArrayList<>();
        Point2D.Double firstCenter;
        //first row
        firstCenter=TopLeftPointCenter;
        for (int i=0;i<3;i++){
            hexagons.add(new Hexagon(new Point2D.Double((firstCenter.x+i*Math.sqrt(3)*a),firstCenter.y),a));
        }

        //second row
        firstCenter=new Point2D.Double((TopLeftPointCenter.x-Math.sqrt(3)*a/2),TopLeftPointCenter.y+3*a/2);
        for (int i=0;i<4;i++){
            hexagons.add(new Hexagon(new Point2D.Double((firstCenter.x+i*Math.sqrt(3)*a),firstCenter.y),a));
        }
        //third row
        firstCenter=new Point2D.Double((TopLeftPointCenter.x-Math.sqrt(3)*a),TopLeftPointCenter.y+3*a);
        for (int i=0;i<5;i++){
            hexagons.add(new Hexagon(new Point2D.Double((firstCenter.x+i*Math.sqrt(3)*a),firstCenter.y),a));
        }
        //fourth row
        firstCenter=new Point2D.Double((TopLeftPointCenter.x-Math.sqrt(3)*a/2),TopLeftPointCenter.y+9*a/2);
        for (int i=0;i<4;i++){
            hexagons.add(new Hexagon(new Point2D.Double((firstCenter.x+i*Math.sqrt(3)*a),firstCenter.y),a));
        }
        //fifth row
        firstCenter=new Point2D.Double(TopLeftPointCenter.x,TopLeftPointCenter.y+6*a);
        for (int i=0;i<3;i++){
            hexagons.add(new Hexagon(new Point2D.Double((firstCenter.x+i*Math.sqrt(3)*a),firstCenter.y),a));
        }
        return hexagons;
    }


    @Override
    public String toString() {
        return "Hexagon{" +
                "center=" + center.x +" "+ center.y+
                ", side=" + side +
                ", corners=" + Arrays.toString(corners) +
                '}';
    }
}
