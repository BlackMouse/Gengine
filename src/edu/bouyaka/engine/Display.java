package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import edu.bouyaka.engine.media.Sprite;

@SuppressWarnings("serial")
public class Display extends JPanel {
	protected int width, height;
	protected BufferedImage displayedContent, editedContent, lightMap;
	protected Graphics2D cG, dG;
	private Gengine engine;

	/**
	 * 
	 * Definition des principales proprietes de la zone d'affichage
	 * 
	 * @param width
	 *            : largeur de l'affichage
	 * @param height
	 *            : hauteur de l'affichage
	 * @param engine
	 *            : moteur calculant le contenu de l'affichage
	 */
	public Display(int width, int height, final Gengine engine) {
		this.width = width;
		this.height = height;
		this.engine = engine;
		// Creation d'une tampon d'affichage "dessin"
		displayedContent = engine.screenConfig.createCompatibleImage(width,
				height, Transparency.OPAQUE);
		// Creation d'une tampon de "dessin"
		editedContent = engine.screenConfig.createCompatibleImage(width,
				height, Transparency.OPAQUE);
		// Creation d'une tampon servant de support pour l'eclairage
		lightMap = engine.screenConfig.createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
		cG = (Graphics2D) editedContent.createGraphics();
		dG = (Graphics2D) displayedContent.createGraphics();

		// Creation d'une fonction d'enregistrement des touches enfoncees
		this.addKeyListener(engine.keyboard);
		// Creation d'une fonction d'enregistrement des touches enfoncees
		this.addMouseListener(engine.mouse);
		// Creation d'une fonction d'enregistrement des touches enfoncees
		this.addMouseMotionListener(engine.mouse);
	}

	/**
	 * Actualisation de la zone d'affichage
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(displayedContent, 0, 0, null);

	}

	/**
	 * Valide de l'affichage
	 */
	public void newFrame() {
		dG.drawImage(editedContent.getSubimage(0, 0, engine.displayWidth,
				engine.displayHeight), 0, 0, null);
		blank(editedContent);
	}

	/**
	 * Permet d'ecrire une image dans le tampon d'edition
	 * 
	 * @param content
	 *            : Image a ecrire
	 * @param x
	 *            : position en x non centree de l'image
	 * @param y
	 *            : position en y non centree de l'image
	 */
	public void drawImage(BufferedImage content, int x, int y) {
		cG.drawImage(content, x, y, null);
	}

	/**
	 * Dessin d'une ligne reliant deux points sur l'ecran
	 * 
	 * @param x1
	 *            : position en x du premier point
	 * @param y1
	 *            : position en y du premier point
	 * @param x2
	 *            : position en x du second point
	 * @param y2
	 *            : position en y du second point
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		cG.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Dessin d'un rectangle creux sur l'ecran
	 * 
	 * @param x1
	 *            : position en x du premier sommet
	 * @param y1
	 *            : position en y du premier sommet
	 * @param x2
	 *            : position en x du second sommet
	 * @param y2
	 *            : position en y du second sommet
	 */

	public void drawRect(int x1, int y1, int x2, int y2) {
		cG.drawRect(x1, y1, x2, y2);
	}

	/**
	 * Dessin d'un rectangle plein sur l'ecran
	 * 
	 * @param x1
	 *            : position en x du premier sommet
	 * @param y1
	 *            : position en y du premier sommet
	 * @param x2
	 *            : position en x du second sommet
	 * @param y2
	 *            : position en y du second sommet
	 */
	public void fillRect(int x1, int y1, int x2, int y2) {
		cG.fillRect(x1, y1, x2, y2);
	}

	/**
	 * Dessin d'une croix sur l'ecran
	 * 
	 * @param x
	 *            : position en x de la croix
	 * @param y
	 *            : position en y de la croix
	 * @param scale
	 *            : taille de la croix
	 * 
	 */
	public void drawCross(int x, int y, int scale) {
		cG.drawLine(x + scale, y, x - scale, y);
		cG.drawLine(x, y + scale, x, y - scale);
	}

	/**
	 * Dessin d'un sprite dans le tampon d'edition
	 * 
	 * @param x
	 *            : position en x centree du sprite
	 * @param y
	 *            : position en y centree du sprite
	 * @param sprite
	 *            : sprite a dessiner
	 * @param frame
	 *            : portion du sprite a dessiner
	 */
	public void drawSprite(int x, int y, Sprite sprite, int frame) {
		int width = sprite.getWidth();
		int height = sprite.getHeight();
		BufferedImage tmp = sprite.getFrame(frame);
		cG.drawImage(tmp, x - width / 2, y - height / 2, null);
	}

	/**
	 * Modification de la couleur du "pinceau" d'edition
	 * 
	 * @param color
	 *            : nouvelle couleur du pinceau
	 */
	public void setColor(Color color) {
		cG.setColor(color);
	}

	/**
	 * Recuperation de la couleur d'un pixel sur l'ecran
	 * 
	 * @param x
	 *            : position du pixel en x
	 * @param y
	 *            : position du pixel en y
	 * @return La couleur du pixel
	 */
	public Color getPixel(int x, int y) {
		return new Color(displayedContent.getRGB(x, y));
	}

	/**
	 * Ecriture d'un pixel sur l'ecran
	 * 
	 * @param x
	 *            : position du pixel en x
	 * @param y
	 *            : position du pixel en y
	 * @param color
	 *            : la couleur du pixel
	 */
	public void setPixel(int x, int y, Color color) {
		editedContent.setRGB(x, y, color.getRGB());
	}

	/**
	 * Nettoyage d'une image
	 * 
	 * @param img
	 *            : Image a nettoyer
	 */
	public void blank(BufferedImage img) {
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

	}

	/**
	 * Permet de définir une icone pour le curseur
	 * 
	 * @param sprite
	 *            : sprite contenant l'icone
	 */
	public void setCursor(Sprite sprite) {
		java.awt.Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(
				sprite.getFrame(0), new Point(0, 0), "mouse");
		engine.display.setCursor(c);
	}

	public Graphics getG() {
		return cG;
	}

}
