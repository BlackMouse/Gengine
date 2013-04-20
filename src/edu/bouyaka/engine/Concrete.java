package edu.bouyaka.engine;

import edu.bouyaka.engine.abstracted.Vector;

public class Concrete extends Entity {
	public Vector pos = new Vector();
	public Vector spd = new Vector();
	public Vector acc = new Vector();
	protected int[] size = new int[2];

	public boolean collisionEnabled = true, fixed = false;

	// Déplacement de l'entitée

	public void moveUp(int n) {
	}

	public void moveDown(int n) {
	}

	public void moveLeft(int n) {
	}

	public void moveRight(int n) {
	}

	public void moveUpRight(int n) {
	}

	public void moveDownRight(int n) {
	}

	public void moveUpLeft(int n) {
	}

	public void moveDownLeft(int n) {
	}

	public boolean isTouchingEdge() {
		return false;
	}

	public int getWidth() {
		return size[0];
	}

	public int getHeight() {
		return size[1];
	}

	public void setWidth(int x) {
		size[0] = x;
	}

	public void setHeight(int y) {
		size[1] = y;
	}

	/*
	 * DAh Dah DAh
	 * 
	 * @param x como que tal
	 * 
	 * @param y Baila bamba
	 */

	public void collideTo(Concrete E) {
	}

	public void checkMouse() {
		int mX = (int) engine.cursor.getPos().getRX(), mY = (int) engine.cursor
				.getPos().getRY();

		if (Math.abs(mX - pos.getRX()) < size[0] / 2
				&& Math.abs(mY - pos.getRY()) < size[1] / 2) {
			if (engine.cursor.isClicked()) {
				System.out.println("click");
				if (engine.cursor.getCButton(1))
					leftClick(mX, mY);

				if (engine.cursor.getCButton(3))
					rightClick(mX, mY);

				if (engine.cursor.getCButton(2))
					middleClick(mX, mY);
			} else {
				if (engine.cursor.getPButton(1))
					leftPress(mX, mY);

				if (engine.cursor.getPButton(3))
					rightPress(mX, mY);

				if (engine.cursor.getPButton(2))
					middlePress(mX, mY);
			}
			hoover(mX, mY);
		}
	}

	protected void leftPress(int x, int y) {
	}

	protected void middlePress(int x, int y) {
	}

	protected void rightPress(int x, int y) {
	}

	protected void leftClick(int x, int y) {
	}

	protected void middleClick(int x, int y) {
	}

	protected void rightClick(int x, int y) {
	}

	protected void hoover(int x, int y) {
	}
}
