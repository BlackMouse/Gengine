package edu.bouyaka.engine.concreted;

import java.awt.Color;
import java.awt.Graphics;

import edu.bouyaka.engine.abstracted.Interface;
import edu.bouyaka.engine.abstracted.Sprite;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;

public class SpriteButton extends Button {
	private SpriteDisplayer spriteDisplayer = new RegularSD(engine);
	private Sprite sprite;

	public SpriteButton() {
		type = "SpriteButton";
	}

	public void setSize(int rW, int rH) {
		this.size[0] = rW;
		this.size[1] = rH;
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

	public void frameIncr() {
		spriteDisplayer.frameIncr();

	}

	public void frameDecr() {
		spriteDisplayer.frameDecr();

	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public void setInterface(Interface E) {
		fatherInterface = E;
	}
}
