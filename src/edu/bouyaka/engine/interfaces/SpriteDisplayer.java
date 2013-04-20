package edu.bouyaka.engine.interfaces;

public interface SpriteDisplayer {


	public void setSpriteId(int spriteId);

	public void setFrame(int frame);

	public void setNFrame(int nFrame);

	public void setSFrameRate(int sFrameRate);

	public int getSpriteId();

	public int getFrame();

	public void frameIncr();

	public void frameDecr();
}
