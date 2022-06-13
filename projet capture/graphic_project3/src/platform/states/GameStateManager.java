package platform.states;

import platform.GameScreen;
import platform.GameSettings;

import java.awt.*;
import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> states;
	
	//Creates the game state manager
	public GameStateManager() {
		this.states = new Stack<GameState>();
		this.states.add(new MenuState(this,new GameSettings()));
	}
	public void addState(GameState state) {
		this.states.add(state);
	}

	public void addActiveState(GameState state) {
		this.states.add(state);
		applyStrategy();
	}

	public void removeState() {
		this.states.pop();
		reinit();
		applyStrategy();
	}

	public void reinit() {
		this.states.peek().reinit();
	}
	public void applyStrategy() {
		this.states.peek().applyStrategy();
	}
	public void clearStack() {
		this.states.clear();
	}
	public GameState getActiveState() {
		return this.states.peek();
	}




	//Calls render(Graphics) for the first state in the stack
	// Called in GameScreen after checking Game.isRunning()
	public void render(Graphics graphics) {
		this.states.peek().render(graphics);
	}
	
	//Calls keyPressed(int) for the first state in the stack<br>
	//Called in Keyboard after checking Game.isRunning()

	public void keyPressed(int key) {
		this.states.peek().keyPressed(key);
	}
	
	//Calls keyReleased(int) for the first state in the stack<br>
	// Called in Keyboard after checking Game.isRunning()
	public void keyReleased(int key) {
		this.states.peek().keyReleased(key);
	}

	//Calls mouseAction(intx,inty) for the first state in the stack<br>
	// Called in Mouse after checking Game.isRunning()
	public void mouseDragged( int x, int y) {
		this.states.peek().mouseDragged(x, y);
	}
	public void mouseMoved( int x, int y) {
		this.states.peek().mouseMoved(x, y);
	}
	public void mouseClicked( int x, int y) {
		this.states.peek().mouseClicked(x, y);
	}
	public void mousePressed( int x, int y) {
		this.states.peek().mousePressed(x, y);
	}
	public void mouseReleased( int x, int y) {
		this.states.peek().mouseReleased(x, y);
	}
	public void mouseEntered( int x, int y) {}
	public void mouseExited( int x, int y) {}
}
