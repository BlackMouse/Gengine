package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.*;

public class RegularC implements Collision {
	boolean processing;

	public RegularC() {
	}

	public void collide(Entity a, Entity b) {
		Concrete A = (Concrete) a;
		Concrete B = (Concrete) b;
		if (A.collisionEnabled && B.collisionEnabled)

			if (Math.abs(A.getX() - B.getX()) < (A.getWidth() + B.getWidth()) / 2
					&& Math.abs(A.getY() - B.getY()) < (A.getHeight() + B
							.getHeight()) / 2) {
				A.collideTo(B);
				B.collideTo(A);

			}
	}
}
