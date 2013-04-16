package edu.bouyaka.engine.abstracted;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Abstract;

/*
 * Définition des principales propriétés de la fenêtre
 */
public class Sprite extends Abstract{
	private BufferedImage image;
	private int nFrame, height, width, frameWidth, frameHeight, frameRate;

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public Sprite(BufferedImage image, int nFrame, int frameRate) {
		this.image = image;
		this.nFrame = nFrame;
		this.width = image.getWidth();
		this.height = this.frameHeight = image.getHeight();
		this.frameWidth = width / nFrame;
		this.frameRate = frameRate;
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public int getWidth() {
		return frameWidth;
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public int getHeight() {
		return frameHeight;
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public int getNFrame() {
		return nFrame;
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public int getFrameRate() {
		return frameRate;
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */

	public BufferedImage get(int index) {
		if (index * frameWidth < width && index != -1)
			return image.getSubimage(index * frameWidth, 0, frameWidth,
					frameHeight);
		else
			return image.getSubimage(0, 0, frameWidth, frameHeight);
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public void scale(double pourcentage) {
	}

	/*
	 * Définition des principales propriétés de la fenêtre
	 */
	public void scale(int newWidth, int newHeight, String type) {
		if (type == "proportionnal") {
			if (newWidth * (height / width) >= newHeight)
				newWidth = nFrame*width * newHeight / height;
			else {
				newHeight = height*nFrame* newWidth / width;
				newWidth = nFrame*newWidth;
			}

			BufferedImage tmp = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = tmp.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
					image.getWidth(), height, null);
			g.dispose();
			image = tmp;
		} else {
			newWidth = nFrame*newWidth;
			BufferedImage tmp = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_ARGB);
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
}
