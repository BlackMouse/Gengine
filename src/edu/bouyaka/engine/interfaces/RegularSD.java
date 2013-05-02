package edu.bouyaka.engine.interfaces;

import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Gengine;
import edu.bouyaka.engine.abstracted.Sprite;

public class RegularSD implements SpriteDisplayer {

	// Identifiant du sprite representant l'entitee
	private Sprite sprite;

	// Portion du sprite representant l'entitee et portion max
	private int frameId, nFrame = 1, sFrameRate;
	private long lastIncr;
	private BufferedImage currentFrame;
	private Gengine engine;

	public RegularSD(Gengine engine) {
		this.engine = engine;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		nFrame = sprite.getNFrame();
		sFrameRate = sprite.getFrameRate();
		currentFrame = sprite.getFrame(frameId);
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
		currentFrame = sprite.getFrame(frameId);
	}

	public void setNFrame(int nFrame) {
		this.nFrame = nFrame;
	}

	public void setSFrameRate(int sFrameRate) {
		this.sFrameRate = sFrameRate;
	}

	public int getFrameId() {
		return frameId;
	}

	public void frameIncr() {
		if (System.nanoTime() - lastIncr >= 1E9 / sFrameRate) {
			lastIncr = System.nanoTime();

			frameId++;
			if (frameId == nFrame)
				frameId = 0;
			currentFrame = sprite.getFrame(frameId);
		}
	}

	public void frameDecr() {
		if (System.nanoTime() - lastIncr >= 1E9 / sFrameRate) {
			lastIncr = System.nanoTime();

			frameId--;
			if (frameId == -1)
				frameId = nFrame - 1;
			currentFrame = sprite.getFrame(frameId);
		}
	}

	public void show(int x, int y) {

		engine.display.drawImage(currentFrame, x, y);
	}

}
