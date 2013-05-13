package edu.bouyaka.engine.interfaces;

import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Gengine;
import edu.bouyaka.engine.Sprite;

public class RegularSD implements SpriteDisplayer {

	// Identifiant du sprite representant l'entitee
	private Sprite sprite;

	// Portion du sprite representant l'entitee et portion max
	private int frameId, nFrame = 1, sFrameRate;
	private long lastIncr;
	private BufferedImage currentFrame;
	private Gengine engine;
	private boolean loop = false;

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

	public void loop(boolean loop) {
		this.loop = loop;
	}

	public void frameIncr() {
		if (System.nanoTime() - lastIncr >= 1E9 / sFrameRate) {
			lastIncr = System.nanoTime();

			frameId++;
			if (frameId == nFrame && loop)
				frameId = 0;
			else if (!loop) {
				frameId = nFrame - 1;
				return;
			}
			currentFrame = sprite.getFrame(frameId);
		}
	}

	public void frameDecr() {
		if (System.nanoTime() - lastIncr >= 1E9 / sFrameRate) {
			lastIncr = System.nanoTime();

			frameId--;
			if (frameId == -1 && loop)
				frameId = nFrame - 1;
			else if (!loop) {
				frameId = 0;
				return;
			}
			currentFrame = sprite.getFrame(frameId);
		}
	}

	public void show(int x, int y) {

		engine.display.drawImage(currentFrame, x, y);
	}

}
