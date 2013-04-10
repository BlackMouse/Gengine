package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Interface extends Abstract {

	// Tampon repr�sentant l'interface
	private BufferedImage interfaceContent;
	// Cr�ation d'une zone �ditable gr�ce � drawInterface2D
	static Graphics drawInterface;

	// Couleur du fond de l'interface
	private Color backGroundColor;

	// Cr�ation d'un tableau de 10 boutons
	private Button[] buttonArray = new Button[10];

	// Nombre max de boutons sur l'interface
	private int maxId = -1;

	// Ajout d'un bouton � l'interface
	public void addButton(Button button, int id) {
		buttonArray[id] = button;
		buttonArray[id].setG(drawInterface);
		if (id > maxId)
			maxId = id;
	}


	// R�cup�ration de l'interface sous forme d'une image
	public BufferedImage get() {
		return interfaceContent;
	}

	// Actualisation de l'affichage
	public void update() {
		clean();
		for (int id = 0; id <= maxId; id++) {
			if(engine.entityEnabled[4][id])
			buttonArray[id].show();
		}
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
		// Cr�ation d'un syst�me de modification du tampon d'interface
		drawInterface = interfaceContent.createGraphics();
		// D�finition de la couleur du fond
		backGroundColor = new Color(255, 255, 255, 0);
		clean();

	}
}
