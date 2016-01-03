package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Main.Game;

/**
 * @author lumpy Class for the Ingame Menu. Save and Load Game, Change Control,
 *         Exit Game
 */
public class InGameMenue {

	private InGameMenue menue;
	private Content hud1, hud2, hud3, hud4;
	private int state;
	private GameStateManager gsm;
	public static boolean entered = false;
	public static int enteredstate = 0;
	private boolean speichern = false;
	private boolean laden = false;

	public InGameMenue(GameStateManager gsm) {
		this.gsm = gsm;
		hud1 = new Content("InGameMenue", "Resources/Menu/InGameMenue1.jpeg",
				282, 256, new Point(1, 1));
		hud2 = new Content("InGameMenue", "Resources/Menu/InGameMenue2.jpeg",
				282, 256, new Point(1, 1));
		hud3 = new Content("InGameMenue", "Resources/Menu/InGameMenue3.jpeg",
				282, 256, new Point(1, 1));
		hud4 = new Content("InGameMenue", "Resources/Menu/InGameMenue4.jpeg",
				282, 256, new Point(1, 1));
		state = 4;
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.DOWN)) {
			Music.getSelectSound();
			state--;
			if (state == 0)
				state = 4;

		} else if (input.isPressed(input.UP)) {
			Music.getSelectSound();
			state++;
			if (state == 5)
				state = 1;
		}
		if (state == 4 && input.isPressed(input.ENTER)) {
			speichern = true;
			// speichern
		}
		if (state == 3 && input.isPressed(input.ENTER)) {
			laden = true;
			// laden
		}
		if (state == 2 && input.isPressed(input.ENTER)) {
			if (entered == false) {
				enteredstate = gsm.getPrevState();
				entered = true;
			}
			gsm.setState(gsm.OPTIONSMENU);
			// optionen
		}
		if (state == 1 && input.isPressed(input.ENTER)) {
			System.exit(0);
		}
	}

	public void draw(Graphics2D g) {
		if (state == 4) {
			g.drawImage(hud1.getImage(0, 0), 115, 32, null);
			laden = false;

		}
		if (state == 3) {
			g.drawImage(hud2.getImage(0, 0), 115, 32, null);
			speichern = false;
		}
		if (state == 2) {
			g.drawImage(hud3.getImage(0, 0), 115, 32, null);
			speichern = false;
			laden = false;
		}
		if (state == 1) {
			g.drawImage(hud4.getImage(0, 0), 115, 32, null);
			speichern = false;
			laden = false;
		}
		if (laden == true) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setFont(new Font("Courier New", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("Laden zurzeit nicht moeglich!", 80, 330);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));

		}
		if (speichern == true) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setFont(new Font("Courier New", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("Speichern zurzeit nicht moeglich", 80, 330);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));

		}

	}
}
