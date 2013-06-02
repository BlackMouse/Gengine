package edu.bouyaka.engine;

import edu.bouyaka.engine.concreted.Button;

public class Interface extends Entity {


	// Cr�ation d'un tableau de 10 boutons
	private Button[] buttonArray = new Button[10];

	// Nombre max de boutons sur l'interface
	private int maxId = -1;

	/**
	 * Permet d'ajout d'un bouton a l'interface
	 * 
	 * @param button
	 *            : bouton a rajouter
	 * @param id
	 *            : ordre d'affichage du bouton
	 */
	public void addButton(Button button, int id) {
		buttonArray[id] = button;
		buttonArray[id].setInterface(this);
		if (id > maxId)
			maxId = id;
	}



	/**
	 * Permet d'actualiser l'affichage de l'interface
	 */
	public void show() {
		for (int id = 0; id <= maxId; id++) {
			if (buttonArray[id] != null)

				if (buttonArray[id].isVisible())
					buttonArray[id].show();
		}
	}



	public Interface() {
		type = "Interface";

	}

	/**
	 * Permet de recuperer les boutons contenus dans l'interface
	 */
	public Button[] getButtons() {
		return buttonArray;
	}

}
