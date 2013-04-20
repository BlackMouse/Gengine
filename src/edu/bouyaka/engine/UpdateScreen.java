package edu.bouyaka.engine;

import java.awt.Color;

public class UpdateScreen extends Thread {
	private Gengine engine;
	private Timer refreshTimer;
	private Timer oneSecTimer;
	private int delta;
	private boolean processed = true;
	int[][] showOrder;

	public UpdateScreen(String name, Gengine engine) {
		super(name);
		this.engine = engine;
		refreshTimer = new Timer();
		refreshTimer.setDelay((int) (1.0E9 / 61));
		oneSecTimer = new Timer();
		oneSecTimer.setDelay((int) 1.0E9);
	}

	/*
	 * Actualisation de l'affichage
	 */
	public void run() {
		processed = false;
		// Verification de la dur�e entre deux affichages
		if (refreshTimer.ended()) {
			if (oneSecTimer.ended()) {
				oneSecTimer.restart();
				engine.shownFps = String.valueOf((int) engine.fps);
			}
			engine.display.newFrame();

			engine.display.setBlankColor(new Color(150, 131, 236,
					(int) (255 / (Math.pow(2, engine.blurAmount)))));
			engine.display.repaint();
			/*
			 * for (int type = 0; type < engine.entityArray.length; type++) {
			 * for (int entity = 0; entity < engine.entityArray[type].length;
			 * entity++) { if (engine.entityArray[type][entity] != null) if
			 * (engine.entityArray[type][entity].isVisible())
			 * engine.entityArray[type][entity].show(); } }
			 */

			int[][] showOrder = engine.heightManager.getShowOrder();
			for (int h = 0; h < showOrder.length; h++) {
				if (showOrder[h][0] != -1 && showOrder[h][1] != -1)
					if (engine.entityArray[showOrder[h][0]][showOrder[h][1]] != null
							&& engine.entityArray[showOrder[h][0]][showOrder[h][1]]
									.isVisible())
						engine.entityArray[showOrder[h][0]][showOrder[h][1]]
								.show();

			}

			delta = (int) refreshTimer.delta();
			engine.fps = 1.0E9 / delta;
			refreshTimer.newTimeKey();
			refreshTimer.restart();

		}
		processed = true;

	}

	public boolean isProcessed() {
		return processed;
	}
}
