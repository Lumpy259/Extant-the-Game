package Manager;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Entity.Player;
import Main.Game;

/**
 * @author lumpy Class for change and managing the control.
 */
public class OptionsMenu {

	private InGameMenue menue;
	private Content hud1, hud2, hud3, hud4;
	private int state;
	FileReader fr;
	BufferedReader br;
	String up, down, right, left, weapon, use, inventory, stats;
	boolean pressedEnter = false;
	private boolean firstTime = true;
	GameStateManager gsm;
	ArrayList<String> line = new ArrayList<>();
	ArrayList<String> linesave;
	InputHandler input;
	Integer s;
	Graphics g;

	/**
	 * The Controlkeys are saved as integer numbers, this Method translate the
	 * integer in a "String", this Method is for the popup windows where you
	 * enter the new Key
	 */
	private void checkZeichen(Integer s) {
		String s1 = String.valueOf((char) Integer.parseInt(s.toString()));
		if (s == 38)
			g.drawString("UP", 240, 212);
		else if (s == 40)
			g.drawString("DOWN", 240, 212);
		else if (s == 37)
			g.drawString("LEFT", 240, 212);
		else if (s == 39)
			g.drawString("RIGHT", 240, 212);
		else if (s == 32)
			g.drawString("SPACE", 240, 212);
		else
			g.drawString(s1, 240, 212);
	}

	/**
	 * The Controlkeys are saved as integer numbers, this Method translate the
	 * integer in a "String", this Method also Checks if the Key is set as a
	 * dumb key, like if you change the UP function with pressing the arrow
	 * down. This dumb keys are marked with !
	 */
	private void zeichenUmsetzung() {

		if (line.get(0).equals("38"))
			g.drawString("UP", 162, 68);
		else if (line.get(0).equals("40") || line.get(0).equals("39")
				|| line.get(0).equals("37")) {
			line.set(0, "33");
		} else
			g.drawString(String.valueOf((char) Integer.parseInt(line.get(0))),
					162, 68);
		if (line.get(1).equals("40"))
			g.drawString("DOWN", 162, 119);
		else if (line.get(1).equals("38") || line.get(1).equals("39")
				|| line.get(1).equals("37")) {
			line.set(1, "33");
		} else
			g.drawString(String.valueOf((char) Integer.parseInt(line.get(1))),
					162, 119);
		if (line.get(2).equals("39"))
			g.drawString("RIGHT", 162, 166);
		else if (line.get(2).equals("40") || line.get(2).equals("38")
				|| line.get(2).equals("37")) {
			line.set(2, "33");
		} else
			g.drawString(String.valueOf((char) Integer.parseInt(line.get(2))),
					162, 166);
		if (line.get(3).equals("37"))
			g.drawString("LEFT", 162, 217);
		else if (line.get(3).equals("40") || line.get(3).equals("39")
				|| line.get(3).equals("38")) {
			line.set(3, "33");
		} else
			g.drawString(String.valueOf((char) Integer.parseInt(line.get(3))),
					162, 217);
		for (int i = 4; i < 8; i++) {
			if (line.get(i).equals("32") && i == 4)
				g.drawString("SPACE", 392, 68);
			else if (line.get(i).equals("32") && i == 5)
				g.drawString("SPACE", 392, 119);
			else if (line.get(i).equals("32") && i == 6)
				g.drawString("SPACE", 392, 166);
			else if (line.get(i).equals("32") && i == 7)
				g.drawString("SPACE", 392, 217);
			else if (i == 4)
				g.drawString(
						String.valueOf((char) Integer.parseInt(line.get(i))),
						392, 68);
			else if (i == 5)
				g.drawString(
						String.valueOf((char) Integer.parseInt(line.get(i))),
						392, 119);
			else if (i == 6)
				g.drawString(
						String.valueOf((char) Integer.parseInt(line.get(i))),
						392, 166);
			else if (i == 7)
				g.drawString(
						String.valueOf((char) Integer.parseInt(line.get(i))),
						392, 217);
		}

	}

	/**
	 * Stop playing the Menu and or the Ingamemenu Music
	 */

	public void stopMusic() {
		if (GameStateManager.getPrevState() == GameStateManager.INGAMEMENUE)
			Music.stopGameMusic();
		if (GameStateManager.getPrevState() == GameStateManager.MENU)
			Music.stopMenuMusic();
	}

