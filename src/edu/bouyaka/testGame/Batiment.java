package edu.bouyaka.testGame;

import edu.bouyaka.engine.Assembly;
import edu.bouyaka.engine.Item;

public class Batiment extends Assembly {
	
	public Batiment(int n) {
		super(n);

		for (int id = 0; id < n; id++) {
			items[id] = new Item();
			itemsPos[id] = new int[2];
		}

		// Géneration de murs
		for (int id = 0; id < 5; id++) {
			itemsPos[id][0] = id * 30;
			itemsPos[id][1] = 0;
			items[id].setSpriteId(5);
		}
		for (int id = 5; id < 11; id++) {
			itemsPos[id][0] = (id - 5) * 30;
			itemsPos[id][1] = 5 * 30;
			items[id].setSpriteId(5);
		}
		for (int id = 11; id < 16; id++) {
			itemsPos[id][0] = 5 * 30;
			itemsPos[id][1] = (id - 11) * 30;
			items[id].setSpriteId(5);
		}
		for (int id = 16; id < 21; id++) {
			itemsPos[id][0] = 0;
			itemsPos[id][1] = (id - 16) * 30;
			items[id].setSpriteId(5);
		}

	}

	public void show() {
		for (int id = 0; id < items.length; id++) {
			items[id].setPos((int) objectPos[0] + itemsPos[id][0],
					(int) objectPos[1] + itemsPos[id][1]);

			items[id].show();
		}
	}

	public void moveDownRight(int n) {
		boolean edgeTouch = false;
		for (int id = 0; id < items.length && !edgeTouch; id++) {
			items[id].moveDownRight(10);
			edgeTouch = items[id].isTouchingEdge();
		}
	}

}
