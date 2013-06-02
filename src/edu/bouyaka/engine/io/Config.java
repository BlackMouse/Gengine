package edu.bouyaka.engine.io;

import java.util.*;
import java.io.*;

import edu.bouyaka.engine.Abstract;

public class Config extends Abstract {
	protected FileInputStream location;
	protected String fileName;
	protected Properties p = new Properties();

	public Config(String location) {
		fileName = location;
		
		try {
			this.location = new FileInputStream(engine.resDir + fileName);
			System.out
					.println(engine.resDir.getAbsolutePath() + fileName + " Loaded!");
			
		} catch (Exception e) {
			engine.resDir = new File(System.getProperty("user.dir") + "/build/res/","/");
			
			try {
				this.location = new FileInputStream(engine.resDir + fileName);
				System.out.println(engine.resDir.getAbsolutePath() + fileName
						+ " Loaded!");
				
			} catch (FileNotFoundException e1) {
				System.out.println("Le fichier de configuration: " + fileName
						+ " N'a pas pu etre charge");
			}

		}

	}

	/**
	 * Lecteur d'un champs dans le fichier de configuration
	 * 
	 * @param property
	 *            : Nom du champs
	 */
	public String read(String property) {
		try {
			p.load(location);

			return p.getProperty(property);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "";
	}
}
