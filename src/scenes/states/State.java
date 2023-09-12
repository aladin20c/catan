package scenes.states;

import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;

public abstract class State {


    public PlayingScene scene;
    public ArrayList<Button> buttons;

    public State(PlayingScene scene) {
        this.scene = scene;
        this.buttons=new ArrayList<>();
    }

    public abstract void  buttonClicked(String name);

    public void render(Graphics graphics) {
        for (Button button : buttons){
            button.render(graphics);
        }
    }

}
