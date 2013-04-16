package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.Gengine;
import edu.bouyaka.engine.abstracted.Vector;

public class RegularM implements Movings {
	Gengine engine;
	Entity E;
	double k = 0.01;

	// Position
	public Vector pos;
	public int[] objectSize;

	public RegularM(Vector pos, int[] objectSize, Gengine engine, Entity E) {
		this.engine = engine;
		this.pos = pos;
		this.objectSize = objectSize;
		this.E = E;
	}

	public void moveUp(int n) {
		if (pos.getRY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setY(pos.getY() - 1.0 * k * n * engine.tick);

		}
	}

	public void moveDown(int n) {
		if (pos.getRY() + 1.0 * n * engine.tick < engine.displayHeight
				- objectSize[1] / 2) {
			pos.setY(pos.getY() + 1.0 * k * n * engine.tick);

		}
	}

	public void moveLeft(int n) {
		if (pos.getRX() - 1.0 * n * engine.tick >= objectSize[0] / 2) {
			pos.setX(pos.getX() - 1.0 * k * n * engine.tick);

		}
	}

	public void moveRight(int n) {
		if (pos.getRX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2) {
			pos.setX(pos.getX() + 1.0 * k * n * engine.tick);

		}
	}

	public void moveUpRight(int n) {
		if (pos.getRX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& pos.getRY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setX(pos.getX() + 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() - 0.707106781 * k * n * engine.tick);

		}
	}

	public void moveDownRight(int n) {
		if (pos.getRX() + 1.0 * n * engine.tick < engine.displayWidth
				- objectSize[0] / 2
				&& pos.getRY() + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			pos.setX(pos.getX() + 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() + 0.707106781 * k * n * engine.tick);

		}
	}

	public void moveUpLeft(int n) {
		if (pos.getRX() - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& pos.getRY() - 1.0 * n * engine.tick >= objectSize[1] / 2) {
			pos.setX(pos.getX() - 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() - 0.707106781 * k * n * engine.tick);

		}
	}

	public void moveDownLeft(int n) {
		if (pos.getRX() - 1.0 * n * engine.tick >= objectSize[0] / 2
				&& pos.getRY() + 1.0 * n * engine.tick < engine.displayHeight
						- objectSize[1] / 2) {
			pos.setX(pos.getX() - 0.707106781 * k * n * engine.tick);
			pos.setY(pos.getY() + 0.707106781 * k * n * engine.tick);

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
		pos.set(x, y);
	}

	public Vector getPos() {
		return pos;
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
