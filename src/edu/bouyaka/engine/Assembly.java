package edu.bouyaka.engine;

public class Assembly extends Item {
	protected Item[] items;
	protected int[][] itemsPos;

	public Assembly(int n) {
		items = new Item[n];
		itemsPos = new int[n][];

	}

	public void show() {
		for (int id = 0; id < items.length; id++) {
			items[id].setPos((int) objectPos[0] + itemsPos[id][0],
					(int) objectPos[1] + itemsPos[id][1]);

			items[id].show();
		}
	}

	

}
