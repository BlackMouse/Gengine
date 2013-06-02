package edu.bouyaka.engine.action;

import edu.bouyaka.engine.Action;

public class InterpretConsole extends Action {
	public void run() {
		if (engine.keyboard.keyP(127)) {
			engine.selectedEntity = engine.console;
			engine.devMode = !engine.devMode;
		}
		if (engine.console.getConfirmedText().endsWith("exit")&&engine.console.getConfirmedText().length()==4) {
			engine.state = "Stop";
		}

	}

}
