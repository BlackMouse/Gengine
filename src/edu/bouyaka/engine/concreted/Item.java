package edu.bouyaka.engine.concreted;

import java.awt.Color;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.interfaces.Movings;
import edu.bouyaka.engine.interfaces.RegularM;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;
import edu.bouyaka.engine.media.Sprite;

public class Item extends Concrete {
	private Movings movings;
	private SpriteDisplayer spriteDisplayer;
	private Sprite sprite;

	public Item() {
		movings = new RegularM(pos, size, this);
		spriteDisplayer = new RegularSD();
		type = "Item";
	}

	public void update() {
		if (sprite.isAnimated())
			frameIncr();
		move();
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

	// D�placement de l'entit�e

	public void moveUp(double n) {
		movings.moveUp(n);
	}

	public void moveDown(double n) {
		movings.moveDown(n);
	}

	public void moveLeft(double n) {
		movings.moveLeft(n);
	}

	public void moveRight(double n) {
		movings.moveRight(n);
	}

	public void moveUpRight(double n) {
		movings.moveUpRight(n);
	}

	public void moveDownRight(double n) {
		movings.moveDownRight(n);
	}

	public void moveUpLeft(double n) {
		movings.moveUpLeft(n);
	}

	public void moveDownLeft(double n) {
		movings.moveDownLeft(n);
	}

	public Vector getPos() {
		return pos;
	}

	public void move(int direction) {
		movings.move(direction);
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		spriteDisplayer.setSprite(sprite);
		size[0] = sprite.getWidth();
		size[1] = sprite.getHeight();

	}

	public void setFrameId(int frameId) {
		spriteDisplayer.setFrameId(frameId);

	}

	public void setNFrame(int nFrame) {
		spriteDisplayer.setNFrame(nFrame);

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
		setCollisionEnabled(flag);
	}

}
