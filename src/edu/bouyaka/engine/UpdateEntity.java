package edu.bouyaka.engine;

import java.awt.Color;

import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.concreted.Button;
import edu.bouyaka.engine.concreted.TextBox;

public class UpdateEntity /* extends Thread */{
	private Gengine engine;
	private boolean processed = true;
	private Entity[] heightMap;

	public UpdateEntity(Gengine engine) {
		this.engine = engine;
	}

	/**
	 * Permet de lancer l'actualisation des entites
	 */
	public void run() {
		processed = false;

		// Mouse actions check
		heightMap = engine.heightManager.getShowOrder();
		try {
			((TextBox) engine.selectedEntity).isEnabled();
			engine.typingText = true;
		} catch (Exception e) {

			engine.typingText = false;
		}
		updateEntitys();
		checkMouseClick();
		checkMousePress();
		checkMouseHoover();
		engine.mouse.resetClick();

		if (engine.devMode) {
			if (engine.selectedEntity != null) {
				Vector Pos = ((Concrete) engine.selectedEntity).pos;
				engine.display.drawCross((int) Pos.getRX(), (int) Pos.getRY(),
						2);
			}
			if (engine.hooveredEntity != null) {
				Vector Pos = ((Concrete) engine.hooveredEntity).pos;
				int width = ((Concrete) engine.hooveredEntity).getWidth();
				int height = ((Concrete) engine.hooveredEntity).getHeight();
				engine.display.setColor(new Color(0, 255, 0, 64));
				engine.display.fillRect((int) Pos.getRX() - width / 2,
						(int) Pos.getRY() - height / 2, width, height);
			}

		}

		processed = true;

	}

	/**
	 * Permet d'executer la methode update et de faire collisionner les entites
	 */
	private void updateEntitys() {
		// Concrete update and collision
		if (engine.state != "Pause") {
			for (int typeA = 0; typeA < 3; typeA++) {
				for (int entityA = 0; entityA < engine.entityArray[typeA].length; entityA++) {

					// update
					if (engine.entityArray[typeA][entityA] != null
							&& engine.entityArray[typeA][entityA].isEnabled()) {
						engine.entityArray[typeA][entityA].update();
					}
					// collision
					if (engine.entityArray[typeA][entityA] != null
							&& ((Concrete) engine.entityArray[typeA][entityA])
									.isCollisionEnabled()) {
						for (int typeB = 0; typeB < 3; typeB++) {
							for (int entityB = entityA; entityB < engine.entityArray[typeB].length; entityB++) {
								if (engine.entityArray[typeB][entityB] != null) {
									if (((Concrete) engine.entityArray[typeB][entityB])
											.isCollisionEnabled()) {
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

		// Abstract update
		for (int type = 3; type < 5; type++) {
			for (int entity = 0; entity < engine.entityArray[type].length; entity++) {
				if (engine.entityArray[type][entity] != null)
					if (engine.entityArray[type][entity].isEnabled()) {
						engine.entityArray[type][entity].update();
					}

			}
		}
	}

	/**
	 * Permet de verifier les clicks de souris et de selectionner les entites
	 */
	private void checkMouseClick() {
		if (engine.mouse.isClicked()) {
			int entityClicked = 0;
			for (int h = heightMap.length - 1; h >= 0 && entityClicked != 1; h--) {
				if (heightMap[h] != null)
					if (heightMap[h].isVisible() && heightMap[h].isEnabled()) {
						try {
							Button[] tmp = ((Interface) heightMap[h])
									.getButtons();
							for (int id = tmp.length - 1; id >= 0
									&& entityClicked != 1; id--) {
								try {
									if (tmp[id].isEnabled()
											&& tmp[id].isVisible()
											&& tmp[id].isClickable()) {
										entityClicked = ((Concrete) tmp[id])
												.checkMouseClick();
										if (entityClicked == 1) {

											engine.selectedEntity = tmp[id];
											return;
										}

									}
								} catch (Exception e) {
								}

							}

						} catch (Exception e) {
							try {
								if (((Concrete) heightMap[h]).isClickable()) {
									entityClicked = ((Concrete) heightMap[h])
											.checkMouseClick();
								}
							} catch (Exception e1) {
							}
						}
						if (entityClicked == 1) {
							engine.selectedEntity = heightMap[h];
							return;
						}
					}

			}/*
			 * if (entityClicked == 0) { engine.selectedEntity = null; }
			 */
		}
	}

	/**
	 * Permet de verifier l'appui de la souris sur les entites
	 */
	private void checkMousePress() {
		if (engine.mouse.isPressed()) {
			int entityPressed = 0;
			for (int h = heightMap.length - 1; h >= 0 && entityPressed == 0; h--) {
				if (heightMap[h] != null)
					if (heightMap[h].isVisible() && heightMap[h].isEnabled()) {
						try {
							Button[] tmp = ((Interface) heightMap[h])
									.getButtons();
							for (int id = tmp.length - 1; id >= 0
									&& entityPressed != 1; id--) {
								try {
									if (tmp[id].isEnabled()
											&& tmp[id].isVisible()) {
										entityPressed = ((Concrete) tmp[id])
												.checkMousePress();
										if (entityPressed == 0) {
											engine.pressedEntity = tmp[id];
											return;
										}
									}
								} catch (Exception e) {
								}
							}

						} catch (Exception e) {
							try {
								entityPressed = ((Concrete) heightMap[h])
										.checkMousePress();
								if (entityPressed == 0) {
									engine.pressedEntity = heightMap[h];
									return;
								}
							} catch (Exception e1) {
							}
						}

					}

			}
		}
	}

	/**
	 * Permet de verifier le survolement des entites
	 */
	private void checkMouseHoover() {
		boolean entityHoovered = false;

		// Concrete check
		for (int h = heightMap.length - 1; h >= 0 && !entityHoovered; h--) {
			if (heightMap[h] != null) {
				if (heightMap[h].isVisible()) {
					try {
						Button[] tmp = ((Interface) heightMap[h]).getButtons();

						for (int id = tmp.length - 1; id >= 0
								&& !entityHoovered; id--) {
							try {
								if (tmp[id].isEnabled() && tmp[id].isVisible()) {
									entityHoovered = ((Concrete) tmp[id])
											.checkMouseHoover();
									if (entityHoovered) {
										engine.hooveredEntity = tmp[id];
										return;
									}
								}
							} catch (Exception e) {
							}

						}
					} catch (Exception e) {
						try {
							entityHoovered = ((Concrete) heightMap[h])
									.checkMouseHoover();
							engine.hooveredEntity = heightMap[h];
							if (entityHoovered) {
								engine.hooveredEntity = heightMap[h];
								return;
							}
						} catch (Exception e1) {
						}
					}
				}
			}

		}
		if (!entityHoovered)
			engine.hooveredEntity = null;
	}

	/**
	 * Permet de savoir si l'actualisation est termine
	 */
	public boolean isProcessed() {
		return processed;
	}
}
