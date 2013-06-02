package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.abstracted.Vector;

public class RegularM extends Entity implements Movings {
	Concrete E;
	double k = 0.05;

	// Position
	public Vector pos;
	public int[] objectSize;

	public RegularM(Vector pos, int[] objectSize, Concrete E) {
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
	}

	public void move(int key) {

		// Haut
		if (key == 0) {
			moveUp(E.getSpeed());
		}

		// Haut-droite
		else if (key == 1) {
			moveUpRight(E.getSpeed());
		}

		// Droite
		else if (key == 2) {
			moveRight(E.getSpeed());
		}

		// Droite-bas
		else if (key == 3) {
			moveDownRight(E.getSpeed());
		}

		// Bas
		else if (key == 4) {
			moveDown(E.getSpeed());
		}

		// Bas-Gauche
		else if (key == 5) {
			moveDownLeft(E.getSpeed());
		}

		// Gauche
		else if (key == 6) {
			moveLeft(E.getSpeed());
		}

		// Gauche-haut
		else if (key == 7) {
			moveUpLeft(E.getSpeed());
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

	@Override
	public int getUpKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDownKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeftKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightKey() {
		// TODO Auto-generated method stub
		return 0;
	}

}
