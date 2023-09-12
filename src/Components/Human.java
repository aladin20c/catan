package Components;


import scenes.PlayingScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Human extends Player {

    public Human(String name, Color color) {
        super(name, color);
        this.cards.add(new Card(Card.robber));

        for (int i =0 ; i<10;i++){
            this.cards.add(new Card(Card.expansion));
        }
        this.cards.add(new Card(Card.monopoly));
        this.cards.add(new Card(Card.progress));
    }

    @Override
    public void applyStrategy(PlayingScene scene) {

    }

    public boolean isHuman(){return true;}
    public boolean isBot(){return false;}

}
