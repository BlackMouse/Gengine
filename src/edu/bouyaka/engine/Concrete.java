package edu.bouyaka.engine;

public class Concrete extends Entity {
	protected Vector pos = new Vector(), spd = new Vector(),
			acc = new Vector();
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

	public Vector getPos() {
		return pos;
	}

	public double getX() {
		return pos.getX();
	}

	public double getY() {
		return pos.getY();
	}

	public double getRX() {
		return pos.getRX();
	}

	public double getRY() {
		return pos.getRY();
	}

	public void setX(double X) {
		pos.setX(X);
	}

	public void setY(double Y) {
		pos.setY(Y);
	}

	public void setRX(double X) {
		pos.setRX(X);
	}

	public void setRY(double Y) {
		pos.setRY(Y);
	}

	public int getWidth() {
		return size[0];
	}

	public int getHeight() {
		return size[1];
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

	public void setRPos(double x, double y) {
		pos.setR(x, y);

	}

	public void collideTo(Concrete E) {
	}

	public void checkMouse() {
		double mX = engine.cursor.getPos().getRX(), mY = engine.cursor.getPos()
				.getRY();

		if (Math.abs(mX - getRX()) < size[0] / 2
				&& Math.abs(mY - getRY()) < size[1] / 2) {
			if (engine.cursor.getState(1))
				leftPress(mX, mY);

			if (engine.cursor.getState(3))
				rightPress(mX, mY);

			if (engine.cursor.getState(2))
				middlePress(mX, mY);
			hoover(mX, mY);
		}
	}

	private void leftPress(double x, double y) {
		setRPos(x, y);
	}

	private void middlePress(double x, double y) {
		// setRPos(x, y);
	}

	private void rightPress(double x, double y) {
		// setRPos(x, y);
	}

	private void hoover(double x, double y) {
		// setRPos(x, y);
	}
}
