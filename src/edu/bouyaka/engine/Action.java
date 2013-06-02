package edu.bouyaka.engine;

public class Action extends Entity {
	public boolean runned;

	/**
	 * Contenu de l'action a executer
	 */
	public void run() {
		runned = true;

	}

	/**
	 * Permet de verifier si l'action est terminée
	 */
	public boolean isRunned() {
		return runned;
	}
}
