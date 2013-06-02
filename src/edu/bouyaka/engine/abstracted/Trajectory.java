package edu.bouyaka.engine.abstracted;

import java.awt.Color;

import edu.bouyaka.engine.Abstract;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.Concrete;

public class Trajectory extends Abstract {
	private Vector[] Points;
	private boolean[] checked;
	private int currentPoint;
	private boolean loop;
	private double speed = 1;

	/**
	 * Permet de definir les positions à parcourir
	 * 
	 * @param v
	 *            : positions
	 */
	public void define(Vector[] v) {
		Points = new Vector[v.length];
		for (int id = 0; id < v.length; id++)
			Points[id] = v[id];
		checked = new boolean[v.length];
	}

	/**
	 * Permet de definir une position unique à atteindre
	 * 
	 * @param v
	 *            : position a atteindre
	 */
	public void define(Vector v) {
		Vector[] tmp = new Vector[1];
		tmp[0] = v;
		define(tmp);

	}

	/**
	 * Permet de guider une entite
	 * 
	 * @param E
	 *            : entite a guider
	 */
	public void guide(Concrete E) {

		if (currentPoint < Points.length
				&& Points[currentPoint].getRDistanceTo(E.getPos()) <= (E
						.getHeight() + E.getWidth()) / 4) {
			checked[currentPoint] = true;
			currentPoint++;

		}
		if (currentPoint >= Points.length) {
			if (!loop) {
				return;
			} else {
				reset();
			}
		}

		if (engine.devMode) {
			for (int id = 0; id < Points.length - 1; id++) {
				engine.display.setColor(Color.RED);
				engine.display.drawLine((int) Points[id].getRX(),
						(int) Points[id].getRY(), (int) Points[id + 1].getRX(),
						(int) Points[id + 1].getRY());
				engine.display.setColor(Color.WHITE);
				engine.display.drawCross((int) Points[id].getRX(),
						(int) Points[id].getRY(), 4);

			}
			engine.display.setColor(Color.RED);
			engine.display.drawLine((int) Points[Points.length - 1].getRX(),
					(int) Points[Points.length - 1].getRY(),
					(int) Points[0].getRX(), (int) Points[0].getRY());
			engine.display.setColor(Color.WHITE);
			engine.display.drawCross((int) Points[Points.length - 1].getRX(),
					(int) Points[Points.length - 1].getRY(), 4);
			engine.display.drawCross((int) Points[0].getRX(),
					(int) Points[0].getRY(), 4);
		}

		Vector closest = Points[currentPoint];
		double closestD = closest.getDistanceTo(E.getPos());
		for (int i = currentPoint; i < Points.length; i++) {
			if (Points[i].getDistanceTo(E.getPos()) < closestD
					&& checked[i - 1]) {
				closest = Points[i];
				closestD = closest.getDistanceTo(E.getPos());
			}
		}
		E.moveTo(closest, speed);

	}

	/**
	 * Permet de suivre la trajectoire en boucle
	 */
	public void loop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * Permet de remettre la trajectoire a zero
	 */
	public void reset() {
		currentPoint = 0;
		for (int i = 0; i < checked.length; i++)
			checked[i] = false;
	}

	/**
	 * Permet de connaitre la vitesse de la trajectoire
	 * @return la vitesse
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Permet de definir la vitesse de la trajectoire
	 * @param speed: vitesse à definir
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Permet de savoir si la trajectoire est terminee
	 * @return
	 */
	public boolean hasEnded() {
		if (checked[checked.length - 1]) {
			return true;
		}
		return false;
	}
}