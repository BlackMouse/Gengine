package edu.bouyaka.testGame;

import edu.bouyaka.engine.Config;
import edu.bouyaka.engine.Gengine;

public class SysConfig extends Config{

	public SysConfig(String location, Gengine engine) {
		super(location, engine);
	}
	
	public void load(){
		try {
			engine.displayWidth = Integer.parseInt(read("displayWidth"));
			engine.displayHeight = Integer
					.parseInt(read("displayHeight"));
			engine.fullScreen = Boolean.parseBoolean(read("fullScreen"));
			if (engine.fullScreen) {
				engine.displayWidth = engine.screenWidth;
				engine.displayHeight = engine.screenHeight;
			}
			engine.blurAmount = Integer.parseInt(read("blurAmount"));
			engine.npcAmount = Integer.parseInt(read("npcAmount"));
			engine.setNpcAmount(engine.npcAmount);
			engine.pack = read("pack");
			engine.rev = read("rev");
			engine.nHTiles = Integer.parseInt(read("nHTiles"));
			engine.nVTiles = Integer.parseInt(read("nVTiles"));
			engine.tileWidth = (int) engine.displayWidth / engine.nHTiles;
			engine.tileHeight = (int) engine.displayHeight / engine.nVTiles;
			engine.resDir = getResDir();
			System.out.println("Configuration correctement charg�e pour " +fileName);
		} catch (Exception e) {
			System.out.println("Erreur dans le chargement de la configuration pour " +fileName);
		}
	}

}
