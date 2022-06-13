package platform.states;

import platform.GameSettings;
import platform.entities.Player;

import javax.swing.*;
import java.awt.*;

public abstract class GameState extends JPanel {

	protected GameStateManager gsm;
	protected GameSettings settings;
	protected Player currentplayer;
	//Creates game state


	public GameState(GameStateManager gsm, GameSettings settings) {
		this.gsm = gsm;
		this.settings = settings;
	}

	public GameState(GameStateManager gsm, GameSettings settings, Player currentplayer) {
		this.gsm = gsm;
		this.settings = settings;
		this.currentplayer = currentplayer;
	}

	public abstract void reinit();
	public void applyStrategy(){}

	public GameStateManager getGsm() {return gsm;}
	public GameSettings getSettings() {return settings;}
	public Player getCurrentplayer() {return currentplayer;}

	public abstract void render(Graphics graphics);


	public void keyPressed(int key){}
	public void keyReleased(int key){}
	public void mouseMoved( int x, int y){}
	public void mouseClicked( int x, int y){}
	public void mousePressed( int x, int y){}
	public void mouseReleased( int x, int y){}
	public void mouseDragged( int x, int y){}
	public void mouseEntered( int x, int y){}
	public void mouseExited( int x, int y){}


}
