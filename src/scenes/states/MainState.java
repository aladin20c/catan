package scenes.states;

import Components.Corner;
import Components.Side;
import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;
import utils.Resource;

import java.util.ArrayList;


public class MainState extends State {

    public MainState(PlayingScene scene) {
        super(scene);
        System.out.println("[MainState] making State");
        for(Button button : scene.controls){button.setActive( !button.getText().equals("roll"));}
        for(Button button : scene.devCards){button.setActive(true);}
        for(Button button : scene.resCards){button.setActive(true);}
    }


    public void buttonClicked(String name) {
        if (name.equals("exit")) {

            Window.changeScene(31);

        } else if(name.equals("next")){

            scene.nextPlayer();

        } else if(name.equals("buy")){

            if(scene.players[scene.currentPlayer].canBuyDevCard(scene.deck)) {
                scene.players[scene.currentPlayer].buyDevCard(scene.deck,true);
            }

        } else if(name.equals("build")){

            ArrayList<Corner> corners=scene.players[scene.currentPlayer].getPotentialSettlements();
            if(scene.players[scene.currentPlayer].canBuildSettlement(corners)) {
                scene.setState(new SettlementState(scene,corners));
            }

        } else if(name.equals("upgrade")){

            ArrayList<Corner> corners=scene.players[scene.currentPlayer].getPotentialCities();
            if(scene.players[scene.currentPlayer].canBuildCity(corners)) {
                scene.setState(new CityState(scene,corners));
            }

        } else if(name.equals("road")){

            ArrayList<Side> sides=scene.players[scene.currentPlayer].getPotentialRoads();
            if(scene.players[scene.currentPlayer].canBuildRoad(sides)) {
                scene.setState(new RoadState(scene,sides));
            }

        } else if(name.equals("robber")){

            if(scene.players[scene.currentPlayer].canUseCard("robber")){
                scene.setState(new PlaceRobberState(scene));
            }

        } else if(name.equals("expansion")){

            if(scene.players[scene.currentPlayer].canUseCard("expansion")) {
                scene.setState(new Expansion1State(scene));
            }

        } else if(name.equals("progress")){

            if(scene.players[scene.currentPlayer].canUseCard("progress")) {
                scene.setState(new ProgressState(scene));
            }

        } else if(name.equals("monopoly")){

            if(scene.players[scene.currentPlayer].canUseCard("monopoly")) {
                scene.setState(new MonopolyState(scene));

            }
        } else if(name.equals("lumbercard")){

            scene.setState(new TradeState(scene,Resource.LUMBER));

        }else if(name.equals("brickcard")){

            scene.setState(new TradeState(scene,Resource.BRICK));

        }else if(name.equals("wheatcard")){

            scene.setState(new TradeState(scene,Resource.WHEAT));

        }else if(name.equals("sheepcard")){

            scene.setState(new TradeState(scene,Resource.SHEEP));

        }else if(name.equals("orecard")){

            scene.setState(new TradeState(scene,Resource.ORE));

        }
    }
}
