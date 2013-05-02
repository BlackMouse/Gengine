package edu.bouyaka.engine;

public class UpdateScreen /* extends Thread */{
	private Gengine engine;
	private Timer refreshTimer;
	private boolean processed = true;
	int[][] showOrder;
	private Timer oneSecTimer;

	public UpdateScreen(Gengine engine) {
		this.engine = engine;
		refreshTimer = new Timer();
		refreshTimer.setDelay((int) (1.0E9 / 60));
		oneSecTimer = new Timer();
		oneSecTimer.setDelay((int) 1.0E9);
	}

	/*
	 * Actualisation de l'affichage
	 */
	public void run() {
		processed = false;
		// Verification de la dur�e entre deux affichages
		//if (refreshTimer.ended()) {
			engine.display.newFrame();
			engine.display.repaint();

			Entity[] showOrder = engine.heightManager.getShowOrder();
			for (int h = 0; h < showOrder.length; h++) {
				if (showOrder[h] != null)
					if (showOrder[h].isVisible() && showOrder[h].isEnabled()) {
						showOrder[h].show();
					}
			}
			double delta = refreshTimer.delta();
			engine.fps = 1.0E9 / delta;
			if (oneSecTimer.ended()) {
				oneSecTimer.restart();
				engine.shownFps = String.valueOf((int) engine.fps);
			}
			refreshTimer.newTimeKey();
			refreshTimer.restart();

			processed = true;
		//}

	}

	public boolean isProcessed() {
		return processed;
	}
}
