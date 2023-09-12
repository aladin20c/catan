package renderer;

import java.util.HashMap;

public class ModelManager {

	private static final HashMap<String, Model> models = new HashMap<>();

	public static final String BLOCK = "block";
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
		System.out.println("[ModelManager]: Initialization...");
		models.put(BLOCK, new Model(BLOCK ));
		models.put(SEA, new Model(SEA ));
		models.put(DESERT, new Model(DESERT ));
		models.put(LUMBER, new Model(LUMBER ));
		models.put(ORE, new Model(ORE ));
		models.put(SHEEP, new Model(SHEEP ));
		models.put(BRICK, new Model(BRICK ));
		models.put(WHEAT, new Model(WHEAT));
		models.put(HUMAN_BLUE, new Model(HUMAN_BLUE ));
		models.put(HUMAN_RED, new Model(HUMAN_RED ));
		models.put(HUMAN_GREEN, new Model(HUMAN_GREEN ));
		models.put(HUMAN_YELLOW, new Model(HUMAN_YELLOW ));
		models.put(BOT_BLUE, new Model(BOT_BLUE ));
		models.put(BOT_RED, new Model(  BOT_RED ));
		models.put(BOT_GREEN, new Model(  BOT_GREEN ));
		models.put(BOT_YELLOW, new Model(  BOT_YELLOW ));

		models.put(DE1, new Model(  DE1 ));
		models.put(DE2, new Model(  DE2 ));
		models.put(DE3, new Model(  DE3 ));
		models.put(DE4, new Model(  DE4 ));
		models.put(DE5, new Model(  DE5 ));
		models.put(DE6, new Model(  DE6 ));

		models.put(RS, new Model(  RS ));
		models.put(BS, new Model(  BS ));
		models.put(GS, new Model(  GS ));
		models.put(YS, new Model(  YS ));

		models.put(RRH, new Model(  RRH));
		models.put(BRH, new Model(  BRH));
		models.put(GRH, new Model(  GRH));
		models.put(YRH, new Model(  YRH));

		models.put(RRV, new Model(  RRV));
		models.put(BRV, new Model(  BRV));
		models.put(GRV, new Model(  GRV));
		models.put(YRV, new Model(  YRV));

		models.put(RC, new Model(  RC ));
		models.put(BC, new Model(  BC ));
		models.put(GC, new Model(  GC ));
		models.put(YC, new Model(  YC ));

		models.put(CITY, new Model(  CITY ));
		models.put(ROAD, new Model(  ROAD ));
		models.put(SETTELMENT, new Model(  SETTELMENT ));

		models.put(ROBBERPIECE, new Model(  ROBBERPIECE ));

		models.put(DEVCARD, new Model(  DEVCARD ));
		models.put(ROBBER, new Model(  ROBBER ));
		models.put(EXPANSION, new Model(EXPANSION ));
		models.put(PROGRESS, new Model(  PROGRESS ));
		models.put(MONOPOLY, new Model(  MONOPOLY ));
		models.put(VP, new Model(  VP ));
		models.put(RESARD, new Model(  RESARD ));
		models.put(LUMBERCARD, new Model(  LUMBERCARD));
		models.put(BRICKCARD, new Model(  BRICKCARD ));
		models.put(WHEATCARD, new Model(  WHEATCARD ));
		models.put(ORECARD, new Model(  ORECARD ));
		models.put(SHEEPCARD, new Model(  SHEEPCARD ));
	}
	
	public static Model getmodel(String key) {
		if(key==null || key.isEmpty()) return null;
		return models.get(key);
	}
}
