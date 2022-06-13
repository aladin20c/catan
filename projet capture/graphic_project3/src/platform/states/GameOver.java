package platform.states;


import platform.DisplayJframe;
import platform.GameSettings;
import platform.entities.Player;

import java.awt.*;
import java.util.Arrays;


public class GameOver extends GameState {

	public GameOver(GameStateManager gsm,GameSettings settings) {
		super(gsm,settings);
		System.out.println("[GameStates][GameOver]: Creating game over state...");
	}
	public void reinit(){}


	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Arial", Font.PLAIN, 30));
		graphics.drawString("Game Over!", 200, 100);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial", Font.PLAIN, 15));
		graphics.drawString("Press any button", 200, 150);

		graphics.setColor(Color.black);
		graphics.setFont(new Font("Arail", Font.PLAIN, 37));
		Player[]p = settings.getPlayers();

		Arrays.sort(p, (o1, o2) -> Integer.compare(o1.getVP(), o2.getVP()));
		for(int i=0;i<p.length;i++) {
			graphics.drawString(p[i].name, DisplayJframe.WIDTH/2 -200, 250+i*100);
		}
		for(int i=0;i<p.length;i++) {
			graphics.drawString(""+p[i].getVP(), DisplayJframe.WIDTH/2 +200, 250+i*100);
		}
	}

	@Override
	public void keyPressed(int key) {}
	@Override
	public void keyReleased(int key) {
		this.gsm.clearStack();
		this.gsm.addState(new MenuState(gsm,new GameSettings()));
	}
	@Override
	public void mouseReleased( int x, int y) {
		this.gsm.clearStack();
		this.gsm.addState(new MenuState(gsm,new GameSettings()));
	}
}
