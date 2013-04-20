package edu.bouyaka.engine.concreted;

import java.awt.Graphics;

import edu.bouyaka.engine.abstracted.Interface;
import edu.bouyaka.engine.interfaces.RegularSD;
import edu.bouyaka.engine.interfaces.SpriteDisplayer;

public class SpriteButton extends Button {
	private SpriteDisplayer spriteDisplayer = new RegularSD();

	public SpriteButton() {
		type = "SpriteButton";
	}

	public void setSize(int rW, int rH) {
		this.size[0] = rW;
		this.size[1] = rH;
	}

	public void show() {
		engine.display.drawImage(
				engine.Sprite(getSpriteId()).get(spriteDisplayer.getFrame()),
				(int) (pos.getRX() - size[0] / 2),
				(int) (pos.getRY() - size[1] / 2));

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
