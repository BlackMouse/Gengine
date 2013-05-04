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
	boolean loop, playing;

	public Sound(String fileName) {

		AudioInputStream audioInputStream = null;
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
							+ fileName + "N'a pas pu �tre charg�");
				}

			}

			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			soundClip = (Clip) AudioSystem.getLine(info);
			soundClip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loop(boolean loop) {
		this.loop = loop;
		if (loop)
			soundClip.loop(-1);
		else
			soundClip.loop(0);
	}

	public void play() {
		playing = true;
		stop();
		loop(loop);
		soundClip.start();

	}

	public void stop() {
		playing = false;
		soundClip.stop();
		soundClip.setMicrosecondPosition(0);

	}

	public void pause() {
		playing = false;
		soundClip.stop();

	}

	public void resume() {
		if (playing = false) {
			playing = true;
			soundClip.start();
		}

	}

	public boolean isPlaying() {
		if (soundClip.getMicrosecondLength() == soundClip
				.getMicrosecondPosition())
			return false;
		return playing;
	}

}
