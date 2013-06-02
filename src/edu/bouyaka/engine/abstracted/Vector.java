package edu.bouyaka.engine.abstracted;

import edu.bouyaka.engine.Abstract;

public class Vector extends Abstract {
	private double x = 0, y = 0;

	/**
	 * Permet de definir la valeur en x relative aux unites du jeu
	 */
	public void setX(double X) {
		x = X;
	}

	/**
	 * Permet de definir la valeur en y relative aux unites du jeu
	 */
	public void setY(double Y) {
		y = Y;
	}

	/**
	 * Permet de definir les valeur en x et en y relatives aux unites du jeu
	 */
	public void set(double X, double Y) {
		x = X;
		y = Y;
	}

	/**
	 * Permet de cloner les valeurs d'un vecteur
	 * 
	 * @param v
	 *            : vecteur a cloner
	 */
	public void set(Vector v) {

		x = v.getX();
		y = v.getY();
	}

	/**
	 * Permet de recuperer la valeur en x relative aux unites du jeu
	 */
	public double getX() {
		return x;
	}

	/**
	 * Permet de recuperer la valeur en y relative aux unites du jeu
	 */
	public double getY() {
		return y;
	}

	/**
	 * Permet de recuperer la distance a un autre vecteur relative aux unites du
	 * jeu
	 */
	public double getDistanceTo(Vector v) {
		return Math.sqrt(Math.pow(v.getX() - x, 2) + Math.pow(v.getY() - y, 2));
	}

	/**
	 * Permet de recuperer la distance a un autre vecteur en pixels
	 */
	public double getRDistanceTo(Vector v) {
		return Math.sqrt(Math.pow(v.getRX() - getRX(), 2)
				+ Math.pow(v.getRY() - getRY(), 2));
	}

	/**
	 * Permet de recuperer la valeur en x en pixel
	 */
	public double getRX() {
		return x * engine.vRef.getX();
	}

	/**
	 * Permet de definir la valeur en x en pixel
	 */
	public void setRX(double X) {
		x = X / engine.vRef.getX();
	}

	/**
	 * Permet de recuperer la valeur en y en pixel
	 */
	public double getRY() {
		return y * engine.vRef.getY();
	}

	/**
	 * Permet de definir la valeur en y en pixel
	 */
	public void setRY(double Y) {
		y = Y / engine.vRef.getY();
	}

	/**
	 * Permet de definir la valeur en x et en y en pixel
	 */
	public void setR(double X, double Y) {
		x = X / engine.vRef.getX();
		y = Y / engine.vRef.getY();
	}

	/**
	 * Permet d'appliquer un vecteur
	 * 
	 * @param v
	 *            : vecteur a appliquer
	 * @param k
	 *            : poids du vecteur a appliquer
	 */
	public void applyVector(Vector v, double k) {
		x = x + v.getX() * k;
		y = y + v.getY() * k;
	}

	/**
	 * Permet de recuperer un clone du vecteur
	 */
	public Vector clone() {
		Vector tmp = new Vector();
		tmp.set(x, y);
		return tmp;

	}

	/**
	 * Permet de recuperer un clone du vecteur avec un decalage aleatoire
	 * 
	 * @param k
	 *            : poids du decalage
	 */
	public Vector randClone(double k) {
		Vector tmp = new Vector();
		tmp.set(x + k * Math.random() - k / 2, y + k * Math.random() - k / 2);
		return tmp;

	}

}
