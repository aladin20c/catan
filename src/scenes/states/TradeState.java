package scenes.states;

import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;
import utils.Resource;

import java.awt.*;
import java.util.ArrayList;

public class TradeState extends State{

    ArrayList<Resource> resourcesOut;
    ArrayList<Resource> resourcesIn;

    public TradeState(PlayingScene scene, Resource resource) {
        super(scene);
        System.out.println("[TradeState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit") || button.getText().equals("next"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(true);}

        resourcesOut=new ArrayList<>();
        resourcesIn=new ArrayList<>();



    }

    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {

            Window.changeScene(31);

        }else if (name.equals("next")) {
            //todo
            scene.setState(new MainState(scene));
        }
        //todo
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
    }



}
