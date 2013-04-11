package edu.bouyaka.engine;

import java.awt.Color;
import edu.bouyaka.engine.interfaces.Movings;
import edu.bouyaka.engine.interfaces.RegularM;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;

public class Item extends Concrete {
	private Movings movings;
	private SpriteDisplayer spriteDisplayer;
	private int[] spriteSize = new int[2];

	public Item() {
		movings = new RegularM(pos, spriteSize, engine, this);
		spriteDisplayer = new RegularSD();
		type = "Item";
	}

	public void update() {
		frameIncr();
	}

	public void show() {
		engine.display.drawImage(
				engine.Sprite(getSpriteId()).get(spriteDisplayer.getFrame()),
				(int) (pos.getX() - spriteSize[0] / 2),
				(int) (pos.getY() - spriteSize[1] / 2));
		engine.display.setColor(Color.green);
		engine.display.drawRect((int) (pos.getX() - spriteSize[0] / 2),
				(int) (pos.getY() - spriteSize[1] / 2), spriteSize[0],
				spriteSize[1]);
	}

	// Déplacement de l'entitée

	public void moveUp(int n) {
		movings.moveUp(n);
	}

	public void moveDown(int n) {
		movings.moveDown(n);
	}

	public void moveLeft(int n) {
		movings.moveLeft(n);
	}

	public void moveRight(int n) {
		movings.moveRight(n);
	}

	public void moveUpRight(int n) {
		movings.moveUpRight(n);
	}

	public void moveDownRight(int n) {
		movings.moveDownRight(n);
	}

	public void moveUpLeft(int n) {
		movings.moveUpLeft(n);
	}

	public void moveDownLeft(int n) {
		movings.moveDownLeft(n);
	}

	public Vector getPos() {
		return pos;
	}

	public void move(int direction) {
		movings.move(direction);
	}

	

	public void setSpriteId(int spriteId) {
		spriteDisplayer.setSpriteId(spriteId);
		spriteSize[0] = engine.Sprite(spriteId).getWidth();
		spriteSize[1] = engine.Sprite(spriteId).getHeight();

	}

	public void setFrame(int frame) {
		spriteDisplayer.setFrame(frame);

	}

	public void setNFrame(int nFrame) {
		spriteDisplayer.setNFrame(nFrame);

	}

	public void setSFrameRate(int sFrameRate) {
		spriteDisplayer.setSFrameRate(sFrameRate);

	}

	public int getSpriteId() {
		return spriteDisplayer.getSpriteId();
	}

	public int getFrame() {
		return spriteDisplayer.getFrame();
	}

	public int getWidth() {
		return spriteSize[0];
	}

	public int getHeight() {
		return spriteSize[1];
	}

	public void frameIncr() {
		spriteDisplayer.frameIncr();

	}

	public void frameDecr() {
		spriteDisplayer.frameDecr();

	}


	public void enableCollision(boolean flag) {
		collisionEnabled = flag;
	}

	public void Collide(Concrete E) {

		if (getY() - E.getY() > 0 && getX() - E.getX() < 0) {
			moveLeft(2);
			moveDown(2);

		} else if (getY() - E.getY() > 0 && getX() - E.getX() > 0) {
			moveRight(2);
			moveDown(2);

		} else if (getY() - E.getY() < 0 && getX() - E.getX() < 0) {
			moveLeft(2);
			moveUp(2);

		} else if (getY() - E.getY() < 0 && getX() - E.getX() > 0) {
			moveRight(2);
			moveUp(2);

		}
	}

	public boolean isTouchingEdge() {
		if (pos.getX() - spriteSize[0] / 2 <= 0
				|| pos.getY() - spriteSize[1] / 2 <= 0
				|| pos.getX() + spriteSize[0] / 2 >= 0
				|| pos.getY() + spriteSize[1] / 2 >= 0)
			return true;
		return false;
	}
}
