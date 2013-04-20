package edu.bouyaka.engine.concreted;

import java.awt.Color;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.interfaces.Movings;
import edu.bouyaka.engine.interfaces.RegularM;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;

public class Item extends Concrete {
	private Movings movings;
	private SpriteDisplayer spriteDisplayer;

	public Item() {
		movings = new RegularM(pos, size, engine, this);
		spriteDisplayer = new RegularSD();
		type = "Item";
	}

	public void update() {
		frameIncr();
	}

	public void show() {
		engine.display.drawImage(
				engine.Sprite(getSpriteId()).get(spriteDisplayer.getFrame()),
				(int) (pos.getRX() - size[0] / 2),
				(int) (pos.getRY() - size[1] / 2));
		engine.display.setColor(Color.green);
		engine.display.drawRect((int) (pos.getRX() - size[0] / 2),
				(int) (pos.getRY() - size[1] / 2), size[0], size[1]);
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
		size[0] = engine.Sprite(spriteId).getWidth();
		size[1] = engine.Sprite(spriteId).getHeight();

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
		return size[0];
	}

	public int getHeight() {
		return size[1];
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

		if (pos.getY() - E.pos.getY() > 0 && pos.getX() - E.pos.getX() < 0) {
			this.moveLeft(1);
			this.moveDown(1);

		} else if (pos.getY() - E.pos.getY() > 0
				&& pos.getX() - E.pos.getX() > 0) {
			this.moveRight(1);
			this.moveDown(1);

		} else if (pos.getY() - E.pos.getY() < 0
				&& pos.getX() - E.pos.getX() < 0) {
			this.moveLeft(1);
			this.moveUp(1);

		} else if (pos.getY() - E.pos.getY() < 0
				&& pos.getX() - E.pos.getX() > 0) {
			this.moveRight(1);
			this.moveUp(1);

		}
	}

	public boolean isTouchingEdge() {
		if (pos.getX() - size[0] / 2 <= 0 || pos.getY() - size[1] / 2 <= 0
				|| pos.getX() + size[0] / 2 >= engine.screenWidth
				|| pos.getY() + size[1] / 2 >= engine.screenHeight)
			return true;
		return false;
	}
}
