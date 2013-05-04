package edu.bouyaka.engine.abstracted;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import edu.bouyaka.engine.Abstract;
import edu.bouyaka.engine.concreted.Button;

public class Interface extends Abstract {

	// Tampon repr�sentant l'interface
	private BufferedImage interfaceContent;
	// Cr�ation d'une zone �ditable gr�ce � drawInterface2D
	static Graphics drawInterface;

	// Cr�ation d'un tableau de 10 boutons
	private Button[] buttonArray = new Button[10];

	// Nombre max de boutons sur l'interface
	private int maxId = -1;

	// Ajout d'un bouton � l'interface
	public void addButton(Button button, int id) {
		buttonArray[id] = button;
		buttonArray[id].setInterface(this);
		buttonArray[id].setG(drawInterface);
		if (id > maxId)
			maxId = id;
	}

	// R�cup�ration de l'interface sous forme d'une image
	public BufferedImage get() {
		return interfaceContent;
	}

	// Actualisation de l'affichage
	public void show() {
		for (int id = 0; id <= maxId; id++) {
			if (buttonArray[id] != null)
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
		type = "Interface";
		interfaceContent = new BufferedImage(engine.displayWidth,
				engine.displayHeight, BufferedImage.TYPE_INT_ARGB);
		// Cr�ation d'un syst�me de modification du tampon d'interface
		drawInterface = interfaceContent.createGraphics();
		clean();

	}

	public void enable(boolean state) {
		for (int id = 0; id <= maxId; id++) {
			if (buttonArray[id] != null)
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
