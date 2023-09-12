package scenes.states;


import Jade.Window;
import renderer.Button;
import scenes.PlayingScene;


import java.awt.*;

public class DiscardState extends State{

    int cards=0;

    public DiscardState(PlayingScene scene,int cards) {
        super(scene);
        System.out.println("[Discard] making State");
        this.cards=cards;
        for(Button button : scene.controls){button.setActive(button.getText().equals("exit"));}
        for(Button button : scene.devCards){button.setActive(false);}
        for(Button button : scene.resCards){button.setActive(true);}
        if(cards==0) {
            if(scene.players[scene.currentPlayer]== scene.human) {
                scene.setState(new ForcedPlaceRobberState(scene));
            }else{
                scene.setState(new WaitingState(scene));
            }
        }
    }



    @Override
    public void buttonClicked(String name) {
        if (name.equals("exit")) {

            Window.changeScene(31);

        } else if(name.equals("lumbercard")){

            if(scene.human.lumber>0) {
                scene.human.lumber--;
                this.cards--;
            }

        }else if(name.equals("brickcard")){

            if(scene.human.brick>0) {
                scene.human.brick--;
                this.cards--;
            }

        }else if(name.equals("wheatcard")){

            if(scene.human.wheat>0) {
                scene.human.wheat--;
                this.cards--;
            }

        }else if(name.equals("sheepcard")){

            if(scene.human.sheep>0) {
                scene.human.sheep--;
                this.cards--;
            }

        }else if(name.equals("orecard")){

            if(scene.human.ore>0) {
                scene.human.ore--;
                this.cards--;
                if(cards==0) {
                }
            }
        }

        if(this.cards==0) {
            if(scene.players[scene.currentPlayer]== scene.human) {
                scene.setState(new ForcedPlaceRobberState(scene));
            }else{
                scene.setState(new WaitingState(scene));
            }
        }

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics.drawString("you have to discard "+this.cards+" cards", scene.sideBar.x+5, scene.sideBar.y+scene.sideBar.height-20);
    }








}
