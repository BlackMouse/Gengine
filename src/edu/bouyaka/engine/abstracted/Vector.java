package edu.bouyaka.engine.abstracted;

import edu.bouyaka.engine.Abstract;

public class Vector extends Abstract {
	Vector attachedVector;
	double x = 0, y = 0, lastUpdate;

	public void setX(double X) {
		x = X;
	}

	public void setY(double Y) {
		y = Y;
	}

	public void set(double X, double Y) {
		x = X;
		y = Y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDistanceTo(Vector v) {
		return Math.sqrt(Math.pow(v.getX()-getX(),2)+Math.pow(v.getY()-getY(),2));
	}

	public double getRX() {
		return x * engine.vRef.getX();
	}

	public double getRY() {
		return y * engine.vRef.getY();
	}

	public void setRX(double X) {
		x = X / engine.vRef.getX();
	}

	public void setRY(double Y) {
		y = Y / engine.vRef.getY();
	}

	public void setR(double X, double Y) {
		x = X / engine.vRef.getX();
		y = Y / engine.vRef.getY();
	}

	public void attachVector(Vector attachedVector) {
		this.attachedVector = attachedVector;
	}

	public void update() {
		if (attachedVector != null)
			applyVector(attachedVector, lastUpdate - engine.tickTime);
		lastUpdate = engine.tickTime;
	}

	public void applyVector(Vector v, double k) {
		x = v.getX() * k;
		y = v.getY() * k;
	}

}
