package edu.bouyaka.engine.concreted;

import edu.bouyaka.engine.abstracted.Vector;

public class Assembly extends Item {
	protected Item[] items;
	protected Vector[] itemsPos;

	public Assembly(int n) {
		items = new Item[n];
		itemsPos = new Vector[n];

	}



}
