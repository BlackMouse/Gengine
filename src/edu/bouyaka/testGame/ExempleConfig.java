package edu.bouyaka.testGame;

import edu.bouyaka.engine.Config;
import edu.bouyaka.engine.Gengine;

public class ExempleConfig extends Config {

	public ExempleConfig(String location, Gengine engine) {
		super(location, engine);
		// TODO Auto-generated constructor stu
	}

	public void load(){
		try {
			//Exemple de récuperation d'entier depuis un fichier de configuration
			int entier = Integer.parseInt(read("entier"));
			//Exemple de récuperation d'un charactère depuis un fichier de configuration
			char charactere = read("caractere").charAt(0);
			//Exemple de récuperation d'un drapeau depuis un fichier de configuration
			boolean drapeau = Boolean.parseBoolean(read("drapeau"));
			//Exemple de récuperation d'un nombre à virgule depuis un fichier de configuration
			double decimal = Double.parseDouble(read("decimal"));
			//Exemple de récuperation d'un entier long depuis un fichier de configuration
			long long_nombre = Long.parseLong(read("long_nombre"));

			System.out.println("Configuration correctement charg�e pour " +fileName);
		} catch (Exception e) {
			System.out.println("Erreur dans le chargement de la configuration pour " +fileName);
		}
	}

}
