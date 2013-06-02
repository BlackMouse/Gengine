package edu.bouyaka.engine;

public abstract class Entity {
	protected static Gengine engine;
	protected boolean enabled, visible;
	protected String type = "Entity";
	protected String[] param = new String[5];
	protected int heightLevel;
	public boolean concreted;

	/**
	 * Permet d'actualiser l'entite
	 */
	public void update() {
	}

	/**
	 * Permet d'afficher l'entite
	 */
	public void show() {
	}

	/**
	 * Permet de connaitre le type de l'entite
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Permet d'activer ou de desactiver l'entite
	 */
	public void enable(boolean state) {
		enabled = state;
	}

	/**
	 * Permet de rendre visible ou invisible l'entite
	 */
	public void setVisible(boolean state) {
		visible = state;
		if (state) {
			engine.heightManager.setHeight(heightLevel, this);
		} else {
			engine.heightManager.removeEntity(this);
		}
	}

	/**
	 * Permet de suprimmer completement l'entite
	 */
	public void remove() {
		engine.removeEntity(this);
		setVisible(false);
		enabled = false;
	}

	/**
	 * Permet de definir la hauteur de l'entite
	 * 
	 * @param h
	 *            : hauteur de 0 a 9
	 */
	public void setHeightLevel(int h) {
		heightLevel = h;
		engine.heightManager.setHeight(h, this);
	}

	/**
	 * Permet de verifier si l'entite est active
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Permet de verifier si l'entite est visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Permet de recuperer la valeur d'un parametre de l'entite
	 * 
	 * @param paramId
	 *            : identifiant du parametre
	 */
	public String param(int paramId) {
		if (paramId >= param.length || paramId < 0)
			return null;
		else
			return param[paramId];
	}

	/**
	 * Permet de definir la valeur d'un parametre
	 * 
	 * @param paramId
	 *            : identifiant du parametre
	 * @param param
	 *            : valeur du parametre
	 */
	public void setParam(int paramId, String param) {
		if (paramId >= this.param.length || paramId < 0)
			return;
		else
			this.param[paramId] = param;
	}
}
