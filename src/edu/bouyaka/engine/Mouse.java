package edu.bouyaka.engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import edu.bouyaka.engine.abstracted.Sprite;
import edu.bouyaka.engine.abstracted.Vector;

public class Mouse extends Entity implements MouseListener, MouseMotionListener {
	private Vector pos = new Vector(), lastP = new Vector(),
			lastR = new Vector(), lastC = new Vector();
	private boolean[] pButton = new boolean[5], cButton = new boolean[5];
	private boolean clicked;
	private Sprite defaultSprite, clickSprite;

	public void mouseClicked(MouseEvent evt) {
		lastC.setR(evt.getX(), evt.getY());
		cButton[evt.getButton()] = true;
		clicked = true;

	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void mousePressed(MouseEvent evt) {
		lastR.setR(evt.getX(), evt.getY());
		if (clickSprite != null)
			engine.display.setCursor(clickSprite);
		pButton[evt.getButton()] = true;
	}

	public void mouseReleased(MouseEvent evt) {
		lastR.setR(evt.getX(), evt.getY());
		if (defaultSprite != null)
			engine.display.setCursor(defaultSprite);
		pButton[evt.getButton()] = false;
	}

	public void mouseDragged(MouseEvent evt) {
		pos.setR(evt.getX(), evt.getY());
	}

	public void mouseMoved(MouseEvent evt) {
		pos.setR(evt.getX(), evt.getY());

	}

	public void setCursorSprite(Sprite s) {
		defaultSprite = s;
		if (engine.display != null)
			engine.display.setCursor(defaultSprite);
	}

	public void setClickSprite(Sprite s) {
		clickSprite = s;
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

	public boolean getPButton(int id) {
		return pButton[id];
	}

	public Vector getLastC() {
		return lastC;
	}

	public boolean getCButton(int id) {
		return cButton[id];
	}

	public boolean isClicked() {
		return clicked;
	}

	public void resetClick() {
		clicked = false;
	}

}
