package edu.bouyaka.engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import edu.bouyaka.engine.abstracted.Vector;

public class Mouse extends Entity implements MouseListener, MouseMotionListener {
	private Vector pos = new Vector(), lastP = new Vector(),
			lastR = new Vector();
	private boolean[] state = new boolean[5];

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void mousePressed(MouseEvent evt) {
		lastR.setR(evt.getX(), evt.getY());
		engine.display.setCursor(engine.Sprite(7));
		state[evt.getButton()] = true;
	}

	public void mouseReleased(MouseEvent evt) {
		lastR.setR(evt.getX(), evt.getY());
		engine.display.setCursor(engine.Sprite(6));
		state[evt.getButton()] = false;
	}

	public void mouseDragged(MouseEvent evt) {
		pos.setR(evt.getX(), evt.getY());
	}

	public void mouseMoved(MouseEvent evt) {
		pos.setR(evt.getX(), evt.getY());

	}

	public Vector getPos() {
		return pos;
	}

	public Vector getLastP() {
		return lastP;
	}

	public Vector getLastR() {
		return lastR;
	}

	public boolean getState(int id) {
		return state[id];
	}

}
