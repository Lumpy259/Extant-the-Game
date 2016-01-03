 package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Player;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Music;

public class OutroState extends GameState {

	public ArrayList<Integer> name = new ArrayList<>();
	private Content  outro1, yui, hero;
	private int state;
	ArrayList<ArrayList<String>> dialog = new ArrayList<>();
	int dialogCounter = 0;
	int inDialogCounter = 0;
	String playerName = "";
	
	public OutroState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		outro1 = new Content("Intro1", "Resources/Outro/extant_outro.jpg", 512, 384, new Point(1,1));
		yui = new Content("Yui", "Resources/Intro/Yui.png", 70, 70, new Point(1,1));
		hero = new Content("Hero", "Resources/Intro/Hero.png", 70, 70, new Point(1,1)); 
		state = 1;
		ArrayList dialog1 = new ArrayList<>();
		dialog1.add("");
		dialog1.add("Danke das du Extant gespielt hast");
		dialog.add(dialog1);
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.SPACE) && state == 2){
			System.exit(0);
		}
		else if (input.isPressed(input.SPACE)){
			state++;
			inDialogCounter++;
		}
		else {
			if (inDialogCounter > dialog.get(dialogCounter).size()-1) {
				inDialogCounter = 0;
				dialogCounter++;
			}
			if (dialogCounter > dialog.size()) 
				dialogCounter--;
		}
	}

	
	public void draw(Graphics2D g) {
		if(state == 1)		g.drawImage(outro1.getImage(0,0), 0, 0, null);
		else {
			g.setColor(Color.BLACK);
			g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));
			g.setColor(Color.WHITE);
			drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 140, 155);
		}
	}

	public void handleInput(InputHandler input) {	
		
	}

	private void drawString(Graphics g, String text, int x, int y) {
             for (String line : text.split("\n"))
                 g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}
