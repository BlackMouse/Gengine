package edu.bouyaka.engine;

public abstract class Entity {
	public static Gengine engine;
	protected boolean enabled, visible;
	protected String type;

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

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isVisible() {
		return visible;
	}
}
