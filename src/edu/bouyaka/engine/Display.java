package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	protected int width, height;
	protected BufferedImage displayedContent, editedContent;
	protected Graphics2D cG, dG;
	protected Color backGroundColor;
	private Gengine engine;

	/*
	 * D�finition des principales propri�t�s de la zone d'affichage
	 */
	public Display(int width, int height, final Gengine engine) {
		this.width = width;
		this.height = height;
		this.engine = engine;
		// Cr�ation d'une tampon d'affichage "dessin"
		displayedContent = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Cr�ation d'une tampon de "dessin"
		editedContent = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		cG = (Graphics2D) editedContent.createGraphics();
		dG = (Graphics2D) displayedContent.createGraphics();

		// Cr�ation d'une fonction d'enregistrement des touches enfonc�es
		this.addKeyListener(new KeyListener() {

			// Action lors de l'appui
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode()!=524)
				engine.keyboard[key.getKeyCode()] = true;
				engine.Button(0).replaceText(""+key.getKeyCode());
			}

			// Action lors du relachement
			public void keyReleased(KeyEvent key) {
				engine.keyboard[key.getKeyCode()] = false;
				engine.Button(0).replaceText(""+key.getKeyCode());
			}

			// Action lors de l'appui+relachement
			public void keyTyped(KeyEvent key) {

			}
		});
	}

	/*
	 * Actualisation de la zone d'affichage
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(displayedContent, 0, 0, null);

	}

	/*
	 * Validation de l'affichage
	 */
	public void newFrame() {
		dG.drawImage(editedContent.getSubimage(0, 0, engine.displayWidth,
				engine.displayHeight), 0, 0, null);

		blank();
	}

	/*
	 * Ecriture d'une image dans le tampon d'edition
	 */
	public void drawImage(BufferedImage content, int x, int y) {
		cG.drawImage(content, x, y, null);
	}

	/*
	 * Dessin d'une ligne dans le tampon d'edition
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		cG.drawLine(x1, y1, x2, y2);
	}/*
	 * Dessin d'un rectangle dans le tampon d'edition
	 */
	public void drawRect(int x1, int y1, int x2, int y2) {
		cG.drawRect(x1, y1, x2, y2);
	}

	/*
	 * Dessin d'une croix dans le tampon d'edition
	 */
	public void drawCross(int x, int y, int scale) {
		cG.drawLine(x + scale, y, x - scale, y);
		cG.drawLine(x, y + scale, x, y - scale);
	}

	/*
	 * Dessin d'un sprite dans le tampon d'edition
	 */
	public void drawSprite(int x, int y, Sprite sprite, int frame) {
		int width = sprite.getWidth();
		int height = sprite.getHeight();
		BufferedImage tmp = (sprite.get(frame));
		cG.drawImage(tmp, x - width / 2, y - height / 2, null);
	}

	/*
	 * Modification de la couleur du pinceau
	 */
	public void setColor(Color color) {
		cG.setColor(color);
	}

	/*
	 * R�cuperation de la couleur d'un pixel dans le tampon d'affichage
	 */
	public Color getPixel(int x, int y) {
		return new Color(displayedContent.getRGB(x, y));
	}

	/*
	 * Ecriture d'un pixel dans le tampon d'�dition
	 */
	public void setPixel(int x, int y, Color color) {
		editedContent.setRGB(x, y, color.getRGB());
	}

	/*
	 * D�finition de la couleur de nettoyage
	 */
	public void setBlankColor(Color color) {
		backGroundColor = color;
	}

	/*
	 * Nettoyage du tampon d'�dition
	 */
	public void blank() {
		cG.setColor(backGroundColor);
		cG.fillRect(0, 0, width, height);
	}
}
