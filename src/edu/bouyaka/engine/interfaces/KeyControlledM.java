package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.Gengine;
import edu.bouyaka.engine.Vector;

public class KeyControlledM implements Movings {

	Gengine engine;
	Entity E;

	private int upKey, downKey, leftKey, rightKey;

	// Position
	public Vector pos;
	public int[] objectSize;

	public KeyControlledM(Vector pos, int[] objectSize, Gengine engine, Entity E) {
		this.engine = engine;
		this.pos = pos;
		this.objectSize = objectSize;
		this.E = E;
	}

	public void moveUp(int n) {
		if (pos.getY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setY(pos.getY() - 1.0 * n * engine.tick);

		}
	}

	public void moveDown(int n) {
		if (pos.getY() + 1.0 * n * engine.tick < engine.displayHeight
				- objectSize[1] / 2) {
			pos.setY(pos.getY() + 1.0 * n * engine.tick);

		}
	}

	public void moveLeft(int n) {
		if (pos.getX() - 1.0 * n * engine.tick >= objectSize[0] / 2) {
			pos.setX(pos.getX() - 1.0 * n * engine.tick);

		}
	}

	public void moveRight(int n) {
			if (pos.getX() + 1.0 * n * engine.tick < engine.displayWidth
					- objectSize[0] / 2) {
				pos.setX(pos.getX() + 1.0 * n * engine.tick);
			
		}
	}

	public void moveUpRight(int n) {
		if (pos.getX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& pos.getY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setX(pos.getX() + 0.707106781 * n * engine.tick);
			pos.setY(pos.getY() - 0.707106781 * n * engine.tick);

		}
	}

	public void moveDownRight(int n) {
		if (pos.getX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& pos.getY() + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			pos.setX(pos.getX() + 0.707106781 * n * engine.tick);
			pos.setY(pos.getY() + 0.707106781 * n * engine.tick);

		}
	}

	public void moveUpLeft(int n) {
		if (pos.getX() - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& pos.getY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setX(pos.getX() - 0.707106781 * n * engine.tick);
			pos.setY(pos.getY() - 0.707106781 * n * engine.tick);

		}
	}

	public void moveDownLeft(int n) {
		if (pos.getX() - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& pos.getY() + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			pos.setX(pos.getX() - 0.707106781 * n * engine.tick);
			pos.setY(pos.getY() + 0.707106781 * n * engine.tick);

		}
	}

	public double getX() {
		return pos.getX();
	}

	public double getY() {
		return pos.getY();
	}

	// D�finition de la position de l'objet
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	public Vector getPos() {
		return pos;
	}

	public void move() {
		if (engine.keyboard.keyP(upKey) && engine.keyboard.keyP(leftKey)) {
			moveUpLeft(2);

		} else if (engine.keyboard.keyP(upKey) && engine.keyboard.keyP(rightKey)) {
			moveUpRight(2);

		} else if (engine.keyboard.keyP(downKey) && engine.keyboard.keyP(leftKey)) {
			moveDownLeft(2);

		} else if (engine.keyboard.keyP(downKey) && engine.keyboard.keyP(rightKey)) {
			moveDownRight(2);

		} else if (engine.keyboard.keyP(upKey))
			moveUp(2);

		else if (engine.keyboard.keyP(downKey))
			moveDown(2);

		else if (engine.keyboard.keyP(leftKey))
			moveLeft(2);

		else if (engine.keyboard.keyP(rightKey))
			moveRight(2);

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

}
