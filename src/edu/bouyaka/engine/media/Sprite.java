package edu.bouyaka.engine.media;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Entity;

/*
 * Definition des principales proprietes de la fenetre
 */
public class Sprite extends Entity {
	private BufferedImage image;
	private int nFrame, frameRate, height, width, frameWidth, frameHeight;
	final boolean enabled = false, visible = false;
	private boolean loop = false, animated = false;

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public Sprite(BufferedImage image, int nFrame, int frameRate) {
		this.width = image.getWidth();
		this.height = this.frameHeight = image.getHeight();
		this.image = engine.screenConfig.createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
		this.image.getGraphics().drawImage(image, 0, 0, null);
		this.nFrame = nFrame;
		if (nFrame > 0)
			animated = true;
		this.frameWidth = width / nFrame;
		this.frameRate = frameRate;
	}

	public void loop(boolean loop) {
		this.loop = loop;
	}

	public boolean isLooped() {
		return loop;
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public int getWidth() {
		return frameWidth;
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public int getHeight() {
		return frameHeight;
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public int getNFrame() {
		return nFrame;
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public int getFrameRate() {
		return frameRate;
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */

	public BufferedImage getFrame(int index) {
		if (index * frameWidth < width && index != -1)
			return image.getSubimage(index * frameWidth, 0, frameWidth,
					frameHeight);
		else
			return image.getSubimage(0, 0, frameWidth, frameHeight);
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public void scale(double pourcentage) {
		int newWidth = (int) (width * pourcentage / 100);
		int newHeight = (int) (height * pourcentage / 100);
		BufferedImage tmp = engine.screenConfig.createCompatibleImage(newWidth,
				newHeight, Transparency.TRANSLUCENT);
		Graphics2D g = tmp.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(),
				image.getHeight(), null);
		g.dispose();
		image = tmp;
		this.width = newWidth;
		this.height = this.frameHeight = newHeight;
		this.frameWidth = width / nFrame;
	}

	/*
	 * Definition des principales proprietes de la fenetre
	 */
	public void scale(int newWidth, int newHeight, String type) {
		if (type == "proportionnal") {
			if (newWidth * (height / width) >= newHeight)
				newWidth = nFrame * width * newHeight / height;
			else {
				newHeight = height * nFrame * newWidth / width;
				newWidth = nFrame * newWidth;
			}

			BufferedImage tmp = engine.screenConfig.createCompatibleImage(
					newWidth, newHeight, Transparency.TRANSLUCENT);
			Graphics2D g = tmp.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
					image.getWidth(), image.getHeight(), null);
			g.dispose();
			image = tmp;
		} else {
			newWidth = nFrame * newWidth;
			BufferedImage tmp = engine.screenConfig.createCompatibleImage(
					newWidth, newHeight, Transparency.TRANSLUCENT);
			Graphics2D g = tmp.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
					image.getWidth(), height, null);
			g.dispose();
			image = tmp;

		}

		this.width = newWidth;
		this.height = this.frameHeight = newHeight;
		this.frameWidth = width / nFrame;
	}

	public boolean isAnimated() {
		return animated;
	}

	public Sprite clone() {
		Sprite tmp = new Sprite(image, nFrame, frameRate);
		tmp.loop(loop);
		return tmp;
	}
}
