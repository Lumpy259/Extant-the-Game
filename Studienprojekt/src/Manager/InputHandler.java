package Manager;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;

import Main.Game;

/**
 * @author lumpy Class for Manage the Keys
 */
public class InputHandler implements KeyListener, FocusListener, MouseListener,
		MouseMotionListener {

	public static int pressedKey;
	public static int NUM_KEYS = 29;
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int USE = 4;
	public static int INVENTORY = 5;
	public static int SPACE = 6;
	public static int STRG = 7;
	public static int MENU = 8;
	public static int STATUS = 9;

	public static int INGAMEMENUE = 10;

	public static int ONE = 11;
	public static int TWO = 12;
	public static int THREE = 13;
	public static int FOUR = 14;
	public static int FIVE = 15;
	public static int SIX = 16;
	public static int SEVEN = 17;
	public static int EIGHT = 18;
	public static int NINE = 19;
	public static int ZERO = 20;
	public static int ENTER = 21;
	public static int BOSS = 22;
	public static int BACKSPACE = 23;
	public static int TALK = 24;
	public static int DEBUG = 25;
	public static int MAP = 26;
	public static int TUTORIAL = 27;
	public static int CRAFTING = 28;
	
	private int keyUP, keyDOWN, keyRIGHT, keyLEFT, keyUSE, keyWEAPON,
			keyINVENTORY, keySTAT;
	private String up, down, right, left, weapon, use, inventory, stats;
	FileReader fr;
	FileWriter filewriter;
	BufferedReader br;

	public InputHandler(Game game) {
		game.addKeyListener(this);
		game.addFocusListener(this);
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		/*
		 * Loading the Control Keys from the control textfile in the Resource
		 * directory.
		 */
		try {
			File f = new File("Resources/Options/control.txt");
			// File b = new File("Options/control.txt");
			// System.out.println(new
			// File("Resources/Options/control.txt").exists());
			// System.out.println(new File("Options/control.txt").exists());
			// System.out.println(new File("/Options/control.txt").exists());
			// System.out.println(new File("/control.txt").exists());
			// System.out.println(f.getAbsolutePath());
			// System.out.println(b.getAbsolutePath());

			InputStream in = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			reader.close();
			up = out.substring(0, 2);
			down = out.substring(2, 4);
			right = out.substring(4, 6);
			left = out.substring(6, 8);
			;
			use = out.substring(8, 10);
			weapon = out.substring(10, 12);
			inventory = out.substring(12, 14);
			stats = out.substring(14, 16);

		} catch (IOException e) {
			e.printStackTrace();
		}
		keyUP = new Integer(up);
		keyDOWN = new Integer(down);
		keyRIGHT = new Integer(right);
		keyLEFT = new Integer(left);
		keyUSE = new Integer(use);
		keyWEAPON = new Integer(weapon);
		keyINVENTORY = new Integer(inventory);
		keySTAT = new Integer(stats);

	}

	public void keyPressed(KeyEvent ke) {

		keySet(ke.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent ke) {
		keySet(ke.getKeyCode(), false);
		pressedKey = ke.getKeyCode();
	}

	public String getKeyUP() {
		return up;
	}

	public String getKeyDOWN() {
		return down;
	}

	public String getKeyRIGHT() {
		return right;
	}

	public String getKeyLEFT() {
		return left;
	}

	public String getKeyUSE() {
		return use;
	}

	public String getKeyWEAPON() {
		return weapon;
	}

	public String getKeyINVENTORY() {
		return inventory;
	}

	public String getKeySTAT() {
		return stats;
	}

	public void setKeyUP(int key) {
		this.keyUP = key;
	}

	public void setKeyDOWN(int key) {
		this.keyDOWN = key;
	}

	public void setKeyRIGHT(int key) {
		this.keyRIGHT = key;
	}

	public void setKeyLEFT(int key) {
		this.keyLEFT = key;
	}

	public void setKeyUSE(int key) {
		this.keyUSE = key;
	}

	public void setKeyWEAPON(int key) {
		this.keyWEAPON = key;
	}

	public void setKeyINVENTORY(int key) {
		this.keyINVENTORY = key;
	}

	public void setKeySTAT(int key) {
		this.keySTAT = key;
	}

	public void getFile() {
		try {
			InputStream in = new FileInputStream(new File(
					"Resources/Options/control.txt"));
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			reader.close();
			up = out.substring(0, 2);
			down = out.substring(2, 4);
			right = out.substring(4, 6);
			left = out.substring(6, 8);
			;
			use = out.substring(8, 10);
			weapon = out.substring(10, 12);
			inventory = out.substring(12, 14);
			stats = out.substring(14, 16);

		} catch (IOException e) {
			e.printStackTrace();
		}
		keyUP = new Integer(up);
		keyDOWN = new Integer(down);
		keyRIGHT = new Integer(right);
		keyLEFT = new Integer(left);
		keyUSE = new Integer(use);
		keyWEAPON = new Integer(weapon);
		keyINVENTORY = new Integer(inventory);
		keySTAT = new Integer(stats);

	}

	/*
	 * Write the controll keys in the controll textfile in the Resource folder
	 */

	public void writeFile(String up, String down, String right, String left,
			String use, String weapon, String inventory, String stats) {
		try {
			filewriter = new FileWriter("Resources/Options/control.txt");
			filewriter.write(up + "\n");
			filewriter.write(down + "\n");
			filewriter.write(right + "\n");
			filewriter.write(left + "\n");
			filewriter.write(use + "\n");
			filewriter.write(weapon + "\n");
			filewriter.write(inventory + "\n");
			filewriter.write(stats + "\n");
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void keySet(int keyCode, boolean pressed) {

		if (keyCode == keyUP)
			keyState[UP] = pressed;
		if (keyCode == keyDOWN)
			keyState[DOWN] = pressed;
		if (keyCode == keyLEFT)
			keyState[LEFT] = pressed;
		if (keyCode == keyRIGHT)
			keyState[RIGHT] = pressed;
		if (keyCode == keyWEAPON)
			keyState[SPACE] = pressed;
		if (keyCode == KeyEvent.VK_CONTROL)
			keyState[STRG] = pressed;
		if (keyCode == KeyEvent.VK_ENTER)
			keyState[ENTER] = pressed;
		if (keyCode == KeyEvent.VK_BACK_SPACE)
			keyState[BACKSPACE] = pressed;

		if (keyCode == keyUSE)
			keyState[USE] = pressed;
		if (keyCode == keyINVENTORY)
			keyState[INVENTORY] = pressed;
		if (keyCode == keySTAT)
			keyState[STATUS] = pressed;
		if (keyCode == KeyEvent.VK_ESCAPE)
			keyState[INGAMEMENUE] = pressed;
		if (keyCode == KeyEvent.VK_1)
			keyState[ONE] = pressed;
		if (keyCode == KeyEvent.VK_2)
			keyState[TWO] = pressed;
		if (keyCode == KeyEvent.VK_3)
			keyState[THREE] = pressed;
		if (keyCode == KeyEvent.VK_4)
			keyState[FOUR] = pressed;
		if (keyCode == KeyEvent.VK_5)
			keyState[FIVE] = pressed;
		if (keyCode == KeyEvent.VK_6)
			keyState[SIX] = pressed;
		if (keyCode == KeyEvent.VK_7)
			keyState[SEVEN] = pressed;
		if (keyCode == KeyEvent.VK_8)
			keyState[EIGHT] = pressed;
		if (keyCode == KeyEvent.VK_9)
			keyState[NINE] = pressed;
		if (keyCode == KeyEvent.VK_0)
			keyState[ZERO] = pressed;
		if (keyCode == KeyEvent.VK_Q)
			keyState[BOSS] = pressed;
		if (keyCode == KeyEvent.VK_T)
			keyState[TALK] = pressed;
		if (keyCode == KeyEvent.VK_D)
			keyState[DEBUG] = pressed;
		if (keyCode == KeyEvent.VK_M)
			keyState[MAP] = pressed;
		if (keyCode == KeyEvent.VK_P)
			keyState[TUTORIAL] = pressed;
		if (keyCode == KeyEvent.VK_C)
			keyState[CRAFTING] = pressed;

	}

	public void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}

	public void keyTyped(KeyEvent ke) {

	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void mouseDragged(MouseEvent arg0) {

	}

	public void mouseMoved(MouseEvent arg0) {

	}

	public void focusGained(FocusEvent arg0) {

	}

	public void focusLost(FocusEvent fe) {
		for (int i = 0; i < keyState.length; i++)
			keyState[i] = false;
	}
}
