package edu.bouyaka.engine.concreted;

import edu.bouyaka.engine.Timer;

public class TextBox extends Button {
	private boolean typingEnabled;
	private Timer typingDelay;
	private String confirmedText;

	public TextBox() {
		super();
		typingDelay = new Timer();
		typingDelay.setDelay(10000);
		typingDelay.start();

	}

	public void setTypingDelay(long delay) {
		typingDelay.setDelay(delay);
	}

	protected void leftPress(double x, double y) {
		typingEnabled = true;
		System.out.println("click on console");
	}

	protected void middlePress(double x, double y) {
	}

	protected void rightPress(double x, double y) {
	}

	protected void hoover(double x, double y) {
	}

	public String getConfirmedText() {
		return confirmedText;
	}

	public void update() {
		if (typingDelay.ended()) {
			typingDelay.start();
			char ch = engine.lastInput;

			if (engine.keyboard.keyP(10)) {
				typingEnabled = false;
				confirmedText = text;
			} else

			if (typingEnabled
					&& engine.lastInput != '\0'
					&& (Character.isLetter(ch) || Character.isDigit(ch) || Character
							.isSpaceChar(ch))) {
				text = text + engine.lastInput;
				engine.lastInput = '\0';
			} else

			if (engine.keyboard.keyP(8) && text.length() > 1
					&& engine.lastInput != '\0') {
				text = text.substring(0, text.length() - 1);
				engine.lastInput = '\0';
			} else if (engine.keyboard.keyP(8) && engine.lastInput != '\0') {
				text = "";
				engine.lastInput = '\0';
			}
		}

		super.update();
	}
}
