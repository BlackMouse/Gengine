package edu.bouyaka.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard extends Entity implements KeyListener {

	public boolean[] keyMap = new boolean[256];

	// Action lors de l'appui
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() != 524)
			keyMap[key.getKeyCode()] = true;
		if (engine.devMode)
			System.out.println("key N." + key.getKeyCode() + "pressed");
	}

	// Action lors du relachement
	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() != 524)
			keyMap[key.getKeyCode()] = false;
	}

	// Action lors de l'appui+relachement
	public void keyTyped(KeyEvent key) {
		if (key.getKeyCode() != 524)
			engine.lastInput = key.getKeyChar();
	}

	public boolean keyP(int id) {
		return keyMap[id];
	}

}
