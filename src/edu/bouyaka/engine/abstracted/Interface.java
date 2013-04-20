package edu.bouyaka.engine.abstracted;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Abstract;
import edu.bouyaka.engine.concreted.Button;

public class Interface extends Abstract {

	// Tampon représentant l'interface
	private BufferedImage interfaceContent;
	// Création d'une zone éditable grâce à drawInterface2D
	static Graphics drawInterface;

	// Création d'un tableau de 10 boutons
	private Button[] buttonArray = new Button[10];

	// Nombre max de boutons sur l'interface
	private int maxId = -1;

	// Ajout d'un bouton à l'interface
	public void addButton(Button button, int id) {
		buttonArray[id] = button;
		buttonArray[id].setG(drawInterface);
		buttonArray[id].setInterface(this);
		if (id > maxId)
			maxId = id;
	}

	// Récupération de l'interface sous forme d'une image
	public BufferedImage get() {
		return interfaceContent;
	}

	// Actualisation de l'affichage
	public void update() {
		for (int id = 0; id <= maxId; id++) {
			if (buttonArray[id].isVisible())
				buttonArray[id].show();
		}
		engine.display.drawImage(interfaceContent, 0, 0);
		clean();
	}

	// Nettoyage de l'interface
	public void clean() {
		((Graphics2D) drawInterface).setComposite(AlphaComposite
				.getInstance(AlphaComposite.CLEAR));
		drawInterface.fillRect(0, 0, engine.displayWidth, engine.displayWidth);
		((Graphics2D) drawInterface).setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER));

	}

	public Interface() {
		interfaceContent = new BufferedImage(engine.displayWidth,
				engine.displayHeight, BufferedImage.TYPE_INT_ARGB);
		// Création d'un système de modification du tampon d'interface
		drawInterface = interfaceContent.createGraphics();
		clean();

	}

	public void enable(boolean state) {
		for (int id = 0; id <= maxId; id++) {
			buttonArray[id].enable(state);
		}
		enabled = state;
	}

	public void setVisible(boolean state) {
		for (int id = 0; id <= maxId; id++) {
			buttonArray[id].setVisible(state);
		}
		visible = state;
	}

}
