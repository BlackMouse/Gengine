package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.abstracted.Vector;

public class KeyControlledM extends Entity implements Movings {

	Concrete E;
	double k = 0.05;

	private int upKey, downKey, leftKey, rightKey;

	// Position
	public Vector pos;
	public int[] objectSize;

	public KeyControlledM(Vector pos, int[] objectSize, Concrete E) {
		this.pos = pos;
		this.objectSize = objectSize;
		this.E = E;
	}

	public void moveUp(double n) {
		if (pos.getRY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setY(pos.getY() - 1.0 * k * n * engine.tick);

		}
	}

	public void moveDown(double n) {
		if (pos.getRY() + 1.0 * n * engine.tick < engine.displayHeight
				- objectSize[1] / 2) {
			pos.setY(pos.getY() + 1.0 * k * n * engine.tick);

		}
	}

	public void moveLeft(double n) {
		if (pos.getRX() - 1.0 * n * engine.tick >= objectSize[0] / 2) {
			pos.setX(pos.getX() - 1.0 * k * n * engine.tick);

		}
	}

	public void moveRight(double n) {
		if (pos.getRX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2) {
			pos.setX(pos.getX() + 1.0 * k * n * engine.tick);

		}
	}

	public void moveUpRight(double n) {
		if (pos.getRX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& pos.getRY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setX(pos.getX() + 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() - 0.707106781 * k * n * engine.tick);

		}
	}

	public void moveDownRight(double n) {
		if (pos.getRX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& pos.getRY() + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			pos.setX(pos.getX() + 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() + 0.707106781 * k * n * engine.tick);

		}
	}

	public void moveUpLeft(double n) {
		if (pos.getRX() - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& pos.getRY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setX(pos.getX() - 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() - 0.707106781 * k * n * engine.tick);

		}
	}

	public void moveDownLeft(double n) {
		if (pos.getRX() - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& pos.getRY() + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			pos.setX(pos.getX() - 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() + 0.707106781 * k * n * engine.tick);

		}
	}

	public void move() {
		if (!engine.typingText) {
			if (engine.keyboard.keyP(upKey) && engine.keyboard.keyP(leftKey)) {
				moveUpLeft(E.getSpeed());

			} else if (engine.keyboard.keyP(upKey)
					&& engine.keyboard.keyP(rightKey)) {
				moveUpRight(E.getSpeed());

			} else if (engine.keyboard.keyP(downKey)
					&& engine.keyboard.keyP(leftKey)) {
				moveDownLeft(E.getSpeed());

			} else if (engine.keyboard.keyP(downKey)
					&& engine.keyboard.keyP(rightKey)) {
				moveDownRight(E.getSpeed());

			} else if (engine.keyboard.keyP(upKey))
				moveUp(E.getSpeed());

			else if (engine.keyboard.keyP(downKey))
				moveDown(E.getSpeed());

			else if (engine.keyboard.keyP(leftKey))
				moveLeft(E.getSpeed());

			else if (engine.keyboard.keyP(rightKey))
				moveRight(E.getSpeed());
		}

	}

	public void move(int direction) {

	}

	public void setUpKey(int key) {
		upKey = key;
	}

	public void setDownKey(int key) {
		downKey = key;
	}

	public void setLeftKey(int key) {
		leftKey = key;
	}

	public void setRightKey(int key) {
		rightKey = key;
	}

	public int getUpKey() {
		return upKey;
	}

	public int getDownKey() {
		return downKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

}
