package edu.bouyaka.engine;

public class Concrete extends Entity {
	protected double[] objectPos = new double[2];

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
	
	public boolean isTouchingEdge(){
		return false;
	}

	public double[] getPos() {
		return objectPos;
	}

	public double getX() {
		return objectPos[0];
	}

	public double getY() {
		return objectPos[1];
	}

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	
	/*
	 * DAh Dah DAh
	 * @param x como que tal
	 * @param y Baila bamba
	 */
	public void setPos(int x, int y) {
		objectPos[0] = x;
		objectPos[1] = y;

	}

	public void collideTo(Concrete E) {
	}

}
