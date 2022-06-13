package platform;

import javax.swing.*;

public class DisplayJframe {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	
	//Creates and shows a JFrame window Called in Main
	public static void createDisplay() {
		System.out.println("[Render][DisplayManager]: Creating window...");
		
		JFrame window = new JFrame("Platform Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 50, WIDTH, HEIGHT);
		window.add(new GameScreen());
		window.setResizable(false);
		window.setVisible(true);
	}
}
