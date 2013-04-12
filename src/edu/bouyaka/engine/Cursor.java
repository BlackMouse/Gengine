package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Cursor extends Entity implements MouseListener,
		MouseMotionListener {
	Vector pos = new Vector(), lastP = new Vector(), lastR = new Vector();

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		System.out.println(arg0.getX() + ":" + arg0.getY());
		engine.display.setColor(Color.red);
		engine.display.drawCross(arg0.getX(), arg0.getY(), 3);
		System.out.println(arg0.getButton());
	}

	public void mouseReleased(MouseEvent arg0) {
		System.out.println(arg0.getX() + ":" + arg0.getY());
		engine.display.setColor(Color.red);
		engine.display.drawCross(arg0.getX(), arg0.getY(), 3);
		lastR.setR(arg0.getX(), arg0.getY());
		System.out.println(arg0.getButton());
	}

	public void mouseDragged(MouseEvent arg0) {
		pos.setR(arg0.getX(), arg0.getY());
		System.out.println(arg0.getX() + ":" + arg0.getY());
		engine.display.setColor(Color.red);
		engine.display.drawCross(arg0.getX(), arg0.getY(), 3);
	}

	public void mouseMoved(MouseEvent arg0) {
		pos.setR(arg0.getX(), arg0.getY());
		System.out.println(arg0.getX() + ":" + arg0.getY());

	}
	public void update(){
		engine.display.drawCross((int)pos.getRX(), (int)pos.getRY(), 3);
	}

}
