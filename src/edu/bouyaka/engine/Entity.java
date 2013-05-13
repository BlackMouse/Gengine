package edu.bouyaka.engine;

public abstract class Entity {
	protected static Gengine engine;
	protected boolean enabled, visible;
	protected String type = "Entity";
	protected String[] param = new String[5];
	public boolean concreted;

	public void update() {
	}

	public void show() {
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

	public String param(int paramId) {
		if (paramId >= param.length || paramId < 0)
			return null;
		else
			return param[paramId];
	}

	public void setParam(int paramId, String param) {
		if (paramId >= this.param.length || paramId < 0)
			return;
		else
			this.param[paramId] = param;
	}
}
