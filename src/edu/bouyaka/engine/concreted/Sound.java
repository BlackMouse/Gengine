package edu.bouyaka.engine.concreted;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.bouyaka.engine.Concrete;

public class Sound extends Concrete {
	Clip soundClip = null;
	boolean loop;
	public AudioInputStream audioInputStream;

	public Sound(String fileName) {
		try {
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(
						System.getProperty("user.dir") + "/res/" + fileName));
			} catch (Exception e) {
				try {
					audioInputStream = AudioSystem
							.getAudioInputStream(new File(System
									.getProperty("user.dir")
									+ "/build/res/"
									+ fileName));
				} catch (FileNotFoundException e1) {
					System.out.println("Erreur dans le chargement du son: "
							+ "res/" + fileName + " N'a pas pu �tre charg�");
				}

			}

			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			soundClip = (Clip) AudioSystem.getLine(info);
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {

			e.printStackTrace();
		}

	}

	public void loop(boolean loop) {
		this.loop = loop;
		if (soundClip.isOpen()) {
			if (loop)
				soundClip.loop(-1);
			else
				soundClip.loop(0);
		}
	}

	public void play() {
		if (!soundClip.isOpen())
			try {
				soundClip.open(audioInputStream);
			} catch (LineUnavailableException | IOException e) {
				e.printStackTrace();
			}
		soundClip.setFramePosition(0);

		if (isPlaying())
			soundClip.stop();

		loop(loop);
		soundClip.start();

	}

	public void stop() {
		soundClip.stop();
		soundClip.setFramePosition(0);

	}

	public void pause() {
		soundClip.stop();

	}

	public void resume() {
		loop(loop);
		soundClip.start();

	}

	public long getPosition() {
		return soundClip.getMicrosecondPosition()
				% soundClip.getMicrosecondLength();
	}

	public void setPosition(long pos) {
		soundClip.setMicrosecondPosition((long) (pos * 1.0E3));
	}

	public boolean isPlaying() {
		if (!soundClip.isActive())
			return false;
		if (soundClip.getMicrosecondPosition()
				% soundClip.getMicrosecondLength() >= 0.9 * soundClip
				.getMicrosecondLength()) {
			stop();
			return true;
		}
		return true;
	}
}
