package observers;

import Components.Corner;
import Components.Player;
import Components.Side;
import Jade.Window;
import scenes.PlayingScene;



public class RoadObserver {



    public static void checkLongestRoads(Corner corner) {
        if(corner==null || corner.player==null) return;
        if((corner.getSides()[0]!=null && corner.getSides()[0].player!=null && corner.getSides()[1]!=null && corner.getSides()[1].player!=null && corner.getSides()[0].player==corner.getSides()[1].player && corner.getSides()[1].player!= corner.player)){

            corner.getSides()[0].player.longestPath=calculateLongestPath(corner.getSides()[0].player);
            assign();

        }else if (corner.getSides()[2]!=null && corner.getSides()[2].player!=null && corner.getSides()[1]!=null && corner.getSides()[1].player!=null && corner.getSides()[2].player==corner.getSides()[1].player && corner.getSides()[1].player!= corner.player){

            corner.getSides()[2].player.longestPath=calculateLongestPath(corner.getSides()[2].player);
            assign();

        }else if(corner.getSides()[0]!=null && corner.getSides()[0].player!=null && corner.getSides()[2]!=null && corner.getSides()[2].player!=null && corner.getSides()[0].player==corner.getSides()[2].player && corner.getSides()[2].player!= corner.player){

            corner.getSides()[0].player.longestPath=calculateLongestPath(corner.getSides()[0].player);
            assign();

        }
    }


    public static void checkLongestRoads(Side side){
        int calculate=calculateLongestPathStartingFrom(side);
        side.player.longestPath = Math.max(calculate,side.player.longestPath);
        if(side.player.ways>4) {
            assign();
        }
    }


    public static void assign() {
        PlayingScene scene = (PlayingScene) Window.getCurrentScene();

        Player tmp = null;
        int longestRoad=4;

        for(Player player : scene.players){
            if(player.longestPath>longestRoad){
                longestRoad=player.longestPath;
                tmp=player;
            }
        }

        if(tmp!=null && !tmp.hasLongestRoad){
            for(Player player : scene.players){
                if(player==tmp){
                    player.hasLongestRoad=true;
                    player.increaseVP(2);
                }else if(player.hasLongestRoad){
                    player.hasLongestRoad=false;
                    player.increaseVP(-2);
                }
            }
        }
    }



    public static int calculateLongestPath(Player player) {
        return 0;
    }



//longest road

    public static int calculateLongestPathStartingFrom(Side side){
        if(side==null || side.player==null) return 0;
        Side[] tmp = new Side[0];
        return explore(side,tmp);
    }


    public static int explore(Side side,Side[] sides_1){
        Side[] sides_2=new Side[sides_1.length+1];
        sides_2[sides_2.length-1]=side;
        System.arraycopy(sides_1, 0, sides_2, 0, sides_1.length);

        int res1=0;
        for (Side s : getLeadingRoads_1(side,sides_2)){
            if(s!=null) {
                res1 = Math.max(explore(s, sides_2), res1);
            }
        }

        int res2=0;
        for (Side s : getLeadingRoads_2(side,sides_2)){
            if(s!=null) {
                res2 = Math.max(explore(s, sides_2), res2);
            }
        }

        /*if(side.player.isHuman()){
            System.out.println("res" + (1+res) );
        }*/
        return 1+res1+res2;
    }


    public static Side[] getLeadingRoads_1(Side road,Side[] roads){
        Side[] sides=new Side[3];
        int count=0;

        if(road.corner1.player==null || road.corner1.player==road.player) {
            for (Side side : road.corner1.getSides()) {
                if (isSafe(side,roads)) {
                    sides[count]=side;
                    count++;
                }
            }
        }
        return sides;
    }

    public static Side[] getLeadingRoads_2(Side road,Side[] roads){
        Side[] sides=new Side[3];
        int count=0;

        if(road.corner2.player==null || road.corner2.player==road.player) {
            for (Side side : road.corner2.getSides()) {
                if (isSafe(side,roads)) {
                    sides[count]=side;
                    count++;
                }
            }
        }

        return sides;
    }


    private static boolean isSafe(Side road,Side[] roads){
        if(roads ==null || road==null || road.player==null || road.player!=roads[roads.length-1].player) return false;
        for (Side side : roads) if (side == road) return false;
        return true;
    }


}

