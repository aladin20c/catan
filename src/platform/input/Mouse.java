package platform.input;



import platform.Game;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseInputListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		Game.getStateManager().mouseDragged(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.getStateManager().mouseMoved(e.getX(), e.getY());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Game.getStateManager().mouseClicked(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Game.getStateManager().mousePressed(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Game.getStateManager().mouseReleased(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO
	}

}
