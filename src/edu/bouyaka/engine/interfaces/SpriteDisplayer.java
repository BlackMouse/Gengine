package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.abstracted.Sprite;

public interface SpriteDisplayer {

	public void setSprite(Sprite sprite);

	public void setFrameId(int frame);

	public void setNFrame(int nFrame);

	public void setSFrameRate(int sFrameRate);

	public int getFrameId();

	public void frameIncr();

	public void frameDecr();

	public void show(int x, int y);
}
