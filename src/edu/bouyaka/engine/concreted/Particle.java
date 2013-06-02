package edu.bouyaka.engine.concreted;

import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.concreted.Item;

public class Particle extends Item {
	protected double attractSpeed, repulseSpeed;
	protected Vector repulsePos, attractPos;

	public void update() {

		if (attractPos != null) {
			moveTo(attractPos, attractSpeed);
		}
		if (repulsePos != null) {
			repulseFrom(repulsePos, repulseSpeed);
		}
	}

	public void setRepulseFrom(Vector V, double speed) {
		repulsePos = V;
		repulseSpeed = speed;

	}

	public void setAttractTo(Vector V, double speed) {
		attractPos = V;
		attractSpeed = speed;

	}

	public void stopAttracting() {
		attractPos = null;
		attractSpeed = 0.0;
	}

	public void stopRepulsing() {
		repulsePos = null;
		repulseSpeed = 0.0;
	}

}
