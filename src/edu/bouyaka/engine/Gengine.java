package edu.bouyaka.engine;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;

import edu.bouyaka.engine.Interface;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.concreted.Button;
import edu.bouyaka.engine.concreted.Item;
import edu.bouyaka.engine.concreted.Npc;
import edu.bouyaka.engine.concreted.Player;
import edu.bouyaka.engine.concreted.Sound;
import edu.bouyaka.engine.interfaces.Collision;
import edu.bouyaka.engine.interfaces.RegularC;

public final class Gengine {

	public int displayWidth = 1280, displayHeight = 720, screenWidth,
			screenHeight;
	public String pack;
	public String rev, shownFps = "5";
	public int npcAmount, spriteAmount;
	public int blurAmount, nHTiles, nVTiles;
	public double tick = 1, fps = 60, tickTime;
	public boolean[][] entityEnabled;
	public boolean vSync = true, fullScreen = false, interfaceEdited, devMode;
	public BufferedImage mainBackGround, mainContent, mainInterface;
	public Display display;
	public JFrame window;
	public File resDir = new File(System.getProperty("user.dir") + "/res/", "/");
	public UpdateScreen refreshLoop;
	public UpdateEntity updateEntityLoop;
	public HeightMapManager heightManager = new HeightMapManager(200, 10);
	private Timer refreshTimer;
	public Vector vRef = new Vector();
	public Mouse mouse = new Mouse();
	public Keyboard keyboard = new Keyboard();
	public char lastInput;
	public String state;
	public Collision collision = new RegularC();
	public String GameName = "DAh";
	public Entity selectedEntity = null;
	public GraphicsConfiguration screenConfig;

	// Entity[0]=Players array
	// Entity[1]=Npc array
	// Entity[2]=Item array
	// Entity[3]=Interface array
	// Entity[4]=Buttons array
	// Entity[5]=Sprites array
	// Entity[6]=Sounds array
	// Entity[7]=Undefined array
	public Entity[][] entityArray;

	public Gengine(String GameName) {
		this.GameName = GameName;
		refreshTimer = new Timer();
		Entity.engine = this;
		// Cr�ation d'une fen�tre principale
		window = new JFrame(GameName);
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
		screenConfig = gd.getDefaultConfiguration();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();

		refreshLoop = new UpdateScreen(this);
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

	}

