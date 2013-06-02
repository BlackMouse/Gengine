package edu.bouyaka.engine.interfaces;

import edu.bouyaka.engine.*;

public class RegularC implements Collision {
	boolean processing;

	public void collide(Entity a, Entity b) {
		Concrete A = (Concrete) a;
		Concrete B = (Concrete) b;
		if (A.isCollisionEnabled() && B.isCollisionEnabled())

			if (Math.abs(A.getPos().getRX() - B.getPos().getRX()) < (A.getWidth() + B
					.getWidth()) / 2
					&& Math.abs(A.getPos().getRY() - B.getPos().getRY()) < (A.getHeight() + B
							.getHeight()) / 2) {
				A.collideTo(B);
				B.collideTo(A);

			}
	}

	
	public boolean areColliding(Entity a, Entity b) {
		Concrete A = (Concrete) a;
		Concrete B = (Concrete) b;
		if (A.isCollisionEnabled() && B.isCollisionEnabled())

			if (Math.abs(A.getPos().getRX() - B.getPos().getRX()) < (A.getWidth() + B
					.getWidth()) / 2
					&& Math.abs(A.getPos().getRY() - B.getPos().getRY()) < (A.getHeight() + B
							.getHeight()) / 2) {
				return true;

			}
		return false;
	}
}
