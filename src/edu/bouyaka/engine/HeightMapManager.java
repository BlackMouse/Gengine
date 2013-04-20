package edu.bouyaka.engine;

public class HeightMapManager {
	private int[][] heightMap;
	private int[][] showMap;
	private int entityN, entityPerL;
	boolean changed = true;

	public HeightMapManager(int entityPerL, int heightL) {
		this.entityN = entityPerL * heightL;

		heightMap = new int[entityN][2];
		showMap = new int[entityN][2];
		for (int id = 0; id < entityN; id++) {
			heightMap[id][0] = -1;
			heightMap[id][1] = -1;
			showMap[id][0] = -1;
			showMap[id][1] = -1;
		}
	}

	public int[][] getShowOrder() {
		if (changed) {
			for (int h = 0; h < entityN; h++) {
				showMap[h][0] = heightMap[entityN - h - 1][0];
				showMap[h][1] = heightMap[entityN - h - 1][1];
			}
			changed = false;
		}
		return showMap;

	}

	public int[][] getHeightMap() {
		return heightMap;

	}

	public void setHeight(int height, String Etype, int Eid) {
		changed = true;
		int targetHeight = height * entityPerL;
		for (int h = entityN - 1; h > targetHeight; h--) {
			heightMap[h][0] = heightMap[h - 1][0];
			heightMap[h][1] = heightMap[h - 1][1];
		}
		int type = -1;
		if (Etype == "Player")
			type = 0;
		else if (Etype == "Npc")
			type = 1;
		else if (Etype == "Item")
			type = 2;
		else if (Etype == "Interface")
			type = 3;
		else if (Etype == "Button")
			type = 4;
		for (int h = 0; h < targetHeight; h++) {
			if (heightMap[h][0] == type && heightMap[h][1] == Eid) {
				heightMap[targetHeight][0] = -1;
				heightMap[targetHeight][1] = -1;
			}
		}
		System.out.println(type + ":" + Eid);
		heightMap[targetHeight][0] = type;
		heightMap[targetHeight][1] = Eid;
	}
}
