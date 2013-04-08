package edu.bouyaka.testGame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.bouyaka.engine.Config;
import edu.bouyaka.engine.Gengine;

public class SpritesConfig extends Config {

	public SpritesConfig(String location, Gengine engine) {
		super(location, engine);
	}

	public void load() {
		// Chargements des sprites
		engine.spriteAmount = Integer.parseInt(read("spriteAmount"));
		engine.setSpriteAmount(engine.spriteAmount);
		for (int n = 0; n < engine.spriteAmount; n++) {
			try {
				engine.addSprite(n, ImageIO.read(new File(engine.resDir
						+ engine.pack + "/", read("Sprite_" + n))),
						Integer.parseInt(read("Sprite_" + n + "NF")),
						Integer.parseInt(read("Sprite_" + n + "FR")));

				engine.Sprite(n).scale(engine.tileWidth, engine.tileHeight,
						"proportionnal");

			} catch (IOException e) {
				System.out
						.println("Erreur dans le chargement du sprite N." + n);
			}
		}
	}

}
