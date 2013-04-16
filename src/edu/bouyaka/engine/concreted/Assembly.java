package edu.bouyaka.engine.concreted;

import edu.bouyaka.engine.abstracted.Vector;

public class Assembly extends Item {
	protected Item[] items;
	protected Vector[] itemsPos;

	public Assembly(int n) {
		items = new Item[n];
		itemsPos = new Vector[n];

	}

	public void show() {
		for (int id = 0; id < items.length; id++) {
			items[id].setPos((int) pos.getX() + itemsPos[id].getX(),
					(int) pos.getY() + itemsPos[id].getY());

			items[id].show();
		}
	}

}
