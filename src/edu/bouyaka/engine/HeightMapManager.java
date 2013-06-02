package edu.bouyaka.engine;

public class HeightMapManager {
	private Entity[] heightMap;
	private int entityN, entityPerLevel;
	
	public HeightMapManager(int entityPerLevel, int heightL) {
		this.entityN = entityPerLevel * heightL;
		this.entityPerLevel = entityPerLevel;
		heightMap = new Entity[entityN];
		for (int id = 0; id < entityN; id++) {
		}
	}

	
	/**
	 * Permet de recuperer l'ordre d'affichage
	 */
	public Entity[] getShowOrder() {
		return heightMap;
	}

	/**
	 * Permet de verifier le contenu de l'affichage
	 */
	public void checkHeightMap() {
		for (int h = 0; h < heightMap.length; h++) {
			if (heightMap[h] != null)
				System.out.println(h + ":" + heightMap[h].getType());
		}
	}

	/**
	 * Permet de definir la hauteur d'une entite
	 * @param height: hauteur 
	 * @param E: entite a definir
	 */
	public void setHeight(int height, Entity E) {
		if (height == 0)
			return;

		int targetHeight = height * entityPerLevel - 1;
		int shift = targetHeight - 1;
		for (int h = targetHeight - 1; h >= 0 && heightMap[h + 1] != null; h--) {
			shift = h;
		}
		for (int h = shift; h < targetHeight; h++) {
			heightMap[h] = heightMap[h + 1];
		}
		for (int h = 0; h < entityN; h++) {
			if (heightMap[h] == E) {
				heightMap[h] = null;
			}
		}
		heightMap[targetHeight] = E;
	}

	/**
	 * Permet de supprimer une entite du tableau d'affichage
	 * @param E: entite a supprimer
	 */
	public void removeEntity(Entity E) {
		for (int h = 0; h < entityN; h++) {
			if (heightMap[h] == E) {
				heightMap[h] = null;
			}
		}
	}
}
