package platform.render;

import java.util.HashMap;

public class ModelManager {

	private static final HashMap<String, Model> models = new HashMap<>();

	public static final String Block = "block";
	public static final String SEA = "sea";
	public static final String DESERT = "desert";
	public static final String LUMBER = "lumber";
	public static final String ORE = "ore";
	public static final String SHEEP = "sheep";
	public static final String BRICK = "brick";
	public static final String WHEAT = "wheat";

	public static final String HUMAN_BLUE = "human_blue";
	public static final String HUMAN_RED = "human_red";
	public static final String HUMAN_GREEN = "human_green";
	public static final String HUMAN_YELLOW = "human_yellow";
	public static final String BOT_BLUE = "bot_blue";
	public static final String BOT_RED = "bot_red";
	public static final String BOT_GREEN = "bot_green";
	public static final String BOT_YELLOW = "bot_yellow";

	public static final String ROBBERPIECE = "robber_piece";

	public static final String DE1 = "de1";
	public static final String DE2 = "de2";
	public static final String DE3 = "de3";
	public static final String DE4 = "de4";
	public static final String DE5 = "de5";
	public static final String DE6 = "de6";

	public static final String DEVCARD = "dc";
	public static final String ROBBER = "robber";
	public static final String PROGRESS = "progress";
	public static final String EXPANSION = "expansion";
	public static final String MONOPOLY = "monopoly";
	public static final String VP = "vp";

	public static final String RESARD = "rc";
	public static final String LUMBERCARD = "lumbercard";
	public static final String ORECARD = "orecard";
	public static final String SHEEPCARD = "sheepcard";
	public static final String BRICKCARD = "brickcard";
	public static final String WHEATCARD = "wheatcard";

	//public static final String S = "redSettelment";

	public static final String RS = "redSettelment";
	public static final String BS = "blueSettelment";
	public static final String GS = "greenSettelment";
	public static final String YS = "yellowSettelment";

	public static final String RRH = "redRoadH";
	public static final String RRV = "redRoadV";
	public static final String BRH = "blueRoadH";
	public static final String BRV = "blueRoadV";
	public static final String GRH = "greenRoadH";
	public static final String GRV = "greenRoadV";
	public static final String YRH = "yellowRoadH";
	public static final String YRV = "yellowRoadV";

	public static final String RC = "redCity";
	public static final String BC = "blueCity";
	public static final String GC = "greenCity";
	public static final String YC = "yellowCity";

	public static final String CITY = "city";
	public static final String ROAD = "road";
	public static final String SETTELMENT = "settelment";
	
	public static void init() {
		System.out.println("[Resources][ModelManager]: Initialization...");
		models.put(Block, new Model(90,90,Block ));
		models.put(SEA, new Model(90,90,SEA ));
		models.put(DESERT, new Model(90,90,DESERT ));
		models.put(LUMBER, new Model(90,90,LUMBER ));
		models.put(ORE, new Model(90,90,ORE ));
		models.put(SHEEP, new Model(90,90,SHEEP ));
		models.put(BRICK, new Model(90,90,BRICK ));
		models.put(WHEAT, new Model(90,90,WHEAT));
		models.put(HUMAN_BLUE, new Model(40,40,HUMAN_BLUE ));
		models.put(HUMAN_RED, new Model(40,40,HUMAN_RED ));
		models.put(HUMAN_GREEN, new Model(40,40,HUMAN_GREEN ));
		models.put(HUMAN_YELLOW, new Model(40,40,HUMAN_YELLOW ));
		models.put(BOT_BLUE, new Model(40,40,BOT_BLUE ));
		models.put(BOT_RED, new Model( 40,40, BOT_RED ));
		models.put(BOT_GREEN, new Model( 40,40, BOT_GREEN ));
		models.put(BOT_YELLOW, new Model(  40,40,BOT_YELLOW ));

		models.put(DE1, new Model(  50,50,DE1 ));
		models.put(DE2, new Model(  50,50,DE2 ));
		models.put(DE3, new Model(  50,50,DE3 ));
		models.put(DE4, new Model(  50,50,DE4 ));
		models.put(DE5, new Model(  50,50,DE5 ));
		models.put(DE6, new Model(  50,50,DE6 ));

		models.put(RS, new Model(  30,30,RS ));
		models.put(BS, new Model(  30,30,BS ));
		models.put(GS, new Model(  30,30,GS ));
		models.put(YS, new Model(  30,30,YS ));

		models.put(RRH, new Model(  90,7,RRH));
		models.put(BRH, new Model(  90,7,BRH));
		models.put(GRH, new Model(  90,7,GRH));
		models.put(YRH, new Model(  90,7,YRH));

		models.put(RRV, new Model(  7,90,RRV));
		models.put(BRV, new Model(  7,90,BRV));
		models.put(GRV, new Model(  7,90,GRV));
		models.put(YRV, new Model(  7,90,YRV));

		models.put(RC, new Model(  30,30,RC ));
		models.put(BC, new Model(  30,30,BC ));
		models.put(GC, new Model(  30,30,GC ));
		models.put(YC, new Model(  30,30,YC ));

		models.put(CITY, new Model(  50,50,CITY ));
		models.put(ROAD, new Model(  50,50,ROAD ));
		models.put(SETTELMENT, new Model(  50,50,SETTELMENT ));

		models.put(ROBBERPIECE, new Model(  64,64,ROBBERPIECE ));

		models.put(DEVCARD, new Model(  33,50,DEVCARD ));
		models.put(ROBBER, new Model(  33,50,ROBBER ));
		models.put(EXPANSION, new Model(  33,50,EXPANSION ));
		models.put(PROGRESS, new Model(  33,50,PROGRESS ));
		models.put(MONOPOLY, new Model(  33,50,MONOPOLY ));
		models.put(VP, new Model(  33,50,VP ));
		models.put(RESARD, new Model(  33,50,RESARD ));
		models.put(LUMBERCARD, new Model(  33,50,LUMBERCARD));
		models.put(BRICKCARD, new Model(  33,50,BRICKCARD ));
		models.put(WHEATCARD, new Model(  33,50,WHEATCARD ));
		models.put(ORECARD, new Model(  33,50,ORECARD ));
		models.put(SHEEPCARD, new Model(  33,50,SHEEPCARD ));


	}
	
	public static Model getmodel(String key) {
		if(key==null || key.isEmpty()) return null;
		return models.get(key);
	}
}
