package edu.bouyaka.engine;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;

public class Gengine {

	public long lastUpdate, lastFps, lastTick, time;
	public int displayWidth = 1280, displayHeight = 720, screenWidth,
			screenHeight;
	public String pack;
	public String rev, shownFps = "5";
	public int npcAmount, spriteAmount;
	public int tileWidth, tileHeight, blurAmount, nHTiles, nVTiles;
	public double tick = 1, fps = 60;
	public boolean[] keyboard = new boolean[256];
	public boolean[][] entityEnabled;
	public boolean vSync = true, fullScreen = false, interfaceEdited;
	public BufferedImage mainBackGround, mainContent, mainInterface;
	public Display display;
	public JFrame window;
	public File resDir;
	private UpdateScreen refreshLoop;
	private UpdateEntity updateEntityLoop;
	private Timer refreshTimer;

	// Entity[0]=Players array
	// Entity[1]=Npc array
	// Entity[2]=Item array
	// Entity[3]=Interface array
	// Entity[4]=Buttons array
	// Entity[5]=Sprites array
	// Entity[6]=Undefined array
	// Entity[7]=Undefined array
	public Entity[][] entityArray;

	public Gengine() {
		refreshTimer = new Timer();
		Entity.engine = this;
		// Cr�ation d'une fen�tre principale
		window = new JFrame("Default");
		entityArray = new Entity[6][];
		entityEnabled = new boolean[6][];
		setPlayerAmount(2);
		setNpcAmount(10);
		setItemAmount(2);
		entityArray[2] = new Entity[1];
		entityEnabled[2] = new boolean[1];
		setInterfaceAmount(1);
		setButtonAmount(10);
		setSpriteAmount(2);
		// D�tection de l'affichage
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();

		refreshLoop = new UpdateScreen("refreshLoop", this);
		updateEntityLoop = new UpdateEntity(this);

	}

	public void start() {
		// Cr�ation de la zone d'affichage du jeu
		createDisplay(displayWidth, displayHeight);

		// Configuration de la fen�tre principale
		window.setSize(displayWidth, displayHeight);
		if (fullScreen)
			window.setLocation(0, 0);
		else
			window.setLocationRelativeTo(null);

		window.setContentPane(display);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		window.setResizable(false);
		window.setVisible(true);
		// Chargement de l'interface dans l'�cran
		display.setInterface(Interface(0).get());

	}

	public void update() {
		if (refreshLoop.isProcessed()
				&& updateEntityLoop.isProcessed()) {
			refreshTimer.newTimeKey();
			updateEntityLoop.run();
			refreshLoop.run();
			tick =  15* refreshTimer.delta() / (1E8);
		}

	}

	public Player Player(int id) {
		return (Player) entityArray[0][id];
	}

	public Npc Npc(int id) {
		return (Npc) entityArray[1][id];
	}

	public Item Item(int id) {
		return (Item) entityArray[2][id];
	}

	public Interface Interface(int id) {
		return (Interface) entityArray[3][id];
	}

	public Button Button(int id) {
		return (Button) entityArray[4][id];
	}

	public Sprite Sprite(int id) {
		return (Sprite) entityArray[5][id];
	}

	public void replacePlayer(int id, Player E) {
		entityArray[0][id] = E;
	}

	public void replaceNpc(int id, Npc E) {
		entityArray[1][id] = E;
	}

	public void replaceItem(int id, Item E) {
		entityArray[2][id] = E;
	}

	public void replaceInterface(int id, Interface E) {
		entityArray[3][id] = E;
	}

	public void replaceButton(int id, Button E) {
		entityArray[4][id] = E;
	}

	public void replaceSprite(int id, Sprite E) {
		entityArray[5][id] = E;
	}

	public void addPlayer(int id) {
		if (id >= entityArray[0].length)
			setPlayerAmount(entityArray[0].length + 5);
		entityArray[0][id] = null;
		entityArray[0][id] = new Player();
	}

	public void addNpc(int id) {
		if (id >= entityArray[1].length)
			setNpcAmount(entityArray[1].length + 5);
		entityArray[1][id] = null;
		entityArray[1][id] = new Npc();
	}

	public void addItem(int id) {
		if (id >= entityArray[2].length)
			setItemAmount(entityArray[2].length + 5);
		entityArray[2][id] = null;
		entityArray[2][id] = new Item();
	}

	public void addInterface(int id) {
		if (id >= entityArray[3].length)
			setInterfaceAmount(entityArray[3].length + 5);
		entityArray[3][id] = null;
		entityArray[3][id] = new Interface();
	}

