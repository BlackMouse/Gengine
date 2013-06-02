package edu.bouyaka.engine;

public class UpdateScreen /* extends Thread */{
	private Gengine engine;
	private Timer refreshTimer, oneSecTimer;
	private boolean processed = true;

	public UpdateScreen(Gengine engine) {
		this.engine = engine;
		refreshTimer = new Timer();
		refreshTimer.setDelay((int) (1.0E9/120));

		oneSecTimer = new Timer();
		oneSecTimer.setDelay((int) 5.0E8);
	}

	/**
	 * Permet d'actualiser l'affichage
	 */
	public void run() {
		processed = false;
		// Verification de la dur�e entre deux affichages
		if (refreshTimer.ended()) {
			refreshTimer.restart();

			engine.display.newFrame();
			engine.display.repaint();

			Entity[] showOrder = engine.heightManager.getShowOrder();
			for (int h = 0; h < showOrder.length; h++) {
				if (showOrder[h] != null)
					if (showOrder[h].isVisible()) {
						showOrder[h].show();
					}
			}
			double delta = refreshTimer.delta();
			refreshTimer.newTimeKey();
			engine.fps = 1.0E9 / delta;
			if (oneSecTimer.ended()) {
				oneSecTimer.restart();
				engine.shownFps = String.valueOf((int) engine.fps);
			}
			engine.tick = 15 * delta / (1E8);
			engine.tickTime = engine.tickTime + engine.tick;

		}
		processed = true;

	}

	/**
	 * Permet de verifier si l'affichage est termine
	 */
	public boolean isProcessed() {
		return processed;
	}
}
