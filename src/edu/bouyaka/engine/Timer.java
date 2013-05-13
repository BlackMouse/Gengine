package edu.bouyaka.engine;

public class Timer {
	private long timeKeyA, timeKeyB;
	private long timeAmount;

	public double delta() {
		return System.nanoTime() - timeKeyA;
	}

	public void newTimeKey() {
		timeKeyA = System.nanoTime();
	}

	public void setDelay(long timeAmount) {
		this.timeAmount = timeAmount;
	}

	public void start() {
		timeKeyB = timeAmount + System.nanoTime();
	}

	public void restart() {
		start();
	}

	public boolean ended() {
		if (System.nanoTime() >= timeKeyB) {
			return true;
		}
		return false;
	}
}
