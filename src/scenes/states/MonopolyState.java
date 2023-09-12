package scenes.states;

import Components.Player;
import Jade.Window;
import renderer.Button;
import renderer.Model;
import renderer.ModelManager;
import renderer.Renderer;
import scenes.PlayingScene;
import utils.Resource;

import java.awt.*;

public class MonopolyState extends State{

    Resource choice;
    Model model;

    public MonopolyState(PlayingScene scene) {
        super(scene);
        System.out.println("[MonopolyState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit") || button.getText().equals("next") );}
        for(Button button : scene.devCards){button.setActive(button.getText().equals("monopoly"));}
        for(Button button : scene.resCards){button.setActive(true);}
        this.choice=null;
        this.model=null;
    }



    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {

            Window.changeScene(31);

        } else if(name.equals("monopoly")){

            scene.setState(new MainState(scene));

        }else if(name.equals("next")){

            if(this.choice!=null){
                int total=0;
                for (Player player : scene.players){
                    int n=player.countResources(choice);
                    total+=n;
                    player.addResources(choice,-n);
                }
                scene.players[scene.currentPlayer].addResources(choice,total);
                scene.players[scene.currentPlayer].useCard("monopoly");
            }
            scene.setState(new MainState(scene));

        }else if(name.equals("lumbercard")){

            if(this.choice==null){
                this.choice=Resource.LUMBER;
                this.model= ModelManager.getmodel(ModelManager.LUMBERCARD);
            }else if(this.choice==Resource.LUMBER){
                this.choice=null;
                this.model= null;
            }

        }else if(name.equals("brickcard")){

            if(this.choice==null){
                this.choice=Resource.BRICK;
                this.model= ModelManager.getmodel(ModelManager.BRICKCARD);
            }else if(choice==Resource.BRICK){
                this.choice=null;
                this.model= null;
            }

        }else if(name.equals("wheatcard")){

            if(this.choice==null){
                this.choice=Resource.WHEAT;
                this.model= ModelManager.getmodel(ModelManager.WHEATCARD);
            }else if(this.choice==Resource.WHEAT){
                this.choice=null;
                this.model= null;
            }

        }else if(name.equals("sheepcard")){

            if(this.choice==null){
                this.choice=Resource.SHEEP;
                this.model= ModelManager.getmodel(ModelManager.SHEEPCARD);
            }else if(this.choice==Resource.SHEEP){
                this.choice=null;
                this.model= null;
            }

        }else if(name.equals("orecard")){

            if(this.choice==null){
                this.choice=Resource.ORE;
                this.model= ModelManager.getmodel(ModelManager.ORECARD);
            }else if(this.choice==Resource.ORE){
                this.choice=null;
                this.model= null;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        Renderer.renderModel(model, 10,scene.bottomBar.y-110,66,100,graphics);
    }
}
