package edu.bouyaka.engine;

public abstract class Entity {
	protected static Gengine engine;
	protected boolean enabled, visible;
	protected String type = "Entity";
	protected double[] prop = new double[5];

	public void update() {
	}

	public void show() {
	}

	public void checkMouse() {
	}

	public String getType() {
		return type;
	}

	public void enable(boolean state) {
		enabled = state;
	}

	public void setVisible(boolean state) {
		visible = state;
	}

	public void setHeightLevel(int h) {
		engine.heightManager.setHeight(h, this);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isVisible() {
		return visible;
	}

	public double prop(int id) {
		if (id >= prop.length || id < 0)
			return -1;
		else
			return prop[id];
	}

	public void setProp(int id, double prop) {
		if (id >= this.prop.length || id < 0)
			return;
		else
			this.prop[id] = prop;
	}
}
