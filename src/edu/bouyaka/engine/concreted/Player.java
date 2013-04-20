package edu.bouyaka.engine.concreted;

import java.awt.Color;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.interfaces.KeyControlledM;
import edu.bouyaka.engine.interfaces.Movings;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;

public class Player extends Concrete {
	private Movings movings;
	private SpriteDisplayer spriteDisplayer;

	public Player() {
		movings = new KeyControlledM(pos, size, engine, this);
		spriteDisplayer = new RegularSD();
		type = "Player";
	}

	public void update() {
		move();
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

	// D�placement de l'entit�e

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

	public void move() {
		movings.move();
	}

	public void setUpKey(int key) {
		movings.setUpKey(key);
	}

	public void setDownKey(int key) {
		movings.setDownKey(key);
	}

	public void setLeftKey(int key) {
		movings.setLeftKey(key);
	}

	public void setRightKey(int key) {
		movings.setRightKey(key);
	}

	public void setSpriteId(int id) {
		spriteDisplayer.setSpriteId(id);
		size[0] = engine.Sprite(id).getWidth();
		size[1] = engine.Sprite(id).getHeight();
		setNFrame(engine.Sprite(id).getNFrame());
		setSFrameRate(engine.Sprite(id).getFrameRate());
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

	public void collideTo(Concrete E) {

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
}
