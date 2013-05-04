package edu.bouyaka.engine.abstracted;

import edu.bouyaka.engine.Abstract;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.Concrete;

public class Trajectory extends Abstract {
	private Vector[] Points;
	private boolean[] checked;
	private int currentPoint;
	private boolean loop;

	public void define(Vector[] v) {
		Points = v.clone();
		checked = new boolean[v.length];
	}

	public void guide(Concrete E) {
		if (Points[currentPoint].getDistanceTo(E.pos) < 4/engine.vRef.getDistanceTo(new Vector())) {
			checked[currentPoint] = true;
			currentPoint++;

		}
		if (currentPoint == Points.length) {
			if (!loop) {
				E.stopFollowingTrajectory();
				return;
			} else {
				currentPoint = 0;
				for (int i = 0; i < checked.length; i++)
					checked[i] = false;
			}
		}
		Vector closest = Points[currentPoint];
		double closestD = closest.getDistanceTo(E.pos);
		for (int i = currentPoint; i < Points.length; i++) {
			if (Points[i].getDistanceTo(E.pos) < closestD && checked[i - 1]) {
				closest = Points[i];
				closestD = closest.getDistanceTo(E.pos);
			}
		}
		E.moveTo(closest);
	}

	public void loop(boolean loop) {
		this.loop = loop;
	}
}