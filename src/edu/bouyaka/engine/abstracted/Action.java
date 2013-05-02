package edu.bouyaka.engine.abstracted;

import edu.bouyaka.engine.Abstract;
import edu.bouyaka.engine.Entity;

public class Action extends Abstract {
	/*
	 * Modifier types: 0:set 1=add 2=substract 3=multiply 4=divide
	 */
	private int[] modifier = new int[prop.length];

	public void trigger(Entity E) {
		for (int id = 0; id < prop.length; id++)
			affectProp(id, E);
	}

	public void affectProp(int id, Entity E) {
		if (modifier[id] == 0)
			E.setProp(id, prop[id]);
		if (modifier[id] == 1)
			E.setProp(id, E.prop(id) + prop[id]);
		if (modifier[id] == 2)
			E.setProp(id, E.prop(id) - prop[id]);
		if (modifier[id] == 3)
			E.setProp(id, E.prop(id) * prop[id]);
		if (modifier[id] == 4)
			E.setProp(id, E.prop(id) / prop[id]);

	}
}
