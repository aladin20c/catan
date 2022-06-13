package platform.entities;

import platform.render.Model;
import platform.render.ModelManager;
import platform.utils.Direction;
import platform.utils.Ressources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	public static final int top=0;
	public static final int left=1;
	public static final int bottom=2;
	public static final int right=3;
	public static final int top_right=0;
	public static final int top_left=1;
	public static final int bottom_left=2;
	public static final int bottom_right=3;
	public static Model blockModel = ModelManager.getmodel(ModelManager.Block);

	private ArrayList<Tile> blocks= new ArrayList<>();
	private ArrayList<Corner> corners= new ArrayList<>();
	private ArrayList<Side> sides= new ArrayList<>();
	private int height;
	private int width;
	
	public Map(int levelIndex) {
		this.blocks = new ArrayList<>();
		System.out.println("[World][Map]: Loading map file "+levelIndex);
		this.loadFile(levelIndex);

		for(int y=0;y<height;y++) {
			for (int x = 0; x < width; x++){
				if(tileAt(x,y).getType()!=Ressources.SEA) tile_initilalisation(tileAt(x,y));
			}
		}
		labelTilesRandomely();
	}

	
	private void loadFile(int levelIndex) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("./levels/level"+levelIndex+".txt")));
			
			this.height = Integer.parseInt(reader.readLine());
			this.width = Integer.parseInt(reader.readLine());

			
			for(int y=0;y<height;y++) {
				String line = reader.readLine();
				String[] singles = line.split("\\s+");
				
				for(int x=0;x<width;x++) {
					switch(singles[x]) {
					case "S":
						this.blocks.add(new Tile(x*blockModel.width, y*blockModel.height,ModelManager.getmodel("sea"),Ressources.SEA));
						break;
					case "X":
						this.blocks.add(new Tile(x*blockModel.width, y*blockModel.height,blockModel,Ressources.EMPTY));
						break;
					case "0":
						break;
					}
				}
			}
		} catch(IOException e) {
			System.err.println("[World][Map]: Couldn't load map file "+levelIndex);
			this.loadFile(levelIndex-1);
		}
	}


	//convert x y coordinates to a single index i for one dimensional array
	public int index(int x,int y){
		if(x<0 || y<0 || x>=width || y>=height) return -1;
		return x+y*width;
	}
	//return the tile with x y coordinates
	public Tile tileAt(int x, int y){
		if(index(x,y)==-1) return null;
		return blocks.get(index(x,y));
	}
	//a function that initialise a tile, its sides and its corners with taking in account adjascent tiles
	private void tile_initilalisation(Tile t){
		int t_x=t.posX/blockModel.width;
		int t_y=t.posY/blockModel.height;

		if(t.corners[top_right] ==null){
			Corner c = setCorners(tileAt(t_x+1,t_y-1),tileAt(t_x,t_y-1),t,tileAt(t_x+1,t_y),t.posX+blockModel.width,t.posY);
			corners.add(c);
		}
		if(t.corners[top_left]==null){
			Corner c = setCorners(tileAt(t_x,t_y-1),tileAt(t_x-1,t_y-1),tileAt(t_x-1,t_y),t,t.posX,t.posY);
			corners.add(c);
		}
		if(t.corners[bottom_left]==null){
			Corner c = setCorners(t,tileAt(t_x-1,t_y),tileAt(t_x-1,t_y+1),tileAt(t_x,t_y+1),t.posX,t.posY+blockModel.height);
			corners.add(c);
		}
		if(t.corners[bottom_right]==null){
			Corner c = setCorners(tileAt(t_x+1,t_y),t,tileAt(t_x,t_y+1),tileAt(t_x+1,t_y+1),t.posX+blockModel.width,t.posY+blockModel.height);
			corners.add(c);
		}

		if(t.sides[top]==null){
			Side s= setHorizentalSide(tileAt(t_x,t_y-1),t,t.corners[top_left],t.corners[top_right],t.posX,t.posY);
			sides.add(s);
		}
		if(t.sides[left]==null){
			Side s= setVerticalSide(tileAt(t_x-1,t_y),t,t.corners[top_left],t.corners[bottom_left],t.posX,t.posY);
			sides.add(s);
		}
		if(t.sides[bottom]==null){
			Side s= setHorizentalSide(t,tileAt(t_x,t_y+1),t.corners[bottom_left], t.corners[bottom_right],t.posX,t.posY+blockModel.height);
			sides.add(s);
		}
		if(t.sides[right]==null){
			Side s= setVerticalSide(t,tileAt(t_x+1,t_y),t.corners[top_right],t.corners[bottom_right],t.posX+blockModel.width,t.posY);
			sides.add(s);
		}
	}
	public static Corner setCorners(Tile top_right_tile,Tile top_left_tile,Tile bottom_left_tile,Tile bottom_right_tile,int posx,int posy){
		Corner c =new Corner(posx,posy,top_right_tile,top_left_tile,bottom_left_tile,bottom_right_tile);

		if(top_right_tile!=null && top_right_tile.getType()!= Ressources.SEA) {
			top_right_tile.corners[bottom_left]=c;
		}
		if(top_left_tile!=null && top_left_tile.getType()!= Ressources.SEA) {
			top_left_tile.corners[bottom_right] = c;
		}
		if(bottom_left_tile!=null && bottom_left_tile.getType()!= Ressources.SEA ) {
			bottom_left_tile.corners[top_right] = c;
		}
		if(bottom_right_tile!=null && bottom_right_tile.getType()!= Ressources.SEA ) {
			bottom_right_tile.corners[top_left] = c;
		}
		return c;
	}
	public static Side setHorizentalSide(Tile adjascent_1, Tile adjascent_2, Corner corner_1, Corner corner_2,int posx,int posy){
		Side s=new Side(posx,posy,adjascent_1, adjascent_2, corner_1, corner_2, Direction.HOEIZENTAL);
		if(adjascent_1!=null && adjascent_1.getType()!= Ressources.SEA) adjascent_1.sides[bottom]=s;
		if(adjascent_2!=null && adjascent_2.getType()!= Ressources.SEA) adjascent_2.sides[top]=s;
		return s;
	}
	public static Side setVerticalSide(Tile adjascent_1, Tile adjascent_2, Corner corner_1, Corner corner_2,int posx,int posy){
		Side s=new Side(posx,posy,adjascent_1, adjascent_2, corner_1, corner_2,Direction.VERTICAL);
		if(adjascent_1!=null && adjascent_1.getType()!= Ressources.SEA) adjascent_1.sides[right]=s;
		if(adjascent_2!=null && adjascent_2.getType()!= Ressources.SEA) adjascent_2.sides[left]=s;
		return s;
	}

	/*assigning a value and a type to each tile randomely. there will be one tile with a value of 7 ans has a desert type
    also there will be equal partition of numbers.*/
	public void labelTilesRandomely(){
		int[] numbers_possible={2,3,4,5,6,8,9,10,11,12};
		int not_sea=0;
		for(Tile t:blocks) if(t.getType()!=Ressources.SEA) not_sea++;
		int[] lables=new int[not_sea];

		//filling the lables table
		lables[0]=7;
		int lables_index=1;
		int numbers_index=0;
		while(lables_index<lables.length){
			lables[lables_index]=numbers_possible[numbers_index%10];
			lables_index++;
			numbers_index++;
		}
		shuffle(lables);
		Random ran = new Random();

		int cmp=0;
		for (int i=0;i<blocks.size();i++){
			if(blocks.get(i).getType()!=Ressources.SEA) {
				blocks.get(i).setLable(lables[cmp]);
				cmp++;
				if (blocks.get(i).getLable() == 7) {
					blocks.get(i).setType(Ressources.DESERT);
					blocks.get(i).setStealer(true);
					blocks.get(i).setModel(new Model(blockModel.width, blockModel.height, "desert"));
				} else {
					int ressource = ran.nextInt(5);
					switch (ressource) {
						case 0:
							blocks.get(i).setType(Ressources.LUMBER);
							blocks.get(i).setModel(new Model(blockModel.width, blockModel.height, "lumber"));
							break;
						case 1:
							blocks.get(i).setType(Ressources.BRICK);
							blocks.get(i).setModel(new Model(blockModel.width, blockModel.height, "brick"));
							break;
						case 2:
							blocks.get(i).setType(Ressources.WHEAT);
							blocks.get(i).setModel(new Model(blockModel.width, blockModel.height, "wheat"));
							break;
						case 3:
							blocks.get(i).setType(Ressources.SHEEP);
							blocks.get(i).setModel(new Model(blockModel.width, blockModel.height, "sheep"));
							break;
						case 4:
							blocks.get(i).setType(Ressources.ORE);
							blocks.get(i).setModel(new Model(blockModel.width, blockModel.height, "ore"));
							break;
					}
				}
			}
		}
	}
	private static void shuffle(int[] array) {
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
	public ArrayList<Tile> getBlocks() {return blocks;}
	public ArrayList<Corner> getCorners() {return corners;}
	public ArrayList<Side> getSides() {return sides;}
	
}
