package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.Gengine;

public class KeyControlledM implements Movings {

	Gengine engine;
	Entity E;

	private int upKey, downKey, leftKey, rightKey;

	// Position
	public double[] objectPos;
	public int[] objectSize;

	public KeyControlledM(double[] objectPos, int[] objectSize, Gengine engine,
			Entity E) {
		this.engine = engine;
		this.objectPos = objectPos;
		this.objectSize = objectSize;
		this.E = E;
	}

	public void moveUp(int n) {
		if (objectPos[1] - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			objectPos[1] = objectPos[1] - 1.0 * n * engine.tick;

		}
	}

	public void moveDown(int n) {
		if (objectPos[1] + 1.0 * n * engine.tick < engine.displayHeight
				- objectSize[1] / 2) {
			objectPos[1] = objectPos[1] + 1.0 * n * engine.tick;

		}
	}

	public void moveLeft(int n) {
		if (objectPos[0] - 1.0 * n * engine.tick >= objectSize[0] / 2) {
			objectPos[0] = objectPos[0] - 1.0 * n * engine.tick;

		}
	}

	public void moveRight(int n) {
		for (int k = 0; k < n; k++) {
			if (objectPos[0] + 1.0 * n * engine.tick < engine.displayWidth
					- objectSize[0] / 2) {
				objectPos[0] = objectPos[0] + 1.0 * n * engine.tick;
			}
		}
	}

	public void moveUpRight(int n) {
		if (objectPos[0] + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& objectPos[1] - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			objectPos[0] = objectPos[0] + 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] - 0.707106781 * n * engine.tick;

		}
	}

	public void moveDownRight(int n) {
		if (objectPos[0] + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& objectPos[1] + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			objectPos[0] = objectPos[0] + 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] + 0.707106781 * n * engine.tick;

		}
	}

	public void moveUpLeft(int n) {
		if (objectPos[0] - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& objectPos[1] - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			objectPos[0] = objectPos[0] - 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] - 0.707106781 * n * engine.tick;

		}
	}

	public void moveDownLeft(int n) {
		if (objectPos[0] - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& objectPos[1] + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			objectPos[0] = objectPos[0] - 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] + 0.707106781 * n * engine.tick;

		}
	}

	public double getX() {
		return objectPos[0];
	}

	public double getY() {
		return objectPos[1];
	}

	// D�finition de la position de l'objet
	public void setPos(int x, int y) {
		objectPos[0] = x;
		objectPos[1] = y;
	}

	public double[] getPos() {
		return objectPos;
	}

	public void move() {
		if (engine.keyboard[upKey] && engine.keyboard[leftKey]) {
			moveUpLeft(2);

		} else if (engine.keyboard[upKey] && engine.keyboard[rightKey]) {
			moveUpRight(2);

		} else if (engine.keyboard[downKey] && engine.keyboard[leftKey]) {
			moveDownLeft(2);

		} else if (engine.keyboard[downKey] && engine.keyboard[rightKey]) {
			moveDownRight(2);

		} else if (engine.keyboard[upKey])
			moveUp(2);

		else if (engine.keyboard[downKey])
			moveDown(2);

		else if (engine.keyboard[leftKey])
			moveLeft(2);

		else if (engine.keyboard[rightKey])
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
