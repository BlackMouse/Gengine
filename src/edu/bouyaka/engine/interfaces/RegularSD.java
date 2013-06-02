package edu.bouyaka.engine.interfaces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Entity;
import edu.bouyaka.engine.media.Sprite;

public class RegularSD extends Entity implements SpriteDisplayer {

	// Identifiant du sprite representant l'entitee
	private Sprite sprite;

	// Portion du sprite representant l'entitee et portion max
	private int frameId, nFrame = 1, sFrameRate;
	private long lastIncr;
	private BufferedImage currentFrame;
	private Graphics g;

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
			if (frameId == nFrame && sprite.isLooped())
				frameId = 0;
			else if (frameId == nFrame && !sprite.isLooped()) {
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
			if (frameId == -1 && sprite.isLooped())
				frameId = nFrame - 1;
			else if (frameId == -1 && !sprite.isLooped()) {
				frameId = 0;
				return;
			}
			currentFrame = sprite.getFrame(frameId);
		}
	}

	public void show(int x, int y) {
		if (g == null) {
			g = engine.display.getG();
		}
		g.drawImage(currentFrame, x, y, null);

	}

}
