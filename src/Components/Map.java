package Components;


import utils.Hexagon;
import utils.Resource;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	public int a;
	private ArrayList<Tile> tiles;
	private ArrayList<Corner> corners;
	private ArrayList<Side> sides;


	public Map(int mapIndex, int x, int y , int a) {
        System.out.println("[Map]: Loading map  "+mapIndex);
		this.a=a;
		Tile.WIDTH= (int)(Math.sqrt(3)*a);
		Tile.HEIGHT= 2*a;
		this.tiles = new ArrayList<>();
        this.corners = new ArrayList<>();
        this.sides = new ArrayList<>();


        //initialising corners and tiles
        ArrayList<Hexagon> hexagons =Hexagon.map3x3(new Point2D.Double(x,y),a);
        for (Hexagon hexagon : hexagons){
            Tile tile=new Tile(hexagon);
            for(Point2D.Double point : hexagon.corners){
                Corner corner = new Corner(point);
				Corner cornerTmp=null;
				for(Corner c : this.corners){
					if(c.equals(corner)) cornerTmp=c;
				}
                if(cornerTmp==null){
                    this.corners.add(corner);
                }else{
					corner=cornerTmp;
				}
				tile.addCorner(corner);
				corner.addTile(tile);
            }
			this.tiles.add(tile);
        }
		labelTilesRandomly();

		//initialising sides
		for(Tile tile : this.tiles){
			for (Corner corner1 : tile.getCorners()){
				for (Corner corner2 : tile.getCorners()) {
					if(!corner1.equals(corner2) && distanceAlmostEquals(corner1,corner2,a)){
						Side newSide=new Side(corner1,corner2);
						if(!this.sides.contains(newSide)){
							this.sides.add(newSide);
							corner1.addSide(newSide);
							corner2.addSide(newSide);
						}
					}
				}
			}
		}

	}

	private boolean distanceAlmostEquals(Corner corner1,Corner corner2, int a){
		double distance=Math.sqrt(Math.pow(corner1.x-corner2.x,2)+ Math.pow(corner1.y-corner2.y,2));
		return distance>=a-5  && distance<=a+5;
	}



	/*assigning a value and a type to each tile randomly. there will be one tile with a value of 7 and has a desert type.*/

    private void labelTilesRandomly(){
		int[] possible_values={2,3,4,5,6,8,9,10,11,12};
		Resource[] possible_resources={Resource.BRICK, Resource.LUMBER, Resource.WHEAT, Resource.SHEEP, Resource.ORE};
		Random random=new Random();
		int[] labels=new int[tiles.size()];
		//filling the labels table
		for(int i=0;i<11 && i<tiles.size();i++){
			labels[i]=i+2;
		}
		for(int i=11;i< tiles.size();i++){
			labels[i]=possible_values[random.nextInt(10)];
		}

		shuffle(labels);

		for(int i=0;i<labels.length;i++){
			this.tiles.get(i).setLable(labels[i]);
			if(labels[i]==7){
				this.tiles.get(i).setResource(Resource.DESERT);
				this.tiles.get(i).setHasStealer(true);
			}else{
				this.tiles.get(i).setResource(possible_resources[random.nextInt(5)]);
			}
		}
	}

	public static void shuffle(int[] array) {
		Random random = new Random();
		int count = array.length;
		for (int i = count; i > 1; i--) {
			//swap(array, i - 1, random.nextInt(i));
			int r = random.nextInt(i);
			int tmp = array[i - 1];
			array[i - 1] = array[r];
			array[r] = tmp;
		}
	}


	public void render(Graphics graphics) {
		for(Tile tile: this.tiles){
			tile.render(graphics);
		}
		for(Corner corner: this.corners){
			corner.render(graphics);
		}
		for(Side side: this.sides){
			side.render(graphics);
		}
	}

	public ArrayList<Tile> getFreeRobberTiles() {
		ArrayList<Tile> res=new ArrayList<>();
		for(Tile tile : this.tiles){
			if(!tile.hasStealer){
				res.add(tile);
			}
		}
		return res;
	}


	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public ArrayList<Corner> getCorners() {
		return corners;
	}

}
