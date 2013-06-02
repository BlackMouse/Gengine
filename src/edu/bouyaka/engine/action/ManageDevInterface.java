package edu.bouyaka.engine.action;

import edu.bouyaka.engine.Action;
import edu.bouyaka.engine.Timer;

public class ManageDevInterface extends Action {
	Timer triggerDelay;

	public ManageDevInterface() {
		triggerDelay = new Timer();
		triggerDelay.setDelay((long) (1.0E9 / 2));
	}

	public void run() {
		if (triggerDelay.ended()) {
			triggerDelay.restart();
			if (engine.devMode) {
				engine.Interface(0).enable(true);
				engine.Interface(0).setVisible(true);
			} else {
				engine.Interface(0).enable(false);
				engine.Interface(0).setVisible(false);
			}
			engine.Button(1).replaceText(String.valueOf(engine.shownFps));
		}

	}
}
