package scenes.states;

import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;

public class WaitingState extends State{

    public WaitingState(PlayingScene scene) {
        super(scene);
        System.out.println("[WaitingState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(false);}
    }


    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {
            Window.changeScene(31);
            return;
        }
    }

}
