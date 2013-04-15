package edu.bouyaka.engine;

public abstract class Entity {
	public static Gengine engine;
	public boolean enabled;
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

}
