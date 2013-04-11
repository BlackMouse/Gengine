package edu.bouyaka.engine;

public class Concrete extends Entity {
	protected Vector pos = new Vector(), spd = new Vector(),
			acc = new Vector();

	public boolean collisionEnabled = true;

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

	public Vector getPos() {
		return pos;
	}

	public double getX() {
		return pos.getX();
	}

	public double getY() {
		return pos.getY();
	}

	public void setX(double X) {
		pos.setX(X);
	}

	public void setY(double Y) {
		pos.setY(Y);
	}

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	/*
	 * DAh Dah DAh
	 * 
	 * @param x como que tal
	 * 
	 * @param y Baila bamba
	 */
	public void setPos(double x, double y) {
		pos.set(x, y);

	}

	public void collideTo(Concrete E) {
	}

}