	public void addButton(int id) {
		if (id >= entityArray[4].length)
			setButtonAmount(entityArray[4].length + 5);
		entityArray[4][id] = null;
		entityArray[4][id] = new Button();
	}

	public void addSprite(int id, BufferedImage image, int nFrame, int frameRate) {
		if (id >= entityArray[5].length)
			setSpriteAmount(entityArray[5].length + 5);
		entityArray[5][id] = null;
		entityArray[5][id] = new Sprite(image, nFrame, frameRate);
	}

	public void createDisplay(int width, int height) {
		display = new Display(width, height, this);

		// Configuration de la zone d'affichage du jeu
		display.setFocusable(true);
		display.requestFocus();
		display.setBlankColor(new Color(255, 255, 255, (int) (255 / (Math.pow(
				2, blurAmount)))));
		display.blank();
	}

	public void setPlayerAmount(int amount) {
		Player[] eTmp = new Player[amount];
		if (entityArray[0] != null)
			for (int id = 0; id < amount && id < (entityArray[0].length); id++) {
				eTmp[id] = (Player) entityArray[0][id];
			}
		entityArray[0] = eTmp;
		boolean[] bTmp = new boolean[amount];
		if (entityEnabled[0] != null)
			for (int id = 0; id < amount && id < (entityEnabled[0].length); id++) {
				bTmp[id] = entityEnabled[0][id];
			}
		entityEnabled[0] = bTmp;
	}

	public void setNpcAmount(int amount) {
		Npc[] eTmp = new Npc[amount];

		if (entityArray[1] != null)
			for (int id = 0; id < amount && id < (entityArray[1].length); id++) {
				eTmp[id] = (Npc) entityArray[1][id];
			}
		entityArray[1] = eTmp;
		boolean[] bTmp = new boolean[amount];
		if (entityEnabled[1] != null)
			for (int id = 0; id < amount && id < (entityEnabled[1].length); id++) {
				bTmp[id] = entityEnabled[1][id];
			}
		entityEnabled[1] = bTmp;
	}

	public void setItemAmount(int amount) {
		Item[] eTmp = new Item[amount];
		entityEnabled[2] = new boolean[amount];
		if (entityArray[2] != null)
			for (int id = 0; id < amount && id < (entityArray[2].length); id++) {
				eTmp[id] = (Item) entityArray[2][id];
			}
		entityArray[2] = eTmp;
		boolean[] bTmp = new boolean[amount];
		if (entityEnabled[2] != null)
			for (int id = 0; id < amount && id < (entityEnabled[2].length); id++) {
				bTmp[id] = entityEnabled[2][id];
			}
		entityEnabled[2] = bTmp;
	}

	public void setInterfaceAmount(int amount) {
		Interface[] eTmp = new Interface[amount];
		entityEnabled[3] = new boolean[amount];
		if (entityArray[3] != null)
			for (int id = 0; id < amount && id < (entityArray[3].length); id++) {
				eTmp[id] = (Interface) entityArray[3][id];
			}
		entityArray[3] = eTmp;
		boolean[] bTmp = new boolean[amount];
		if (entityEnabled[3] != null)
			for (int id = 0; id < amount && id < (entityEnabled[3].length); id++) {
				bTmp[id] = entityEnabled[0][id];
			}
		entityEnabled[3] = bTmp;
	}

	public void setButtonAmount(int amount) {
		Button[] eTmp = new Button[amount];
		entityEnabled[4] = new boolean[amount];
		if (entityArray[4] != null)
			for (int id = 0; id < amount && id < (entityArray[4].length); id++) {
				eTmp[id] = (Button) entityArray[4][id];
			}
		entityArray[4] = eTmp;
		boolean[] bTmp = new boolean[amount];
		if (entityEnabled[4] != null)
			for (int id = 0; id < amount && id < (entityEnabled[4].length); id++) {
				bTmp[id] = entityEnabled[4][id];
			}
		entityEnabled[4] = bTmp;
	}

	public void setSpriteAmount(int amount) {
		Sprite[] eTmp = new Sprite[amount];
		entityEnabled[5] = new boolean[amount];
		if (entityArray[5] != null)
			for (int id = 0; id < amount && id < (entityArray[5].length); id++) {
				eTmp[id] = (Sprite) entityArray[5][id];
			}
		entityArray[5] = eTmp;
		boolean[] bTmp = new boolean[amount];
		if (entityEnabled[0] != null)
			for (int id = 0; id < amount && id < (entityEnabled[0].length); id++) {
				bTmp[id] = entityEnabled[0][id];
			}
		entityEnabled[5] = bTmp;
	}

}
