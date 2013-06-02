package edu.bouyaka.engine.media;

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
	private Clip soundClip = null;
	private SoundControl sndCtrl;
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
			sndCtrl = new SoundControl(soundClip, this);
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {

			e.printStackTrace();
		}

	}

	public Sound(AudioInputStream stream) {
		try {

			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			soundClip = (Clip) AudioSystem.getLine(info);
			sndCtrl = new SoundControl(soundClip, this);
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		}

	}

	public Sound clone() {
		Sound tmp = new Sound(audioInputStream);
		tmp.loop(sndCtrl.loopState);
		return tmp;
	}

	public void loop(boolean loop) {
		sndCtrl.loopState = loop;
	}

	public void play() {
		sndCtrl.play = true;
		sndCtrl.run();
	}

	public void stop() {
		sndCtrl.stop = true;
		if (!sndCtrl.isRunning())
			sndCtrl.run();

	}

	public void pause() {
		sndCtrl.pause = true;
		if (!sndCtrl.isRunning())
			sndCtrl.run();

	}

	public void resume() {
		sndCtrl.resume = true;
		if (!sndCtrl.isRunning())
			sndCtrl.run();

	}

	public long getPosition() {
		return soundClip.getMicrosecondPosition()
				% soundClip.getMicrosecondLength();
	}

	public void setPosition(long pos) {
		sndCtrl.setPosition = true;
		sndCtrl.position = (long) (pos * 1.0E3);
		if (!sndCtrl.isRunning())
			sndCtrl.run();
	}

	public boolean isPlaying() {
		if (!sndCtrl.isRunning()) {
			sndCtrl.playCheck = true;
			sndCtrl.run();
		}
		if (!soundClip.isActive())
			return false;
		return true;
	}

}

class SoundControl extends Thread {
	public boolean play, stop, pause, resume, loopState, setPosition,
			playCheck;
	private boolean running;
	public long position;
	public Clip soundClip = null;
	public Sound father = null;

	public SoundControl(Clip soundClip, Sound father) {
		this.soundClip = soundClip;
		this.father = father;
	}

	public void run() {
		running = true;
		if (playCheck && soundClip.isOpen()) {
			playCheck = false;
			if (soundClip.getFramePosition() % soundClip.getFrameLength() >= 0.98 * soundClip
					.getFrameLength()) {
				stopSnd();
			}
		} else {
			if (play)
				play();
			if (soundClip.isOpen()) {

				if (stop)
					stopSnd();
				if (resume)
					resumeSnd();
				if (pause)
					pause();
				if (setPosition)
					setPosition(position);
			}
		}
		try {
			Thread.sleep(1000 / 120);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;

	}

	public void loop(boolean loop) {
		if (soundClip.isOpen()) {
			if (loop)
				soundClip.loop(-1);
			else
				soundClip.loop(0);
		}

	}

	public void play() {
		play = false;
		if (!soundClip.isOpen())
			try {
				soundClip.open(father.audioInputStream);
			} catch (LineUnavailableException | IOException e) {
				e.printStackTrace();
			}
		soundClip.setFramePosition(0);

		if (father.isPlaying()) {
			stop = true;
			run();
		}
		loop(loopState);
		soundClip.start();

	}

	public void stopSnd() {
		stop = false;
		soundClip.stop();
		soundClip.setFramePosition(0);

	}

	public void pause() {
		pause = false;
		soundClip.stop();

	}

	public void resumeSnd() {
		resume = false;
		soundClip.start();

	}

	public void setPosition(long pos) {
		setPosition = false;
		soundClip.setMicrosecondPosition((long) (pos * 1.0E3));
	}

	public boolean isRunning() {
		return running;
	}
}
