package platform;



import platform.render.ModelManager;
import platform.states.GameStateManager;

import java.awt.EventQueue;




public class Game {
	private static boolean running = false;
	private static GameStateManager stateManager;

	public static void main(String[] args) {
		System.out.println("[Main][Game]: Starting...");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ModelManager.init();
				DisplayJframe.createDisplay();

				stateManager = new GameStateManager();
				running = true;
				
				System.out.println("[Main][Game]: Started!");
			}
			
		});
	}

	//Checks if the game is running
	public static boolean isRunning() {
		return running;
	}
	//Gets game state manager
	public static GameStateManager getStateManager() {return stateManager;}
}
