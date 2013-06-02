package edu.bouyaka.engine;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;

import javax.swing.JFrame;

import edu.bouyaka.engine.Interface;
import edu.bouyaka.engine.abstracted.Vector;
import edu.bouyaka.engine.action.InitialiseDevInterface;
import edu.bouyaka.engine.action.InterpretConsole;
import edu.bouyaka.engine.action.ManageDevInterface;
import edu.bouyaka.engine.action.ShowCredits;
import edu.bouyaka.engine.concreted.Button;
import edu.bouyaka.engine.concreted.Item;
import edu.bouyaka.engine.concreted.Npc;
import edu.bouyaka.engine.concreted.Player;
import edu.bouyaka.engine.concreted.TextBox;
import edu.bouyaka.engine.interfaces.Collision;
import edu.bouyaka.engine.interfaces.RegularC;
import edu.bouyaka.engine.io.Keyboard;
import edu.bouyaka.engine.io.Mouse;
import edu.bouyaka.engine.media.Sound;
import edu.bouyaka.engine.media.Sprite;

public final class Gengine {

	public int displayWidth = 1280, displayHeight = 720, screenWidth,
			screenHeight;
	public String pack;
	public String rev, shownFps = "5";
	public int blurAmount, nHTiles, nVTiles;
	public double tick = 1, fps = 60, tickTime;
	public boolean[][] entityEnabled;
	public boolean fullScreen = false, devMode = false;
	public boolean typingText;
	public BufferedImage mainBackGround, mainContent, mainInterface;
	public Display display;
	public JFrame window;
	public File resDir = new File(System.getProperty("user.dir") + "/res/", "/");
	public UpdateScreen refreshLoop;
	public UpdateEntity updateEntityLoop;
	public HeightMapManager heightManager = new HeightMapManager(1000, 10);
	private Timer refreshTimer;
	public Vector vRef = new Vector();
	public Mouse mouse = new Mouse();
	public Keyboard keyboard = new Keyboard();
	public char lastInput;
	public String state;
	public Collision collision = new RegularC();
	public String GameName = "DAh";
	public Entity selectedEntity = null, hooveredEntity = null,
			pressedEntity = null;
	public TextBox console;
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
		entityArray = new Entity[7][];
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

