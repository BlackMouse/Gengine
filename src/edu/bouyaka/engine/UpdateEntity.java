package edu.bouyaka.engine;

import edu.bouyaka.engine.interfaces.Collision;
import edu.bouyaka.engine.interfaces.RegularC;

public class UpdateEntity extends Thread {
	private Gengine engine;
	private Collision collision = new RegularC();
	private boolean processed = true;

	public UpdateEntity(Gengine engine) {
		this.engine = engine;
	}

	public void run() {
		processed = false;
		//Concrete update
		if (engine.state != "paused") {
			for (int type = 0; type <=2; type++) {
				for (int entity = 0; entity < engine.entityArray[type].length; entity++) {
					if (engine.entityArray[type][entity] != null)
						if (engine.entityArray[type][entity].isEnabled()) {
							engine.entityArray[type][entity].checkMouse();
							engine.entityArray[type][entity].update();
						}
				}
			}
		}
		

		//Abstract update
			for (int type = 3; type < engine.entityArray.length; type++) {
				for (int entity = 0; entity < engine.entityArray[type].length; entity++) {
					if (engine.entityArray[type][entity] != null)
						if (engine.entityArray[type][entity].isEnabled()) {
							engine.entityArray[type][entity].checkMouse();
							engine.entityArray[type][entity].update();
						}
				
			}
		}
		engine.cursor.resetClick();
		for (int typeA = 0; typeA < 3; typeA++) {
			for (int entityA = 0; entityA < engine.entityArray[typeA].length; entityA++) {
				if (engine.entityArray[typeA][entityA] != null)
					if (engine.entityArray[typeA][entityA].isEnabled())
						for (int typeB = 0; typeB < 3; typeB++) {
							for (int entityB = entityA; entityB < engine.entityArray[typeB].length; entityB++) {
								if (engine.entityArray[typeB][entityB] != null)
									if (engine.entityArray[typeB][entityB].isEnabled()) {
										collision
												.collide(
														engine.entityArray[typeA][entityA],
														engine.entityArray[typeB][entityB]);
									}
							}
						}

			}
		}
		processed = true;

	}

	public boolean isProcessed() {
		return processed;
	}
}
