package edu.bouyaka.engine;

public class Script extends Thread {
	private Action[] action;
	private long[] schedleTime;
	private long startTime;
	private boolean ended;
	private int freq = 120;

	public Script(int nAction) {
		action = new Action[nAction];
		schedleTime = new long[nAction];
	}

	/**
	 * Permet de reinitialiser le script
	 */
	public void reset() {
		ended = false;
		for (int id = 0; id < action.length; id++) {
			if (action[id] != null && !action[id].isRunned()) {
				action[id].runned = false;
			}

		}
	}

	/**
	 * Permet de lancer le script
	 */
	public void run() {
		startTime = System.nanoTime();
		while (!ended) {
			for (int id = 0; id < schedleTime.length; id++) {
				if (System.nanoTime() >= schedleTime[id] + startTime
						&& action[id] != null && !action[id].isRunned()) {
					action[id].run();
				}

			}
			ended = true;
			for (int id = 0; id < action.length; id++) {
				if (action[id] != null && !action[id].isRunned()) {
					ended = false;
				}

			}
			try {
				Thread.sleep(1000 / freq);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Permet de rajouter une action au script
	 * 
	 * @param id
	 *            : identifiant de l'action
	 * @param A
	 *            : Action
	 * @param schedleTime
	 *            : Moment de lancement relatif au lancement du script en ms
	 */
	public void defineAction(int id, Action A, long schedleTime) {
		try {
			action[id] = A;
			this.schedleTime[id] = (long) (schedleTime * 1.0E6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Definition de la frequence d'actualisation du script
	 * 
	 * @param freq
	 *            : frequenc en Hz
	 */
	public void setFreq(int freq) {
		this.freq = freq;
	}

}
