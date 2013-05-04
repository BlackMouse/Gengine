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

	public void moveUp(int n);

	public void moveDown(int n);

	public void moveLeft(int n);

	public void moveRight(int n);

	public void moveUpRight(int n);

	public void moveDownRight(int n);

	public void moveUpLeft(int n);

	public void moveDownLeft(int n);

	public void move();

	public void move(int direction);

}
