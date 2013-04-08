package edu.bouyaka.engine;

public class Timer {
	private long timeKeyA, timeKeyB;
	private int timeAmount;

	public double delta() {
		return System.nanoTime() - timeKeyA;
	}

	public void newTimeKey() {
		timeKeyA = System.nanoTime();
	}

	public void setDelay(int timeAmount) {
		this.timeAmount = timeAmount;
		timeKeyB = timeAmount + System.nanoTime();
	}

	public void start() {
		timeKeyB = timeAmount + System.nanoTime();
	}

	public void restart() {
		start();
	}

	public boolean ended() {
		if (System.nanoTime() >= timeKeyB)
			return true;
		return false;
	}
}
