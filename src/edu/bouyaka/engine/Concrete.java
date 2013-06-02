package edu.bouyaka.engine;

import java.awt.Color;

import edu.bouyaka.engine.abstracted.Trajectory;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.media.Sound;

public class Concrete extends Entity {
	protected Vector pos = new Vector();
	protected Trajectory trajectory;
	protected int[] size = new int[2];
	public final boolean concreted = true;
	protected double speed = 1;
	protected boolean collisionEnabled = false, fixed = false,
			clickable = false;
	private Sound[] soundArray = new Sound[5];

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
	public void moveUp(double n) {
	}

	/**
	 * Permet deplacer l'entite vers le bas
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveDown(double n) {
	}

	/**
	 * Permet deplacer l'entite vers la gauche
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveLeft(double n) {
	}

	/**
	 * Permet deplacer l'entite vers la droite
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveRight(double n) {
	}

	/**
	 * Permet deplacer l'entite vers le haut et la droite
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveUpRight(double n) {
	}

	/**
	 * Permet deplacer l'entite vers le bas et la droite
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveDownRight(double n) {
	}

	/**
	 * Permet deplacer l'entite vers le haut et la gauche
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveUpLeft(double n) {
	}

	/**
	 * Permet deplacer l'entite vers le bas et la gauche
	 * 
	 * @param n
	 *            : nombre de pas
	 */
	public void moveDownLeft(double n) {
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
	 * Permet de vérifier si l'enité suit toujours la trajectoire
	 */
	public boolean isFollowingTrajectory() {
		if (trajectory == null)
			return false;
		return true;
	}

	/**
	 * Permet à l'entitée de se deplacer vers la position P
	 * 
	 * @param P
	 *            : Position voulue
	 */
	public void moveTo(Vector P, double speed) {

		double eX = pos.getX(), eY = pos.getY();
		double pX = P.getX(), pY = P.getY();
		double xK = Math.abs(Math.abs(eX - pX) / pos.getDistanceTo(P));
		double yK = Math.abs(Math.abs(eY - pY) / pos.getDistanceTo(P));

		if (pX - eX > 0)
			moveRight(speed * xK);
		else if (pX - eX < 0)
			moveLeft(speed * xK);

		if (pY - eY > 0)
			moveDown(speed * yK);
		else if (pY - eY < 0)
			moveUp(speed * yK);
		if (engine.devMode) {
			engine.display.setColor(new Color(255, 128, 128, 255));

			int eRX = (int) pos.getRX(), eRY = (int) pos.getRY();
			int pRX = (int) P.getRX(), pRY = (int) P.getRY();
			engine.display.drawLine(eRX, eRY, pRX, pRY);
			engine.display.setColor(Color.WHITE);
			engine.display.drawCross(pRX, pRY, 4);
		}
	}

	/**
	 * Permet à l'entitée de se deplacer'éloigner de la position P
	 * 
	 * @param P
	 *            : Position voulue
	 */
	public void repulseFrom(Vector P, double speed) {
		moveTo(P, -speed);

	}

	/**
	 * Permet de savoir si l'entite touche un bord de l'ecran
	 * 
	 * @return
	 */
	public boolean isTouchingEdge() {

		int eRX = (int) pos.getRX(), eRY = (int) pos.getRY();
		if (eRX + size[0] / 2 > engine.displayWidth || eRX - size[0] / 2 < 0
				|| eRY + size[1] / 2 > engine.displayHeight
				|| eRY - size[1] / 2 < 0)
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

		repulseFrom(E.getPos(), (getSpeed() + E.getSpeed())
				* (size[0] + size[1] + E.getHeight() + E.getWidth())
				/ (2 * 4 * pos.getRDistanceTo(E.getPos())));

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

	/**
	 * Permet de connaitre la vitesse de l'entité
	 * 
	 * @return la vitesse de l'entitée
	 */
	public double getSpeed() {
		if (trajectory == null)
			return speed;
		return trajectory.getSpeed();
	}

	/**
	 * Permet de définir la vitesse de l'entité
	 * 
	 * @param speed
	 *            : Vitesse a definir
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Permet de vérifier si la collision est activée pour l'entité
	 */
	public boolean isCollisionEnabled() {
		return collisionEnabled;
	}

	/**
	 * Permet d'activer ou de desactiver la collision
	 */
	public void setCollisionEnabled(boolean collisionEnabled) {
		this.collisionEnabled = collisionEnabled;
	}

	/**
	 * Permet de verifier si l'entité est fixé spatialement
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * Permet de fixer l'entité spatialement
	 */
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	/**
	 * Permet de definir les sons associes a l'entite
	 * 
	 * @param id
	 *            : Identifiant du son
	 * @param sound
	 *            : Son
	 */
	public void defineSound(int id, Sound sound) {
		this.soundArray[id] = sound;
	}

	/**
	 * Permet de verifier si l'entite peut etre clique
	 */
	public boolean isClickable() {
		return clickable;
	}

	/**
	 * Permet d'activer le click de l'entite
	 */
	public void setClickable(boolean state) {
		clickable = state;
	}
}
