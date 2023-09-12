package scenes.states;

import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

public class RollingState extends State{

    public RollingState(PlayingScene scene) {
        super(scene);
        System.out.println("[RollingState] making State");
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit") || button.getText().equals("roll"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(false);}
    }

    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {
            Window.changeScene(31);
            return;
        } else if(name.equals("roll")){
            scene.rollDice();
        }
    }
}
