package edu.bouyaka.engine;

public class Timer {
	private long timeKeyA, timeKeyB;
	private long timeAmount;

	/**
	 * Permet de mesurer la différence de temps avec une reference definie
	 * precedement
	 * 
	 * @return la difference de temps
	 */
	public double delta() {
		return System.nanoTime() - timeKeyA;
	}

	/**
	 * Permet de creer une nouvelle reference dans le temps (permet ensuite de
	 * mesurer des differences de temps)
	 * 
	 * @see delta
	 */
	public void newTimeKey() {
		timeKeyA = System.nanoTime();
	}

	/**
	 * Permet de définir un delai pour le compte a rebours
	 * 
	 * @param timeAmount
	 *            : duree du delai en nsec
	 */
	public void setDelay(long timeAmount) {
		this.timeAmount = timeAmount;
	}

	/**
	 * Permet de lancer le compte a rebours
	 */
	public void start() {
		timeKeyB = timeAmount + System.nanoTime();
	}

	/**
	 * Permet de relancer le compte a rebours
	 */
	public void restart() {
		start();
	}

	/**
	 * Permet de verifier si le compte a rebours s'est termine
	 */
	public boolean ended() {
		if (System.nanoTime() >= timeKeyB) {
			return true;
		}
		return false;
	}
}
