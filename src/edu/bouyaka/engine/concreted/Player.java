package edu.bouyaka.engine.concreted;

import java.awt.Color;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.abstracted.Sprite;
import edu.bouyaka.engine.interfaces.KeyControlledM;
import edu.bouyaka.engine.interfaces.Movings;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;

public class Player extends Concrete {
	private Movings movings;
	private SpriteDisplayer spriteDisplayer;
	private Sprite sprite;

	public Player() {
		movings = new KeyControlledM(pos, size, engine, this);
		spriteDisplayer = new RegularSD(engine);
		type = "Player";
	}

	public void update() {
		move();
		frameIncr();
	}

	public void show() {
		size[0] = sprite.getWidth();
		size[1] = sprite.getHeight();
		spriteDisplayer.show((int) (pos.getRX() - size[0] / 2),
				(int) (pos.getRY() - size[1] / 2));
		if (!engine.devMode)
			return;
		engine.display.setColor(Color.green);
		engine.display.drawRect((int) (pos.getRX() - size[0] / 2),
				(int) (pos.getRY() - size[1] / 2), size[0], size[1]);
	}

	// Deplacement de l'entitee

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
		super.move();
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

	public void setSprite(Sprite sprite) {
		this.sprite = sprite.clone();
		spriteDisplayer.setSprite(this.sprite);
		size[0] = sprite.getWidth();
		size[1] = sprite.getHeight();

	}

	public void setFrame(int frame) {
		spriteDisplayer.setFrameId(frame);

	}

	public void setFrameId(int frameId) {
		spriteDisplayer.setFrameId(frameId);

	}

	public void setSFrameRate(int sFrameRate) {
		spriteDisplayer.setSFrameRate(sFrameRate);

	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getFrameId() {
		return spriteDisplayer.getFrameId();
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
