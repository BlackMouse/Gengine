package edu.bouyaka.engine.concreted;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.Interface;

public class Button extends Concrete {

	Font f;
	private Color textColor;
	private Color buttonColor;
	protected String text = "";
	private int textSize, lineN = 1, maxLineW;
	protected Interface fatherInterface;
	private boolean widthFixed, heightFixed, textCentered;
	protected Graphics g;

	public Button() {
		clickable = true;
		setColor(new Color(0, 0, 0, 255));
		setTextColor(new Color(255, 255, 255, 255));
		setTextSize(engine.displayHeight / 60);
		type = "Button";

	}

	/**
	 * Permet de definir la taille du bouton
	 * 
	 * @param rW
	 *            : largeur
	 * @param rH
	 *            : hauteur
	 */
	public void setSize(int rW, int rH) {
		this.size[0] = rW;
		this.size[1] = rH;
	}

	public void fixWidth(boolean state) {
		widthFixed = state;
	}

	public void fixHeight(boolean state) {
		heightFixed = state;
	}

	/**
	 * Permet de definir la couleur du fond
	 * 
	 * @param buttonColor
	 *            : couleur du fond
	 */
	public void setColor(Color buttonColor) {
		this.buttonColor = buttonColor;

	}

	/**
	 * Permet de definir la couleur du texte
	 * 
	 * @param textColor
	 *            : couleur du texte
	 */
	public void setTextColor(Color textColor) {
		this.textColor = textColor;

	}

	/**
	 * Permet de definir la hauteur du texte
	 * 
	 * @param textSize
	 *            : hauteur en pixel du texte
	 */
	public void setTextSize(int textSize) {
		this.textSize = textSize;
		f = new Font("Courier New", Font.BOLD, textSize);

	}

	/**
	 * Permet de rajouter du texte au texte existant
	 * 
	 * @param text
	 *            : texte a rajouter
	 */
	public void addText(String text) {
		this.text = this.text + text;
	}

	/**
	 * Permet de remplacer le texte existant par un nouveau
	 * 
	 * @param text
	 *            : nouveau texte
	 */
	public void replaceText(String text) {
		this.text = String.valueOf(text);
	}

	/**
	 * Permet d'afficher le bouton
	 */
	public void show() {
		if (g == null) {
			g = engine.display.getG();
		}
		g.setFont(f);
		int textHeight = g.getFontMetrics().getHeight();

		lineN = 0;

		for (String line : text.split("\n")) {
			if (line.length() > maxLineW) {
				maxLineW = line.length();
			}
			lineN++;
		}
		if (!widthFixed) {
			size[0] = textHeight * (maxLineW + 1) / 2;
		}
		if (!heightFixed) {
			size[1] = textHeight * lineN;
		}

		g.setColor(buttonColor);
		g.fillRect((int) (pos.getRX() - size[0] / (2)),
				(int) (pos.getRY() - size[1] / 2), size[0], size[1]);

		g.setColor(textColor);

		try {
			int lineId = 0;
			for (String line : text.split("\n")) {
				if (textCentered) {
					g.drawString(
							line,
							(int) (pos.getRX() - size[0] / 2 + (size[0] - maxLineW
									* textSize / 1.75) / 2), (int) (pos.getRY()
									- size[1] / 2 + (lineId + 0.75)
									* textHeight));
				} else {
					g.drawString(line, (int) (pos.getRX() - size[0] / 2 + 2),
							(int) (pos.getRY() - size[1] / 2 + (lineId + 0.75)
									* textHeight));
				}
				lineId++;
			}

		} catch (Exception e) {
		}

		size[1] = size[1] * lineN;

	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public void setInterface(Interface Interface) {
		fatherInterface = Interface;
	}

	public boolean isEnabled() {

		if (fatherInterface != null && fatherInterface.isEnabled()) {
			return enabled;
		}
		return false;
	}

	public boolean isVisible() {

		if (fatherInterface.isVisible()) {
			return visible;
		}
		return false;
	}

	public void centerText(boolean state) {
		textCentered = state;
	}

}
