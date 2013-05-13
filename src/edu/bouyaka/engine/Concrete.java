package edu.bouyaka.engine;

import edu.bouyaka.engine.abstracted.Trajectory;
import edu.bouyaka.engine.abstracted.Vector;

public class Concrete extends Entity {
	public Vector pos = new Vector();
	// public Vector spd = new Vector();
	// public Vector acc = new Vector();
	protected Trajectory trajectory;
	protected int[] size = new int[2];
	public final boolean concreted = true;

	public boolean collisionEnabled = true, fixed = false;

	/**
	 * Permet d'effectuer le déplacement par défaut de l'entite
	 * 
	 */
	public void move() {
		if (trajectory != null) {
			trajectory.guide(this);
		}
	}

	/**
	 * Permet deplacer l'entite vers le haut
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveUp(int n) {
	}

	/**
	 * Permet deplacer l'entite vers le bas
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveDown(int n) {
	}

	/**
	 * Permet deplacer l'entite vers la gauche
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveLeft(int n) {
	}

	/**
	 * Permet deplacer l'entite vers la droite
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveRight(int n) {
	}

	/**
	 * Permet deplacer l'entite vers le haut et la droite
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveUpRight(int n) {
	}

	/**
	 * Permet deplacer l'entite vers le bas et la droite
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveDownRight(int n) {
	}

	/**
	 * Permet deplacer l'entite vers le haut et la gauche
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveUpLeft(int n) {
	}

	/**
	 * Permet deplacer l'entite vers le bas et la gauche
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveDownLeft(int n) {
	}

	/**
	 * Permet de recuperer la position de l'entite
	 * 
	 * @return La position de l'entitee
	 */
	public Vector getPos() {
		return pos;
	}

	/**
	 * Permet de definir la position de l'entite
	 * 
	 * @param P
	 *            : position a definir
	 */
	public void setPos(Vector P) {
		pos = P;
	}

	/**
	 * Permet de faire suivre une trajectoire à l'entite
	 * 
	 * @param T
	 *            : Trajectoire voulue
	 */
	public void followTrajectory(Trajectory T) {
		trajectory = T;
	}

	/**
	 * Permet d'arrêter de suivre la trajectoire actuelle
	 */
	public void stopFollowingTrajectory() {
		trajectory = null;
	}

	/**
	 * Permet à l'entitée de se deplacer d'un mouvement vers la position P
	 * 
	 * @param P
	 *            : Position voulue
	 */
	public void moveTo(Vector P) {
		if (Math.abs(P.getX() - pos.getX()) > Math.abs(P.getY() - pos.getY())) {
			if (P.getX() - pos.getX() > 0)
				moveRight(1);
			else
				moveLeft(1);
		} else {
			if (P.getY() - pos.getY() > 0)
				moveDown(1);
			else
				moveUp(1);
		}

	}

	/**
	 * Permet de savoir si l'entite touche un bord de l'ecran
	 * 
	 * @return
	 */
	public boolean isTouchingEdge() {
		if (pos.getRX() + size[0] / 2 > engine.displayWidth
				|| pos.getRX() - size[0] / 2 < 0
				|| pos.getRY() + size[1] / 2 > engine.displayHeight
				|| pos.getRY() - size[1] / 2 < 0)
			return true;
		return false;
	}

	/**
	 * Permet de connaitre la largeur de l'entitee
	 * 
	 * @return La largeur de l'entite
	 */
	public int getWidth() {
		return size[0];
	}

	/**
	 * Permet de connaitre la hauteur de l'entitee
	 * 
	 * @return La hauteur de l'entite
	 */
	public int getHeight() {
		return size[1];
	}

	/**
	 * Permet de définir la largeur de l'entitee
	 * 
	 */
	public void setWidth(int x) {
		size[0] = x;
	}

	/**
	 * Permet de définir la hauteur de l'entitee
	 * 
	 */
	public void setHeight(int y) {
		size[1] = y;
	}

	/**
	 * Permet de tester la collision avec une entitée
	 * 
	 * @param E
	 *            : Entitee a collisionner
	 */
	public void collideTo(Concrete E) {
	}

	/**
	 * Permet de verifier le click de la souris sur l'entite
	 * 
	 * @return Le bouton ayant ete clique
	 */
	public int checkMouseClick() {
		int mX = (int) engine.mouse.getPos().getRX(), mY = (int) engine.mouse
				.getPos().getRY();

		if (Math.abs(mX - pos.getRX()) < size[0] / 2
				&& Math.abs(mY - pos.getRY()) < size[1] / 2) {
			if (engine.mouse.getCButton(1)) {
				leftClick(mX, mY);
				return 1;
			}

			if (engine.mouse.getCButton(3)) {
				rightClick(mX, mY);
				return 3;
			}

			if (engine.mouse.getCButton(2)) {
				middleClick(mX, mY);
				return 2;
			}
		}

		return 0;
	}

	/**
	 * Permet de verifier l'appui de la souris sur l'entite
	 * 
	 * @return Le bouton ayant ete appuye
	 */
	public int checkMousePress() {
		int mX = (int) engine.mouse.getPos().getRX(), mY = (int) engine.mouse
				.getPos().getRY();

		if (Math.abs(mX - pos.getRX()) < size[0] / 2
				&& Math.abs(mY - pos.getRY()) < size[1] / 2) {
			if (engine.mouse.getPButton(1)) {
				leftPress(mX, mY);
				return 1;
			}

			if (engine.mouse.getPButton(3)) {
				rightPress(mX, mY);
				return 2;
			}

			if (engine.mouse.getPButton(2)) {
				middlePress(mX, mY);
				return 3;
			}
		}

		return 0;
	}

	/**
	 * Permet de verifier le survolement de la souris sur l'entite
	 * 
	 * @return Le bouton ayant ete appuye
	 */
	public boolean checkMouseHoover() {
		int mX = (int) engine.mouse.getPos().getRX(), mY = (int) engine.mouse
				.getPos().getRY();

		if (Math.abs(mX - pos.getRX()) < size[0] / 2
				&& Math.abs(mY - pos.getRY()) < size[1] / 2) {
			hoover(mX, mY);
			return true;
		}

		return false;
	}

	/**
	 * Action à effectuer a l'appui gauche
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void leftPress(int x, int y) {
	}

	/**
	 * Action à effectuer a l'appui de la roulette
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void middlePress(int x, int y) {
	}

	/**
	 * Action à effectuer a l'appui droit
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void rightPress(int x, int y) {
	}

	/**
	 * Action à effectuer au click gauche
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void leftClick(int x, int y) {
	}

	/**
	 * Action à effectuer au click de la roulette
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void middleClick(int x, int y) {
	}

	/**
	 * Action à effectuer au click droit
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void rightClick(int x, int y) {
	}

	/**
	 * Action à effectuer au passage de la souris
	 * 
	 * @param x
	 *            : Position en x de la souris (unite: pixel)
	 * @param y
	 *            : Position en y de la souris (unite: pixel)
	 */
	protected void hoover(int x, int y) {
	}
}
