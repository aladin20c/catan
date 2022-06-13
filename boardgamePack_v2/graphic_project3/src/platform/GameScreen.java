package platform;

import platform.input.Keyboard;
import platform.input.Mouse;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

	//Creates game screen called in DisplayJframe
	public GameScreen() {
		super();
		System.out.println("[Render][GameScreen]: Creating game screen");
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());
		this.addMouseMotionListener(new Mouse());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Game.isRunning()) {
			Game.getStateManager().render(g);
		}
		repaint();
	}
}
