package edu.bouyaka.engine.interfaces;

public interface Movings {

	public void setUpKey(int key);

	public void setDownKey(int key);

	public void setLeftKey(int key);

	public void setRightKey(int key);

	public int getUpKey();

	public int getDownKey();

	public int getLeftKey();

	public int getRightKey();

	public void moveUp(double n);

	public void moveDown(double n);

	public void moveLeft(double n);

	public void moveRight(double n);

	public void moveUpRight(double n);

	public void moveDownRight(double n);

	public void moveUpLeft(double n);

	public void moveDownLeft(double n);

	public void move();

	public void move(int direction);

}
