package edu.bouyaka.engine;

import java.util.*;
import java.io.*;

public class Config {
	private FileInputStream location;
	private File resDir;
	protected String fileName;
	private Properties p = new Properties();
	protected Gengine engine;

	public Config(String location, Gengine engine) {
		fileName = location;
		this.engine=engine;
		resDir = new File(System.getProperty("user.dir") + "/res", "/");
		try {
			this.location = new FileInputStream(resDir + fileName);
			System.out
					.println(resDir.getAbsolutePath() + fileName + " Loaded!");
		} catch (Exception e) {
			resDir = new File(System.getProperty("user.dir") + "/build/res",
					"/");
			try {
				this.location = new FileInputStream(resDir + fileName);
				System.out.println(resDir.getAbsolutePath() + fileName
						+ " Loaded!");
			} catch (FileNotFoundException e1) {
				System.out.println("Le fichier de configuration: " + fileName
						+ "N'a pas pu �tre charg�");
			}

		}

	}

	public File getResDir() {
		return resDir;
	}

	// Lecture du fichier de configuration
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