	public OptionsMenu(GameStateManager gsm) {
		hud1 = new Content("Options", "Resources/Menu/Menu_Controlling.jpeg",
				512, 384, new Point(1, 1));
		hud2 = new Content("Options",
				"Resources/Menu/Menu_Controlling_Save.jpeg", 512, 384,
				new Point(1, 1));
		hud3 = new Content("Options",
				"Resources/Menu/Menu_Controlling_Back.jpeg", 512, 384,
				new Point(1, 1));
		hud4 = new Content("Options", "Resources/Menu/Controlling_Key.jpeg",
				308, 231, new Point(1, 1));
		state = 1;
		this.gsm = gsm;

	}

	public void update(InputHandler input) {
		if (firstTime == true) {
			this.input = input;
			input.getFile();
			line.add(0, input.getKeyUP());
			line.add(1, input.getKeyDOWN());
			line.add(2, input.getKeyRIGHT());
			line.add(3, input.getKeyLEFT());
			line.add(4, input.getKeyUSE());
			line.add(5, input.getKeyWEAPON());
			line.add(6, input.getKeyINVENTORY());
			line.add(7, input.getKeySTAT());

			up = line.get(0);
			down = line.get(1);
			right = line.get(2);
			left = line.get(3);
			use = line.get(4);
			weapon = line.get(5);
			inventory = line.get(6);
			stats = line.get(7);
			linesave = new ArrayList<String>(line);
			firstTime = false;
		}

		if (input.isPressed(input.LEFT) && pressedEnter != true) {
			if (state < 10)
				state = state + 10;
			else
				state = state - 10;
			Music.getSelectSound();
			// state--;
			// if(state == 0) state = 2;

		}
		if (input.isPressed(input.RIGHT) && pressedEnter != true) {
			if (state > 10)
				state = state - 10;
			else
				state = state + 10;
			Music.getSelectSound();

			// state++;
			// if(state == 3) state = 1;
		}
		if (input.isPressed(input.DOWN) && pressedEnter != true) {
			if (state < 5 || state < 15 && state > 10)
				state++;
			else
				state = state - 4;
			Music.getSelectSound();

		}
		if (input.isPressed(input.UP) && input.isPressed(input.ENTER) == false
				&& pressedEnter != true) {
			if (state > 1 && state < 6 || state > 11)
				state--;
			else
				state = state + 4;
			Music.getSelectSound();

		}
		if (input.isPressed(input.ENTER) && state == 5) {
			input.writeFile(up, down, right, left, use, weapon, inventory,
					stats);
			this.stopMusic();
			gsm.setState(GameStateManager.OPTIONSMENU);
		}
		if ((input.isPressed(input.ENTER) || input.isPressed(input.INGAMEMENUE)) && state == 15) {
			line = new ArrayList<String>(linesave);
			up = line.get(0);
			down = line.get(1);
			right = line.get(2);
			left = line.get(3);
			use = line.get(4);
			weapon = line.get(5);
			inventory = line.get(6);
			stats = line.get(7);
			input.setKeyUP((char) Integer.parseInt(line.get(0)));
			input.setKeyDOWN((char) Integer.parseInt(line.get(1)));
			input.setKeyRIGHT((char) Integer.parseInt(line.get(2)));
			input.setKeyLEFT((char) Integer.parseInt(line.get(3)));
			input.setKeyUSE((char) Integer.parseInt(line.get(4)));
			input.setKeyWEAPON((char) Integer.parseInt(line.get(5)));
			input.setKeyINVENTORY((char) Integer.parseInt(line.get(6)));
			input.setKeySTAT((char) Integer.parseInt(line.get(7)));
			input.writeFile(up, down, right, left, use, weapon, inventory,
					stats);
			this.stopMusic();
			InGameMenue.entered = false;
			if (GameStateManager.getPrevState() == GameStateManager.MENU)
				gsm.setState(GameStateManager.MENU);
			else
				gsm.setState(InGameMenue.enteredstate);
		}
		if (input.isPressed(input.ENTER) && pressedEnter == true) {

			pressedEnter = false;

		} else if (input.isPressed(input.ENTER) && state != 15 && state != 5) {
			pressedEnter = true;
			
		}
	}

