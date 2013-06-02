package edu.bouyaka.engine.concreted;

public class TextBox extends Button {
	private String confirmedText = "";

	public TextBox() {
		clickable = true;
		type = "TextButton";
	}

	public String getConfirmedText() {
		return confirmedText;
	}

	public void update() {
		if (engine.selectedEntity == this) {
			char ch = engine.lastInput;

			if (engine.keyboard.keyP(10)) {
				engine.selectedEntity = null;
				confirmedText = text;
			} else

			if (engine.lastInput != '\0'
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
