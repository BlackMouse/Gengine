package edu.bouyaka.engine.action;

import edu.bouyaka.engine.Action;
import edu.bouyaka.engine.Interface;
import edu.bouyaka.engine.concreted.Button;
import edu.bouyaka.engine.concreted.TextBox;

public class InitialiseDevInterface extends Action {
	public void run() {
		super.run();
		// Cr�ation d'une interface affichant les informations importantes pour
		// le d�veloppement
		engine.addInterface(0);
		Interface devInterface = new Interface();
		engine.replaceInterface(0, devInterface);
		devInterface.enable(false);
		devInterface.setVisible(false);

		devInterface.setHeightLevel(10);

		// Cr�ation d'un bouton affichant la console
		engine.addButton(0);
		TextBox console = new TextBox();
		engine.console = console;
		engine.replaceButton(0, console);
		console.setFixed(true);
		console.enable(true);
		console.setVisible(true);
		console.getPos().setR(engine.displayWidth / 2,
				engine.displayHeight - engine.displayHeight / 90);
		console.setSize(engine.displayWidth, engine.displayHeight / 45);
		console.fixWidth(true);
		console.replaceText("Console initialis�e");
		devInterface.addButton(console, 0);

		// Cr�ation d'un bouton affichant les fps
		engine.addButton(1);
		Button fpsButton = new Button();
		engine.replaceButton(1, fpsButton);
		fpsButton.enable(true);
		fpsButton.setVisible(true);
		fpsButton.setFixed(true);
		fpsButton.getPos().setR(engine.displayWidth / 90,
				engine.displayHeight / 90);
		fpsButton.setSize(engine.displayWidth / 45, engine.displayHeight / 45);
		fpsButton.centerText(true);
		devInterface.addButton(fpsButton, 1);

	}

}