	public void draw(Graphics2D g) {
		this.g = g;
		
		if (pressedEnter == false) {
			g.drawImage(hud1.getImage(0, 0), 0, 0, null);	

			if (state == 1) {
				g.setColor(Color.RED);
				g.fillRect(159, 40, 70, 40); // HOCH
				g.setColor(Color.BLACK);
				g.fillRect(161, 42, 66, 36); // HOCH
			}
			if (state == 2) {
				g.setColor(Color.RED);
				g.fillRect(159, 90, 70, 40); // Runter
				g.setColor(Color.BLACK);
				g.fillRect(161, 92, 66, 36); // Runter

			}
			if (state == 3) {
				g.setColor(Color.RED);
				g.fillRect(159, 138, 70, 40); // Rechts
				g.setColor(Color.BLACK);
				g.fillRect(161, 140, 66, 36); // Rechts

			}
			if (state == 4) {
				g.setColor(Color.RED);
				g.fillRect(159, 188, 70, 40); // Links
				g.setColor(Color.BLACK);
				g.fillRect(161, 190, 66, 36); // Links

			}
			if (state == 5)
				g.drawImage(hud2.getImage(0, 0), 0, 0, null);

			if (state == 11) {
				g.setColor(Color.RED);
				g.fillRect(389, 40, 70, 40); // Benutzen
				g.setColor(Color.BLACK);
				g.fillRect(391, 42, 66, 36); // Benutzen

			}
			if (state == 12) {
				g.setColor(Color.RED);
				g.fillRect(389, 90, 70, 40); // Waffe
				g.setColor(Color.BLACK);
				g.fillRect(391, 92, 66, 36); // Waffe

			}
			if (state == 13) {
				g.setColor(Color.RED);
				g.fillRect(389, 140, 70, 40); // Inventar
				g.setColor(Color.BLACK);
				g.fillRect(391, 142, 66, 36); // Inventar

			}
			if (state == 14) {
				g.setColor(Color.RED);
				g.fillRect(389, 188, 70, 40); // Status
				g.setColor(Color.BLACK);
				g.fillRect(391, 190, 66, 36); // Status

			}
			if (state == 15)
				g.drawImage(hud3.getImage(0, 0), 0, 0, null);
			g.setColor(Color.WHITE);

			if (firstTime == false) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				this.zeichenUmsetzung();
			}

			g.setColor(Color.WHITE);

		} else {
			g.drawImage(hud4.getImage(0, 0), 102, 77, null);
			s = new Integer(input.pressedKey);
			if (s > 31 && s < 91) {
				for (int i = 0; i < 8; i++) {
					if (s.toString().equals(line.get(i))
							&& !s.toString().equals("39")
							&& !s.toString().equals("40")
							&& !s.toString().equals("38")
							&& !s.toString().equals("39"))
						line.set(i, "33");
				}

				switch (state) {
				case 1:
					line.set(0, s.toString());
					input.setKeyUP((char) Integer.parseInt(line.get(0)));
					this.checkZeichen(s);
					break;
				case 2:
					line.set(1, s.toString());
					input.setKeyDOWN((char) Integer.parseInt(line.get(1)));
					this.checkZeichen(s);
					break;
				case 3:
					line.set(2, s.toString());
					input.setKeyRIGHT((char) Integer.parseInt(line.get(2)));
					this.checkZeichen(s);
					break;
				case 4:
					line.set(3, s.toString());
					input.setKeyLEFT((char) Integer.parseInt(line.get(3)));
					this.checkZeichen(s);
					break;
				case 11:
					line.set(4, s.toString());
					input.setKeyUSE((char) Integer.parseInt(line.get(4)));
					this.checkZeichen(s);
					break;
				case 12:
					line.set(5, s.toString());
					input.setKeyWEAPON((char) Integer.parseInt(line.get(5)));
					this.checkZeichen(s);
					break;
				case 13:
					line.set(6, s.toString());
					input.setKeyINVENTORY((char) Integer.parseInt(line.get(6)));
					this.checkZeichen(s);
					break;
				case 14:
					line.set(7, s.toString());
					input.setKeySTAT((char) Integer.parseInt(line.get(7)));
					this.checkZeichen(s);
					break;
				}
				up = line.get(0);
				down = line.get(1);
				right = line.get(2);
				left = line.get(3);
				use = line.get(4);
				weapon = line.get(5);
				inventory = line.get(6);
				stats = line.get(7);
			}
		}

	}

}
