package edu.bouyaka.engine;

import edu.bouyaka.engine.abstracted.Vector;

public class UpdateEntity /* extends Thread */{
	private Gengine engine;
	private boolean processed = true;
	private Entity[] heightMap;

	public UpdateEntity(Gengine engine) {
		this.engine = engine;
	}

	public void run() {
		processed = false;

		// Mouse actions check
		heightMap = engine.heightManager.getShowOrder();

		updateEntitys();
		checkMouseClick();
		checkMousePress();
		checkMouseHoover();
		engine.mouse.resetClick();

		if (engine.devMode && engine.selectedEntity != null) {
			Vector Pos = ((Concrete) engine.selectedEntity).pos;
			engine.display.drawCross((int) Pos.getRX(), (int) Pos.getRY(), 2);
		}

		processed = true;

	}

	private void updateEntitys() {
		// Concrete update and collision
		if (engine.state != "Pause") {
			for (int typeA = 0; typeA < 3; typeA++) {
				for (int entityA = 0; entityA < engine.entityArray[typeA].length; entityA++) {
					if (engine.entityArray[typeA][entityA] != null) {

						if (engine.entityArray[typeA][entityA].isEnabled()) {
							// update
							engine.entityArray[typeA][entityA].update();

							// collision
							for (int typeB = 0; typeB < 3; typeB++) {
								for (int entityB = entityA; entityB < engine.entityArray[typeB].length; entityB++) {
									if (engine.entityArray[typeB][entityB] != null) {
										if (engine.entityArray[typeB][entityB]
												.isEnabled()) {
											engine.collision
													.collide(
															engine.entityArray[typeA][entityA],
															engine.entityArray[typeB][entityB]);
										}
									}
								}
							}

						}

					}
				}
			}
		}

		// Abstract update
		for (int type = 3; type < engine.entityArray.length; type++) {
			for (int entity = 0; entity < engine.entityArray[type].length; entity++) {
				if (engine.entityArray[type][entity] != null)
					if (engine.entityArray[type][entity].isEnabled()) {
						engine.entityArray[type][entity].update();
					}

			}
		}
	}

	private void checkMouseClick() {
		if (engine.mouse.isClicked()) {
			int entityClicked = 0;

			// Buttons check
			for (int entity = 0; entity < engine.entityArray[4].length
					&& entityClicked != 1; entity++) {
				if (engine.entityArray[4][entity] != null)

					if (engine.entityArray[4][entity].isEnabled()
							&& engine.entityArray[4][entity].isVisible()) {
						try {
							entityClicked = ((Concrete) engine.entityArray[4][entity])
									.checkMouseClick();
						} catch (Exception e) {
						}

						if (entityClicked == 1)
							engine.selectedEntity = engine.entityArray[4][entity];
					}

			}

			// Concrete check
			for (int h = heightMap.length - 1; h >= 0 && entityClicked != 1; h--) {
				if (heightMap[h] != null)
					if (heightMap[h].isVisible() && heightMap[h].isEnabled()) {
						try {
							entityClicked = ((Concrete) heightMap[h])
									.checkMouseClick();
						} catch (Exception e) {
						}
						if (entityClicked == 1)
							engine.selectedEntity = heightMap[h];
					}

			}
			if (entityClicked == 0) {
				engine.selectedEntity = null;
			}
		}
	}

	private void checkMousePress() {
		if (engine.mouse.isPressed()) {
			int entityPressed = 0;

			// Buttons check
			for (int entity = 0; entity < engine.entityArray[4].length
					&& entityPressed != 1; entity++) {
				if (engine.entityArray[4][entity] != null)
					if (engine.entityArray[4][entity].isEnabled()
							&& engine.entityArray[4][entity].isVisible()) {
						try {
							entityPressed = ((Concrete) engine.entityArray[4][entity])
									.checkMousePress();
						} catch (Exception e) {
						}
					}

			}
			// Concrete check
			for (int h = heightMap.length - 1; h >= 0 && entityPressed == 0; h--) {
				if (heightMap[h] != null)
					if (heightMap[h].isVisible() && heightMap[h].isEnabled()) {
						try {
							entityPressed = ((Concrete) heightMap[h])
									.checkMousePress();
						} catch (Exception e) {
						}
					}

			}
		}
	}

	private void checkMouseHoover() {
		if (!engine.mouse.isPressed() && !engine.mouse.isClicked()) {
			boolean entityHoovered = false;

			// Buttons check
			for (int entity = 0; entity < engine.entityArray[4].length
					&& !entityHoovered; entity++) {
				if (engine.entityArray[4][entity] != null)
					if (engine.entityArray[4][entity].isEnabled()
							&& engine.entityArray[4][entity].isVisible()) {
						try {
							entityHoovered = ((Concrete) engine.entityArray[4][entity])
									.checkMouseHoover();
						} catch (Exception e) {
						}
					}

			}

			// Concrete check
			for (int h = heightMap.length - 1; h >= 0 && !entityHoovered; h--) {
				if (heightMap[h] != null)
					if (heightMap[h].isVisible() && heightMap[h].isEnabled()
							&& heightMap[h].concreted) {
						entityHoovered = ((Concrete) heightMap[h])
								.checkMouseHoover();
					}

			}
		}
	}

	public boolean isProcessed() {
		return processed;
	}
}
