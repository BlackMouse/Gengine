package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.Sprite;

public interface SpriteDisplayer {

	public void setSprite(Sprite sprite);

	public void setFrameId(int frame);

	public void setNFrame(int nFrame);

	public void setSFrameRate(int sFrameRate);

	public int getFrameId();

	public void loop(boolean loop);

	public void frameIncr();

	public void frameDecr();

	public void show(int x, int y);
}
