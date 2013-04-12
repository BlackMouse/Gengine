package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Interface extends Abstract {

	// Tampon représentant l'interface
	private BufferedImage interfaceContent;
	// Création d'une zone éditable grâce à drawInterface2D
	static Graphics drawInterface;

	// Couleur du fond de l'interface
	private Color backGroundColor;

	// Création d'un tableau de 10 boutons
	private Button[] buttonArray = new Button[10];

	// Nombre max de boutons sur l'interface
	private int maxId = -1;

	// Ajout d'un bouton à l'interface
	public void addButton(Button button, int id) {
		buttonArray[id] = button;
		buttonArray[id].setG(drawInterface);
		if (id > maxId)
			maxId = id;
	}


	// Récupération de l'interface sous forme d'une image
	public BufferedImage get() {
		return interfaceContent;
	}

	// Actualisation de l'affichage
	public void update() {
		clean();
		for (int id = 0; id <= maxId; id++) {
			if(buttonArray[id].enabled)
			buttonArray[id].show();
		}
		engine.display.drawImage(interfaceContent, 0, 0);
	}

	// Nettoyage de l'interface
	public void clean() {
		// Couleur du fond
		drawInterface.setColor(backGroundColor);
		drawInterface.fillRect(0, 0, engine.displayWidth, engine.displayHeight);

	}

	public Interface() {
		interfaceContent = new BufferedImage(engine.displayWidth,
				engine.displayHeight, BufferedImage.TYPE_INT_ARGB);
		// Création d'un système de modification du tampon d'interface
		drawInterface = interfaceContent.createGraphics();
		// Définition de la couleur du fond
		backGroundColor = new Color(255, 255, 255, 0);
		clean();

	}
}
