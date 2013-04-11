package edu.bouyaka.engine;

public class Vector extends Abstract {
	Vector attachedVector;
	double x = 1, y = 1, lastUpdate;

	public void setX(double X) {
		x = X;
	}

	public void setY(double Y) {
		y = Y;
	}

	public void set(double X, double Y) {
		y = Y;
		x = X;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getRX() {
		return x * engine.vRef.getX();
	}

	public double getRY() {
		return y * engine.vRef.getY();
	}

	public void attachVector(Vector attachedVector) {
		this.attachedVector = attachedVector;
	}

	public void update() {
		if(attachedVector!=null)
		applyVector(attachedVector, lastUpdate - engine.tickTime);
		lastUpdate = engine.tickTime;
	}

	public void applyVector(Vector v, double k) {
		x = v.getX() * k;
		y = v.getY() * k;
	}

}