	/**
	 * Permet de demarrer le moteur
	 */
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
		Script devManagement = new Script(10);
		devManagement.setFreq(10);
		devManagement.defineAction(0, new ShowCredits(), 0);
		devManagement.defineAction(1, new InitialiseDevInterface(), 0);
		devManagement.defineAction(2, new ManageDevInterface(), 250);
		devManagement.defineAction(3, new InterpretConsole(), 250);
		devManagement.start();

	}

	/**
	 * Permet de mettre a jour le contenu du jeu
	 */
	public void update() {

		if (updateEntityLoop.isProcessed())
			updateEntityLoop.run();
		if (refreshLoop.isProcessed())
			refreshLoop.run();

		double delta = refreshTimer.delta();
		refreshTimer.newTimeKey();
		tick = 15 * delta / (1E8);
		fps = 1.0E9 / delta;
		tickTime = tickTime + tick;

	}

	/**
	 * Permet d'acceder a un joueur
	 */
	public Player Player(int id) {
		return (Player) entityArray[0][id];
	}

	/**
	 * Permet d'acceder a un personnage non joueur
	 */
	public Npc Npc(int id) {
		return (Npc) entityArray[1][id];
	}

	/**
	 * Permet d'acceder a un item
	 */
	public Item Item(int id) {
		return (Item) entityArray[2][id];
	}

	/**
	 * Permet d'acceder a une interface
	 */
	public Interface Interface(int id) {
		return (Interface) entityArray[3][id];
	}

	/**
	 * Permet d'acceder a un bouton
	 */
	public Button Button(int id) {
		return (Button) entityArray[4][id];
	}

	/**
	 * Permet d'acceder a un sprite
	 */
	public Sprite Sprite(int id) {
		return (Sprite) entityArray[5][id];
	}

	/**
	 * Permet d'acceder a un son
	 */
	public Sound Sound(int id) {
		return (Sound) entityArray[6][id];
	}

	/**
	 * Permet de remplacer un joueur par un autre (modifie)
	 * 
	 * @param id
	 *            : identifiant du joueur a modifier
	 * @param E
	 *            : joueur de remplacement
	 */
	public void replacePlayer(int id, Player E) {
		if (id >= entityArray[0].length)
			setPlayerAmount(id + 1);
		entityArray[0][id] = E;
	}

	/**
	 * Permet de remplacer un personnage non joueur par un autre (modifie)
	 * 
	 * @param id
	 *            : identifiant du personnage non joueur a modifier
	 * @param E
	 *            : personnage non joueur de remplacement
	 */
	public void replaceNpc(int id, Npc E) {
		if (id >= entityArray[1].length)
			setNpcAmount(id + 1);
		entityArray[1][id] = E;
	}

	/**
	 * Permet de remplacer un item par un autre (modifie)
	 * 
	 * @param id
	 *            : identifiant du item a modifier
	 * @param E
	 *            : item de remplacement
	 */
	public void replaceItem(int id, Item E) {
		if (id >= entityArray[2].length)
			setItemAmount(id + 1);
		entityArray[2][id] = E;
	}

	/**
	 * Permet de remplacer une interface par une autre (modifie)
	 * 
	 * @param id
	 *            : identifiant de l'interface a modifier
	 * @param E
	 *            : interface de remplacement
	 */
	public void replaceInterface(int id, Interface E) {
		if (id >= entityArray[3].length)
			setInterfaceAmount(id + 1);
		entityArray[3][id] = E;
	}

	/**
	 * Permet de remplacer un boutton par un autre (modifie)
	 * 
	 * @param id
	 *            : identifiant du boutton a modifier
	 * @param E
	 *            : boutton de remplacement
	 */
	public void replaceButton(int id, Button E) {
		if (id >= entityArray[4].length)
			setButtonAmount(id + 1);
		entityArray[4][id] = E;
	}

	/**
	 * Permet de remplacer un sprite par un autre (modifie)
	 * 
	 * @param id
	 *            : identifiant du sprite a modifier
	 * @param E
	 *            : sprite de remplacement
	 */
	public void replaceSprite(int id, Sprite E) {
		if (id >= entityArray[5].length)
			setSpriteAmount(id + 1);
		entityArray[5][id] = E;
	}

	/**
	 * Permet de remplacer un son par un autre (modifie)
	 * 
	 * @param id
	 *            : identifiant du son a modifier
	 * @param E
	 *            : son de remplacement
	 */
	public void replaceSound(int id, Sound E) {
		if (id >= entityArray[6].length)
			setSoundAmount(id + 1);
		entityArray[6][id] = E;
	}

	/**
	 * Permet de creer un nouveau joueur
	 * 
	 * @param id
	 *            : identifiant du joueur
	 */
	public void addPlayer(int id) {
		if (id >= entityArray[0].length)
			setPlayerAmount(id + 1);
		entityArray[0][id] = new Player();
	}

	/**
	 * Permet de creer un nouveau personnage non joueur
	 * 
	 * @param id
	 *            : identifiant du personnage non joueur
	 */
	public void addNpc(int id) {
		if (id >= entityArray[1].length)
			setNpcAmount(id + 1);
		entityArray[1][id] = new Npc();
	}

	/**
	 * Permet de creer un nouvel item
	 * 
	 * @param id
	 *            : identifiant du item
	 */
	public void addItem(int id) {
		if (id >= entityArray[2].length)
			setItemAmount(id + 1);
		entityArray[2][id] = new Item();
	}

	/**
	 * Permet de creer une nouvelle interface
	 * 
	 * @param id
	 *            : identifiant de l'interface
	 */
	public void addInterface(int id) {
		if (id >= entityArray[3].length)
			setInterfaceAmount(id + 1);
		entityArray[3][id] = new Interface();
	}

	/**
	 * Permet de creer un nouveau bouton
	 * 
	 * @param id
	 *            : identifiant du bouton
	 */
	public void addButton(int id) {
		if (id >= entityArray[4].length)
			setButtonAmount(id + 1);
		entityArray[4][id] = new Button();
	}

	/**
	 * Permet de creer un nouveau sprite
	 * 
	 * @param id
	 *            : identifiant du sprite
	 */
	public void addSprite(int id, BufferedImage image, int nFrame, int frameRate) {
		if (id >= entityArray[5].length)
			setSpriteAmount(id + 1);
		entityArray[5][id] = new Sprite(image, nFrame, frameRate);
	}

	/**
	 * Permet de creer un nouveau son
	 * 
	 * @param id
	 *            : identifiant du son
	 */
	public void addSound(int id, String location) {
		if (id >= entityArray[6].length)
			setSpriteAmount(id + 1);
		entityArray[6][id] = new Sound(location);
	}

	/**
	 * Permet de supprimer une entite
	 * 
	 * @param E
	 *            : entite a supprimer
	 */
	public void removeEntity(Entity E) {
		for (int type = 0; type < entityArray.length; type++) {
			try {
				for (int id = 0; id < entityArray[type].length; id++) {
					if (entityArray[type][id] == E) {
						entityArray[type][id] = null;
					}
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 
	 * @param width
	 *            : largeur de l'affichage
	 * @param height
	 *            : hauteur de l'affichage
	 */
	public void createDisplay(int width, int height) {
		display = new Display(width, height, this);

		// Configuration de la zone d'affichage du jeu
		display.setFocusable(true);
		display.requestFocus();
	}

	/**
	 * Permet de definir le nombre de joueur
	 */
	public void setPlayerAmount(int amount) {
		Player[] eTmp = new Player[amount];
		if (entityArray[0] != null)
			for (int id = 0; id < amount && id < (entityArray[0].length); id++) {
				eTmp[id] = (Player) entityArray[0][id];
			}
		entityArray[0] = eTmp;

	}

	/**
	 * Permet de definir le nombre de personnages non joueurs
	 */
	public void setNpcAmount(int amount) {
		Npc[] eTmp = new Npc[amount];

		if (entityArray[1] != null)
			for (int id = 0; id < amount && id < (entityArray[1].length); id++) {
				eTmp[id] = (Npc) entityArray[1][id];
			}
		entityArray[1] = eTmp;

	}

	/**
	 * Permet de definir le nombre d'items
	 */
	public void setItemAmount(int amount) {
		Item[] eTmp = new Item[amount];
		entityEnabled[2] = new boolean[amount];
		if (entityArray[2] != null)
			for (int id = 0; id < amount && id < (entityArray[2].length); id++) {
				eTmp[id] = (Item) entityArray[2][id];
			}
		entityArray[2] = eTmp;

	}

	/**
	 * Permet de definir le nombre d'interfaces
	 */
	public void setInterfaceAmount(int amount) {
		Interface[] eTmp = new Interface[amount];
		entityEnabled[3] = new boolean[amount];
		if (entityArray[3] != null)
			for (int id = 0; id < amount && id < (entityArray[3].length); id++) {
				eTmp[id] = (Interface) entityArray[3][id];
			}
		entityArray[3] = eTmp;

	}

	/**
	 * Permet de definir le nombre de boutons
	 */
	public void setButtonAmount(int amount) {
		Button[] eTmp = new Button[amount];
		entityEnabled[4] = new boolean[amount];
		if (entityArray[4] != null)
			for (int id = 0; id < amount && id < (entityArray[4].length); id++) {
				eTmp[id] = (Button) entityArray[4][id];
			}
		entityArray[4] = eTmp;

	}

	/**
	 * Permet de definir le nombre de sprites
	 */
	public void setSpriteAmount(int amount) {
		Sprite[] eTmp = new Sprite[amount];
		if (entityArray[5] != null)
			for (int id = 0; id < amount && id < (entityArray[5].length); id++) {
				eTmp[id] = (Sprite) entityArray[5][id];
			}
		entityArray[5] = eTmp;

	}

	/**
	 * Permet de definir le nombre de sons
	 */
	public void setSoundAmount(int amount) {
		Sound[] eTmp = new Sound[amount];
		if (entityArray[6] != null)
			for (int id = 0; id < amount && id < (entityArray[6].length); id++) {
				eTmp[id] = (Sound) entityArray[6][id];
			}
		entityArray[6] = eTmp;

	}

}
