package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.Gengine;

public class RegularM implements Movings {
	Gengine engine;
	Entity E;

	// Position
	public double[] objectPos;
	public int[] objectSize;

	public RegularM(double[] objectPos, int[] objectSize, Gengine engine,
			Entity E) {
		this.engine = engine;
		this.objectPos = objectPos;
		this.objectSize = objectSize;
		this.E = E;
	}

	public void moveUp(int n) {
		for (int k = 0; k < n; k++) {
			if (objectPos[1] - n * engine.tick >= objectSize[1] / 2) {
				objectPos[1] = objectPos[1] - n * engine.tick;
			}
		}
	}

	public void moveDown(int n) {
		for (int k = 0; k < n; k++) {
			if (objectPos[1] + n * engine.tick < engine.displayHeight
					- objectSize[1] / 2) {
				objectPos[1] = objectPos[1] + n * engine.tick;
			}
		}
	}

	public void moveLeft(int n) {
		for (int k = 0; k < n; k++) {
			if (objectPos[0] - n * engine.tick >= objectSize[0] / 2) {
				objectPos[0] = objectPos[0] - 1.0 * n * engine.tick;
			}
		}
	}

	public void moveRight(int n) {
		for (int k = 0; k < n; k++) {
			if (objectPos[0] + n * engine.tick < engine.displayWidth
					- objectSize[0] / 2) {
				objectPos[0] = objectPos[0] + 1.0 * n * engine.tick;
			}
		}
	}

	public void moveUpRight(int n) {
		if (objectPos[0] + n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& objectPos[1] - n * engine.tick >= objectSize[1] / 2) {
			objectPos[0] = objectPos[0] + 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] - 0.707106781 * n * engine.tick;

		}
	}

	public void moveDownRight(int n) {
		if (objectPos[0] + n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& objectPos[1] + n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			objectPos[0] = objectPos[0] + 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] + 0.707106781 * n * engine.tick;

		}
	}

	public void moveUpLeft(int n) {
		if (objectPos[0] - n * engine.tick >= objectSize[0] / 2
				&& objectPos[1] - n * engine.tick >= objectSize[1] / 2) {
			objectPos[0] = objectPos[0] - 0.707106781 * n * engine.tick;
			objectPos[1] = objectPos[1] - 0.707106781 * n * engine.tick;

		}
	}

	public void moveDownLeft(int n) {
		if (objectPos[0] - n * engine.tick >= objectSize[0] / 2
				&& objectPos[1] + n * engine.tick < engine.displayHeight
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
	}

	public void move(int key) {

		// Haut
		if (key == 0) {
			moveUp(10);
		}

		// Haut-droite
		else if (key == 1) {
			moveUpRight(10);
		}

		// Droite
		else if (key == 2) {
			moveRight(10);
		}

		// Droite-bas
		else if (key == 3) {
			moveDownRight(10);
		}

		// Bas
		else if (key == 4) {
			moveDown(10);
		}

		// Bas-Gauche
		else if (key == 5) {
			moveDownLeft(10);
		}

		// Gauche
		else if (key == 6) {
			moveLeft(10);
		}

		// Gauche-haut
		else if (key == 7) {
			moveUpLeft(10);
		}

	}

	public void setUpKey(int key) {
	}

	public void setDownKey(int key) {
	}

	public void setLeftKey(int key) {
	}

	public void setRightKey(int key) {
	}

}
