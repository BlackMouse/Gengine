package edu.bouyaka.engine;

public class Vector extends Abstract {
	Vector attachedVector;
	double x, y, lastUpdate;
	Vector vSpeed[];

	void setX(double X) {
		x = X;
	}

	void setY(double Y) {
		y = Y;
	}

	double getX() {
		return x;
	}

	double getY() {
		return y;
	}

	public void attachVector(Vector attachedVector) {
		this.attachedVector = attachedVector;
	}

	public void update() {
		applyVector(attachedVector, lastUpdate - engine.tickTime);
		lastUpdate = engine.tickTime;
	}

	public void applyVector(Vector v, double k) {
		x = v.getX() * k;
		y = v.getY() * k;
	}

}