	public void update() {
		refreshTimer.newTimeKey();

		if (updateEntityLoop.isProcessed())
			updateEntityLoop.run();

		if (refreshLoop.isProcessed())
			refreshLoop.run();

		double delta = refreshTimer.delta();
		tick = 15 * delta / (1E8);
		fps = 1.0E9 / delta;
		tickTime = tickTime + tick;

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

	public Sound Sound(int id) {
		return (Sound) entityArray[6][id];
	}

	public void replacePlayer(int id, Player E) {
		if (id >= entityArray[0].length)
			setPlayerAmount(id + 1);
		entityArray[0][id] = E;
	}

	public void replaceNpc(int id, Npc E) {
		if (id >= entityArray[1].length)
			setNpcAmount(id + 1);
		entityArray[1][id] = E;
	}

	public void replaceItem(int id, Item E) {
		if (id >= entityArray[2].length)
			setItemAmount(id + 1);
		entityArray[2][id] = E;
	}

	public void replaceInterface(int id, Interface E) {
		if (id >= entityArray[3].length)
			setInterfaceAmount(id + 1);
		entityArray[3][id] = E;
	}

	public void replaceButton(int id, Button E) {
		if (id >= entityArray[4].length)
			setButtonAmount(id + 1);
		entityArray[4][id] = E;
	}

	public void replaceSprite(int id, Sprite E) {
		if (id >= entityArray[5].length)
			setSpriteAmount(id + 1);
		entityArray[5][id] = E;
	}

	public void replaceSound(int id, Sound E) {
		if (id >= entityArray[6].length)
			setSoundAmount(id + 1);
		entityArray[6][id] = E;
	}

	public void addPlayer(int id) {
		if (id >= entityArray[0].length)
			setPlayerAmount(id + 1);
		entityArray[0][id] = new Player();
	}

	public void addNpc(int id) {
		if (id >= entityArray[1].length)
			setNpcAmount(id + 1);
		entityArray[1][id] = new Npc();
	}

	public void addItem(int id) {
		if (id >= entityArray[2].length)
			setItemAmount(id + 1);
		entityArray[2][id] = new Item();
	}

	public void addInterface(int id) {
		if (id >= entityArray[3].length)
			setInterfaceAmount(id + 1);
		entityArray[3][id] = new Interface();
	}

	public void addButton(int id) {
		if (id >= entityArray[4].length)
			setButtonAmount(id + 1);
		entityArray[4][id] = new Button();
	}

	public void addSprite(int id, BufferedImage image, int nFrame, int frameRate) {
		if (id >= entityArray[5].length)
			setSpriteAmount(id + 1);
		entityArray[5][id] = new Sprite(image, nFrame, frameRate);
	}

	public void addSound(int id, String location) {
		if (id >= entityArray[6].length)
			setSpriteAmount(id + 1);
		entityArray[6][id] = new Sound(location);
	}

	public void createDisplay(int width, int height) {
		display = new Display(width, height, this);

		// Configuration de la zone d'affichage du jeu
		display.setFocusable(true);
		display.requestFocus();
	}

	public void setPlayerAmount(int amount) {
		Player[] eTmp = new Player[amount];
		if (entityArray[0] != null)
			for (int id = 0; id < amount && id < (entityArray[0].length); id++) {
				eTmp[id] = (Player) entityArray[0][id];
			}
		entityArray[0] = eTmp;

	}

	public void setNpcAmount(int amount) {
		Npc[] eTmp = new Npc[amount];

		if (entityArray[1] != null)
			for (int id = 0; id < amount && id < (entityArray[1].length); id++) {
				eTmp[id] = (Npc) entityArray[1][id];
			}
		entityArray[1] = eTmp;

	}

	public void setItemAmount(int amount) {
		Item[] eTmp = new Item[amount];
		entityEnabled[2] = new boolean[amount];
		if (entityArray[2] != null)
			for (int id = 0; id < amount && id < (entityArray[2].length); id++) {
				eTmp[id] = (Item) entityArray[2][id];
			}
		entityArray[2] = eTmp;

	}

	public void setInterfaceAmount(int amount) {
		Interface[] eTmp = new Interface[amount];
		entityEnabled[3] = new boolean[amount];
		if (entityArray[3] != null)
			for (int id = 0; id < amount && id < (entityArray[3].length); id++) {
				eTmp[id] = (Interface) entityArray[3][id];
			}
		entityArray[3] = eTmp;

	}

	public void setButtonAmount(int amount) {
		Button[] eTmp = new Button[amount];
		entityEnabled[4] = new boolean[amount];
		if (entityArray[4] != null)
			for (int id = 0; id < amount && id < (entityArray[4].length); id++) {
				eTmp[id] = (Button) entityArray[4][id];
			}
		entityArray[4] = eTmp;

	}

	public void setSpriteAmount(int amount) {
		Sprite[] eTmp = new Sprite[amount];
		if (entityArray[5] != null)
			for (int id = 0; id < amount && id < (entityArray[5].length); id++) {
				eTmp[id] = (Sprite) entityArray[5][id];
			}
		entityArray[5] = eTmp;

	}

	public void setSoundAmount(int amount) {
		Sound[] eTmp = new Sound[amount];
		if (entityArray[6] != null)
			for (int id = 0; id < amount && id < (entityArray[6].length); id++) {
				eTmp[id] = (Sound) entityArray[6][id];
			}
		entityArray[6] = eTmp;

	}

}
