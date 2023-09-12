package scenes.states;

import Jade.Window;
import renderer.Button;
import renderer.Model;
import renderer.ModelManager;
import renderer.Renderer;
import scenes.PlayingScene;
import utils.Resource;

import java.awt.*;

public class ProgressState extends State{

    Resource[] choice;
    Model[] model;

    public ProgressState(PlayingScene scene) {
        super(scene);
        System.out.println("[ProgressState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit") || button.getText().equals("next") );}
        for(Button button : scene.devCards){button.setActive(button.getText().equals("progress"));}
        for(Button button : scene.resCards){button.setActive(true);}
        this.choice=new Resource[2];
        this.model=new Model[2];
        this.choice[0]=null;
        this.model[0]=null;
        this.choice[1]=null;
        this.model[1]=null;
    }

    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {

            Window.changeScene(31);

        } else if(name.equals("progress")){

            scene.setState(new MainState(scene));

        }else if(name.equals("next")){

            if(choice[0]!=null && choice[1]!=null){
                scene.players[scene.currentPlayer].addResources(choice[0],1);
                scene.players[scene.currentPlayer].addResources(choice[1],1);
                scene.players[scene.currentPlayer].useCard("expansion");
            }
            scene.setState(new MainState(scene));

        }else if(name.equals("lumbercard")){

            if(choice[0]==null){
                this.choice[0]=Resource.LUMBER;
                this.model[0]=ModelManager.getmodel(ModelManager.LUMBERCARD);
            }else if(choice[1]==null){
                this.choice[1]=Resource.LUMBER;
                this.model[1]=ModelManager.getmodel(ModelManager.LUMBERCARD);
            }else if(choice[0]==Resource.LUMBER && choice[1]==Resource.LUMBER){
                this.choice[1]=null;
                this.model[1]=null;
                this.choice[0]=null;
                this.model[0]=null;
            }else if(choice[1]==Resource.LUMBER){
                this.choice[1]=null;
                this.model[1]=null;
            }else if(choice[0]==Resource.LUMBER){
                this.choice[0]=null;
                this.model[0]=null;
            }

        }else if(name.equals("brickcard")){

            if(choice[0]==null){
                choice[0]=Resource.BRICK;
                this.model[0]=ModelManager.getmodel(ModelManager.BRICKCARD);
            }else if(choice[1]==null){
                choice[1]=Resource.BRICK;
                this.model[1]=ModelManager.getmodel(ModelManager.BRICKCARD);
            }else if(choice[0]==Resource.BRICK && choice[1]==Resource.BRICK){
                this.choice[1]=null;
                this.model[1]=null;
                this.choice[0]=null;
                this.model[0]=null;
            }else if(choice[1]==Resource.BRICK){
                choice[1]=null;
                model[1]=null;
            }else if(choice[0]==Resource.BRICK){
                choice[0]=null;
                model[0]=null;
            }

        }else if(name.equals("wheatcard")){

            if(choice[0]==null){
                choice[0]=Resource.WHEAT;
                this.model[0]=ModelManager.getmodel(ModelManager.WHEATCARD);
            }else if(choice[1]==null){
                choice[1]=Resource.WHEAT;
                this.model[1]=ModelManager.getmodel(ModelManager.WHEATCARD);
            }else if(choice[0]==Resource.WHEAT && choice[1]==Resource.WHEAT){
                this.choice[1]=null;
                this.model[1]=null;
                this.choice[0]=null;
                this.model[0]=null;
            }else if(choice[1]==Resource.WHEAT){
                choice[1]=null;
                model[1]=null;
            }else if(choice[0]==Resource.WHEAT){
                choice[0]=null;
                model[0]=null;
            }

        }else if(name.equals("sheepcard")){

            if(choice[0]==null){
                choice[0]=Resource.SHEEP;
                this.model[0]=ModelManager.getmodel(ModelManager.SHEEPCARD);
            }else if(choice[1]==null){
                choice[1]=Resource.SHEEP;
                this.model[1]=ModelManager.getmodel(ModelManager.SHEEPCARD);
            }else if(choice[0]==Resource.SHEEP && choice[1]==Resource.SHEEP){
                this.choice[1]=null;
                this.model[1]=null;
                this.choice[0]=null;
                this.model[0]=null;
            }else if(choice[1]==Resource.SHEEP){
                choice[1]=null;
                model[1]=null;
            }else if(choice[0]==Resource.SHEEP){
                choice[0]=null;
                model[0]=null;
            }

        }else if(name.equals("orecard")){

            if(choice[0]==null){
                choice[0]=Resource.ORE;
                this.model[0]=ModelManager.getmodel(ModelManager.ORECARD);
            }else if(choice[1]==null){
                choice[1]=Resource.ORE;
                this.model[1]=ModelManager.getmodel(ModelManager.ORECARD);
            }else if(choice[0]==Resource.ORE && choice[1]==Resource.ORE){
                this.choice[1]=null;
                this.model[1]=null;
                this.choice[0]=null;
                this.model[0]=null;
            }else if(choice[1]==Resource.ORE){
                choice[1]=null;
                model[1]=null;
            }else if(choice[0]==Resource.ORE){
                choice[0]=null;
                model[0]=null;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(model[0]!=null){
            Renderer.renderModel(model[0], 10,scene.bottomBar.y-110,66,100,graphics);
        }
        if(model[1]!=null) {
            Renderer.renderModel(model[1], 86,scene.bottomBar.y-110,66,100,graphics);
        }
    }
}
