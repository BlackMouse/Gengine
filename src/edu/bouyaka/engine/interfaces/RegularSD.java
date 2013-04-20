package edu.bouyaka.engine.interfaces;


public class RegularSD implements SpriteDisplayer {

	// Identifiant du sprite représentant l'entitée
	private int spriteId;

	// Portion du sprite représentant l'entitée et portion max
	private int frame, nFrame = 1, sFrameRate;
	private long lastIncr;

	public void setSpriteId(int spriteId) {
		this.spriteId = spriteId;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public void setNFrame(int nFrame) {
		this.nFrame = nFrame;
	}

	public void setSFrameRate(int sFrameRate) {
		this.sFrameRate = sFrameRate;
	}

	public int getSpriteId() {
		return spriteId;
	}

	public int getFrame() {
		return frame;
	}

	public void frameIncr() {
		if (System.nanoTime() - lastIncr >= 1E9 / sFrameRate) {
			lastIncr = System.nanoTime();

			frame++;
			if (frame == nFrame)
				frame = 0;
		}
	}

	public void frameDecr() {
		if (System.nanoTime() - lastIncr >= 1E9 / sFrameRate) {
			lastIncr = System.nanoTime();

			frame--;
			if (frame == -1)
				frame = nFrame - 1;
		}
	}

}
